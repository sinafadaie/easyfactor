package com.easyfactor.invoice.api.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.easyfactor.invoice.domain.Invoice;
import com.easyfactor.invoice.domain.InvoiceItem;
import com.easyfactor.invoice.domain.InvoiceStatus;
import com.easyfactor.shared.domain.PaymentProvider;

public record InvoiceResponse(
        UUID id,
        String invoiceNumber,
        InvoiceStatus status,
        UUID ownerId,
        UUID customerId,
        Long customerTelegramUserId,
        String customerDisplayName,
        List<InvoiceItem> items,
        long total,
        LocalDate dueDate,
        String notes,
        PaymentProvider paymentProvider,
        Instant sentAt,
        Instant paidAt,
        Instant createdAt) {

    public static InvoiceResponse from(Invoice invoice) {
        // TODO
        return null;
    }
}
