package com.easyfactor.invoice.domain;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.easyfactor.shared.error.BusinessException;
import com.easyfactor.shared.error.ErrorCode;

import lombok.Getter;

/**
 * Invoice aggregate root; guards all lifecycle transitions.

 * publicToken is a random secret UUID your invoice gets at creation,
 * and it's the part of the link that identifies the invoice to a customer — without any login.
 */
@Getter
public class Invoice {

    private final UUID id;
    private final String invoiceNumber;
    private final UUID ownerId;
    private final String customerPhoneNumber;
    private final String customerFirstName;
    private final String customerLastName;
    private final String customerAddress;
    private final String customerPostalCode;
    private final UUID publicToken;
    private final Instant createdAt;
    private List<InvoiceItem> items;
    private long total;
    private String notes;
    private InvoiceStatus status;
    private String cancelReason;
    private Instant paidAt;
    private Instant dispatchedAt;
    private Instant updatedAt;

    public Invoice(UUID id, String invoiceNumber, UUID ownerId, String customerPhoneNumber,
            String customerFirstName, String customerLastName, String customerAddress, String customerPostalCode,
            List<InvoiceItem> items, long total, String notes, InvoiceStatus status, UUID publicToken,
            String cancelReason, Instant createdAt, Instant paidAt, Instant dispatchedAt, Instant updatedAt) {
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.ownerId = ownerId;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.items = items;
        this.total = total;
        this.notes = notes;
        this.status = status;
        this.publicToken = publicToken;
        this.cancelReason = cancelReason;
        this.createdAt = createdAt;
        this.paidAt = paidAt;
        this.dispatchedAt = dispatchedAt;
        this.updatedAt = updatedAt;
    }

    public static Invoice create(UUID id, String invoiceNumber, UUID ownerId, InvoiceDraft draft, Instant now) {    //it is factory method so it should be static
        List<InvoiceItem> items = buildItems(draft);
        Invoice invoice = new Invoice(id, invoiceNumber, ownerId, draft.customerPhoneNumber(),
                draft.customerFirstName(), draft.customerLastName(), draft.customerAddress(),
                draft.customerPostalCode(), items, 0, draft.notes(), InvoiceStatus.WAIT_FOR_PAYMENT,
                UUID.randomUUID(), null, now, null, null, now);
        invoice.total = invoice.recalculateTotal();
        return invoice;
    }

    public void markPaid(Instant now) {
        transitionTo(InvoiceStatus.WAIT_FOR_DISPATCH, now);
        this.paidAt = now;
    }

    public void markOnDispatching(Instant now) {
        transitionTo(InvoiceStatus.ON_DISPATCHING, now);
    }

    public void markDispatched(Instant now) {
        transitionTo(InvoiceStatus.DISPATCHED, now);
        this.dispatchedAt = now;
    }

    public void cancel(String reason, Instant now) {
        if (status != InvoiceStatus.WAIT_FOR_PAYMENT && status != InvoiceStatus.WAIT_FOR_DISPATCH) {
            throw new BusinessException(ErrorCode.INVALID_INVOICE_STATE, status, InvoiceStatus.CANCELLED);
        }
        this.status = InvoiceStatus.CANCELLED;
        this.cancelReason = reason;
        this.updatedAt = now;
    }

    public void update(InvoiceDraft draft, Instant now) {
        if (status != InvoiceStatus.WAIT_FOR_PAYMENT) {                             //just before payment we can update the invoice
            throw new BusinessException(ErrorCode.INVALID_INVOICE_STATE, status);
        }
        this.items = buildItems(draft);
        this.notes = draft.notes();
        this.total = recalculateTotal();
        this.updatedAt = now;
    }

    public boolean isPaid() {
        return status == InvoiceStatus.WAIT_FOR_DISPATCH
                || status == InvoiceStatus.ON_DISPATCHING
                || status == InvoiceStatus.DISPATCHED;
    }

    private static List<InvoiceItem> buildItems(InvoiceDraft draft) {
        if (draft.items() == null || draft.items().isEmpty()) {
            throw new BusinessException(ErrorCode.VALIDATION_FAILED, "items must not be empty");
        }
        return draft.items().stream()
                .map(item -> InvoiceItem.create(item.productId(), item.description(), item.quantity(), item.unitPrice()))
                .toList();
    }

    private void transitionTo(InvoiceStatus target, Instant now) {
        if (!status.canTransitionTo(target)) {
            throw new BusinessException(ErrorCode.INVALID_INVOICE_STATE, status, target);
        }
        this.status = target;
        this.updatedAt = now;
    }

    private long recalculateTotal() {
        return items.stream().mapToLong(InvoiceItem::getLineTotal).sum();
    }
}
