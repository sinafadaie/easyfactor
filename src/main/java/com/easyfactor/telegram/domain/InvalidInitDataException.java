package com.easyfactor.telegram.domain;

import com.easyfactor.shared.error.BusinessException;
import com.easyfactor.shared.error.ErrorCode;

/**
 * Rejects bad or expired Mini App initData.
 */
public class InvalidInitDataException extends BusinessException {

    public InvalidInitDataException(String reason) {
        super(ErrorCode.INVALID_INIT_DATA, reason);
    }
}
