package com.easyfactor.shared.domain;

/**
 * Shared-kernel gateway identifier; lets Invoice record its chosen provider
 * without depending on the payment package.
 */
public enum PaymentProvider {

    TELEGRAM_STARS,
    STRIPE
}
