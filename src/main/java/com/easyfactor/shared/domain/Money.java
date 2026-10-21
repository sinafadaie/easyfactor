package com.easyfactor.shared.domain;

import java.math.BigDecimal;

/**
 * Immutable amount + currency value object with scale enforcement.
 */
public record Money(
        BigDecimal amount,
        CurrencyCode currency) {

    public static Money of(BigDecimal amount, CurrencyCode currency) {
        // TODO: normalize scale to currency minor units
        return null;
    }

    public static Money zero(CurrencyCode currency) {
        // TODO
        return null;
    }

    public Money plus(Money other) {
        // TODO: enforce same currency
        return null;
    }

    public Money minus(Money other) {
        // TODO
        return null;
    }

    public Money multiply(int quantity) {
        // TODO
        return null;
    }

    public boolean isPositive() {
        // TODO
        return false;
    }

    public boolean isSameCurrencyAs(Money other) {
        // TODO
        return false;
    }

    public long toMinorUnits() {
        // TODO: convert to smallest currency unit (Telegram amounts are integer minor units)
        return 0;
    }
}
