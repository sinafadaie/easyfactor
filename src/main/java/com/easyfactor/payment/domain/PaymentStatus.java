package com.easyfactor.payment.domain;

/**
 * Payment lifecycle.
 */
public enum PaymentStatus {

    INITIATED,
    SUCCEEDED,
    FAILED,
    REFUNDED
}
