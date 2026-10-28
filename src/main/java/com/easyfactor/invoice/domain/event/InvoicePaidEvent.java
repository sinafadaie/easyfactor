package com.easyfactor.invoice.domain.event;

import java.time.Instant;
import java.util.UUID;

/**
 * Published when the owner marks an invoice paid (Observer pattern).
 */
public record InvoicePaidEvent(
        UUID invoiceId,
        String invoiceNumber,
        UUID ownerId,
        long total,
        Instant paidAt) {
}
