package com.easyfactor.telegram.domain;

/**
 * Rejects bad or expired Mini App initData.
 */
public class InvalidInitDataException extends RuntimeException {

    public InvalidInitDataException(String reason) {
        super(reason);
    }
}
