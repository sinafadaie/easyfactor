package com.easyfactor.payment.domain.event;

import java.time.Instant;
import java.util.UUID;

/**
 * Published when a payment fails (Observer pattern).
 */
public record PaymentFailedEvent(
        UUID paymentId,
        UUID invoiceId,
        String reason,
        Instant occurredAt) {
}
