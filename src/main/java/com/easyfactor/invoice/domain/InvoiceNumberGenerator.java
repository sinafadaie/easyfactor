package com.easyfactor.invoice.domain;

/**
 * Invoice number creation port (Factory abstraction).
 */
public interface InvoiceNumberGenerator {

    String nextNumber();
}
