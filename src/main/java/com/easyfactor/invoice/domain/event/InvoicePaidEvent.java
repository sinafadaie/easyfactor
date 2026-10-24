package com.easyfactor.invoice.domain.event;

import java.time.Instant;
import java.util.UUID;

/**
 * Published when an invoice is paid (Observer pattern).
 */
public record InvoicePaidEvent(
        UUID invoiceId,
        UUID paymentId,
        String invoiceNumber,
        UUID ownerId,
        UUID customerId,
        Long customerTelegramUserId,
        long total,
        Instant paidAt) {
}
