package com.easyfactor.invoice.domain;

import java.util.UUID;

/**
 * Invoice number creation port (Factory abstraction).
 */
public interface InvoiceNumberGenerator {

    String nextNumber(UUID ownerId);
}
