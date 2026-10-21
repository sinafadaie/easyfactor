package com.easyfactor.invoice.domain.event;

import java.time.Instant;
import java.util.UUID;

import com.easyfactor.shared.domain.Money;

/**
 * Published when an invoice draft is created (Observer pattern).
 */
public record InvoiceCreatedEvent(
        UUID invoiceId,
        String invoiceNumber,
        UUID ownerId,
        UUID customerId,
        Money total,
        Instant occurredAt) {
}
