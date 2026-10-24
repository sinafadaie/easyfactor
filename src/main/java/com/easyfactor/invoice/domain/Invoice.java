package com.easyfactor.invoice.domain;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.easyfactor.shared.domain.PaymentProvider;

import lombok.Getter;

/**
 * Invoice aggregate root; guards all lifecycle transitions.
 */
@Getter
public class Invoice {

    private final UUID id;
    private final String invoiceNumber;
    private final UUID ownerId;
    private final UUID customerId;
    private final Long customerTelegramUserId;
    private final String customerDisplayName;
    private InvoiceStatus status;
    private final List<InvoiceItem> items;
    private long total;
    private final PaymentProvider paymentProvider;
    private final LocalDate dueDate;
    private final String notes;
    private final Instant createdAt;
    private Instant sentAt;
    private Instant paidAt;
    private Instant updatedAt;

    public Invoice(UUID id, String invoiceNumber, UUID ownerId, UUID customerId, Long customerTelegramUserId,
            String customerDisplayName, InvoiceStatus status, List<InvoiceItem> items, long total,
            PaymentProvider paymentProvider, LocalDate dueDate, String notes, Instant createdAt, Instant sentAt,
            Instant paidAt, Instant updatedAt) {
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.ownerId = ownerId;
        this.customerId = customerId;
        this.customerTelegramUserId = customerTelegramUserId;
        this.customerDisplayName = customerDisplayName;
        this.status = status;
        this.items = items;
        this.total = total;
        this.paymentProvider = paymentProvider;
        this.dueDate = dueDate;
        this.notes = notes;
        this.createdAt = createdAt;
        this.sentAt = sentAt;
        this.paidAt = paidAt;
        this.updatedAt = updatedAt;
    }

    public static Invoice create(UUID id, String invoiceNumber, UUID ownerId, UUID customerId, Long customerTelegramUserId,
            String customerDisplayName, InvoiceDraft draft, Instant now) {
        // TODO
        return null;
    }

    public void markSent(Instant now) {
        // TODO: guard DRAFT -> SENT
    }

    public void markPendingPayment(Instant now) {
        // TODO: guard SENT -> PENDING_PAYMENT
    }

    public void markPaid(Instant now) {
        // TODO: guard PENDING_PAYMENT -> PAID
    }

    public void cancel(String reason, Instant now) {
        // TODO: guard non-terminal -> CANCELLED
    }

    public void expire(Instant now) {
        // TODO: guard SENT/PENDING_PAYMENT -> EXPIRED
    }

    public boolean isPayable() {
        // TODO: status is SENT or PENDING_PAYMENT and not past due
        return false;
    }

    private void transitionTo(InvoiceStatus target, Instant now) {
        // TODO: throw BusinessException(INVALID_INVOICE_STATE) on illegal transition
    }

    private long recalculateTotal() {
        // TODO: sum of item line totals
        return 0;
    }
}
