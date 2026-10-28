package com.easyfactor.invoice.domain.event;

import java.time.Instant;
import java.util.UUID;

/**
 * Published when an invoice is created (Observer pattern).
 *
 * Observer Pattern means:
 * One class announces an event, other classes can react to it.
 * So InvoiceService just says:
 * invoice created/invoice paid
 * and does not directly call Telegram or SMS.
 * This keeps code decoupled and makes new listeners easy to add later.
 * otherwise we need to add them directly on invoice service!
 */
public record InvoiceCreatedEvent(
        UUID invoiceId,
        String invoiceNumber,
        UUID ownerId,
        long total,
        Instant occurredAt) {
}
