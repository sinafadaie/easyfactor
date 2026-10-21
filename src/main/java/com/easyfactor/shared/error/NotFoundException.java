package com.easyfactor.shared.error;

/**
 * Not-found specialization of {@link BusinessException}.
 */
public class NotFoundException extends BusinessException {

    public NotFoundException(String resource, Object id) {
        super(ErrorCode.NOT_FOUND, resource, id);
    }
}
