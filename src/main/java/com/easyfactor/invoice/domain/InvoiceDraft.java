package com.easyfactor.invoice.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.easyfactor.shared.domain.PaymentProvider;

/**
 * Creation input value for an Invoice.
 */
public record InvoiceDraft(
        UUID customerId,
        PaymentProvider paymentProvider,
        LocalDate dueDate,
        String notes,
        List<Item> items) {

    public record Item(
            UUID productId,
            String description,
            int quantity,
            long unitPrice) {
    }
}
