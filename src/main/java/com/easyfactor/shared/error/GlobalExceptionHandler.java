package com.easyfactor.shared.error;

import com.easyfactor.shared.api.ApiResponse;

import jakarta.validation.ConstraintViolationException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Translates exceptions into the uniform {@link ApiResponse} envelope.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    ApiResponse<?> handleBusinessException(BusinessException ex) {
        // TODO
        return null;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ApiResponse<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        // TODO
        return null;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ApiResponse<?> handleConstraintViolation(ConstraintViolationException ex) {
        // TODO
        return null;
    }

    @ExceptionHandler(AccessDeniedException.class)
    ApiResponse<?> handleAccessDenied(AccessDeniedException ex) {
        // TODO
        return null;
    }

    @ExceptionHandler(Exception.class)
    ApiResponse<?> handleUnexpected(Exception ex) {
        // TODO
        return null;
    }
}
