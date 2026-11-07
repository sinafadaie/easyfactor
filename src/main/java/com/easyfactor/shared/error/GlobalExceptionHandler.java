package com.easyfactor.shared.error;

import com.easyfactor.shared.api.ApiError;
import com.easyfactor.shared.api.ApiResponse;

import jakarta.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;


/**
 * Translates exceptions into the uniform {@link ApiResponse} envelope.
 *
 * we need to use ResponseEntity to manage appropriate status code
 * if we use ApiResponse, we always return 200!
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    ResponseEntity<ApiResponse<?>> handleBusinessException(BusinessException ex) {
        ApiError apiError = new ApiError(ex.errorCode().code(), ex.getMessage(), null, null);
        return new ResponseEntity<>(ApiResponse.error(apiError), ex.errorCode().status());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        HashMap<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(fe -> fieldErrors.put(fe.getField(), fe.getDefaultMessage()));

        ApiError apiError = new ApiError(ErrorCode.VALIDATION_FAILED.code(), "Validation failed", fieldErrors, null);
        return new ResponseEntity<>(ApiResponse.error(apiError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ApiResponse<?>> handleConstraintViolation(ConstraintViolationException ex) {
        ApiError apiError = new ApiError(ErrorCode.VALIDATION_FAILED.code(), ex.getMessage(), null, null);

        return new ResponseEntity<>(ApiResponse.error(apiError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    ResponseEntity<ApiResponse<?>> handleAccessDenied(AccessDeniedException ex) {
        ApiError apiError = new ApiError(ErrorCode.FORBIDDEN.code(), "Access denied", null, null);

        return new ResponseEntity<>(ApiResponse.error(apiError), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiResponse<?>> handleUnexpected(Exception ex) {
        log.error("Unhandled exception", ex);       // never let a 500 vanish silently (it just show on console)

        ApiError apiError = new ApiError(ErrorCode.INTERNAL_ERROR.code(), "Internal server error", null, null);
        return new ResponseEntity<>(ApiResponse.error(apiError), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //----------------
    //(Exception.class) catches everything, including Spring MVC's built-in exceptions
    //that already carry a meaningful status
    //so we add this two extra handler (specific exception handlers always win over the catch-all)
    @ExceptionHandler(NoHandlerFoundException.class)
    ResponseEntity<ApiResponse<?>> handleNoHandlerFound(NoHandlerFoundException ex) {
        ApiError apiError = new ApiError(ErrorCode.NOT_FOUND.code(), "Resource not found", null, null);
        return new ResponseEntity<>(ApiResponse.error(apiError), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    ResponseEntity<ApiResponse<?>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        ApiError apiError = new ApiError(ErrorCode.NOT_FOUND.code(), "Method not allowed", null, null);
        return new ResponseEntity<>(ApiResponse.error(apiError), HttpStatus.METHOD_NOT_ALLOWED);
    }
}
