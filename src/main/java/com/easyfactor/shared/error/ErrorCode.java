package com.easyfactor.shared.error;

import org.springframework.http.HttpStatus;

/**
 * Error taxonomy with default HTTP status.
 */
public enum ErrorCode {

    VALIDATION_FAILED("VALIDATION_FAILED", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("UNAUTHORIZED", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("FORBIDDEN", HttpStatus.FORBIDDEN),
    NOT_FOUND("NOT_FOUND", HttpStatus.NOT_FOUND),
    INVALID_INIT_DATA("INVALID_INIT_DATA", HttpStatus.UNAUTHORIZED),
    INVALID_INVOICE_STATE("INVALID_INVOICE_STATE", HttpStatus.CONFLICT),
    PAYMENT_ALREADY_PROCESSED("PAYMENT_ALREADY_PROCESSED", HttpStatus.CONFLICT),
    GATEWAY_UNAVAILABLE("GATEWAY_UNAVAILABLE", HttpStatus.SERVICE_UNAVAILABLE),
    TELEGRAM_API_ERROR("TELEGRAM_API_ERROR", HttpStatus.BAD_GATEWAY),
    INTERNAL_ERROR("INTERNAL_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final HttpStatus status;

    ErrorCode(String code, HttpStatus status) {
        this.code = code;
        this.status = status;
    }

    public String code() {
        return code;
    }

    public HttpStatus status() {
        return status;
    }
}
