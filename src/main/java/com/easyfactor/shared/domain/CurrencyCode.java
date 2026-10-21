package com.easyfactor.shared.domain;

/**
 * Supported currencies and their minor-unit (decimal) counts.
 * XTR (Telegram Stars) is integer-only: 0 decimals.
 */
public enum CurrencyCode {

    XTR("XTR", 0),
    USD("USD", 2),
    EUR("EUR", 2);

    private final String isoCode;
    private final int minorUnits;

    CurrencyCode(String isoCode, int minorUnits) {
        this.isoCode = isoCode;
        this.minorUnits = minorUnits;
    }

    public String isoCode() {
        return isoCode;
    }

    public int minorUnits() {
        return minorUnits;
    }
}
