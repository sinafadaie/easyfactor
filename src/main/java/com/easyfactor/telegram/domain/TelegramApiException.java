package com.easyfactor.telegram.domain;

/**
 * Upstream Bot API failure.
 */
public class TelegramApiException extends RuntimeException {

    private final String method;
    private final Integer errorCode;

    public TelegramApiException(String method, String description, Integer errorCode) {
        super(description);
        this.method = method;
        this.errorCode = errorCode;
    }

    public String method() {
        return method;
    }

    public Integer errorCode() {
        return errorCode;
    }
}
