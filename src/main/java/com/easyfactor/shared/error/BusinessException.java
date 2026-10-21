package com.easyfactor.shared.error;

/**
 * Base runtime exception carrying an {@link ErrorCode}.
 */
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;
    private final Object[] args;

    public BusinessException(ErrorCode errorCode) {
        this(errorCode, new Object[0]);
    }

    public BusinessException(ErrorCode errorCode, Object... args) {
        super(errorCode.name());
        this.errorCode = errorCode;
        this.args = args;
    }

    public ErrorCode errorCode() {
        return errorCode;
    }

    public Object[] args() {
        return args;
    }
}
