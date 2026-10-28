package com.easyfactor.invoice.api.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.easyfactor.invoice.domain.Invoice;
import com.easyfactor.invoice.domain.InvoiceItem;
import com.easyfactor.invoice.domain.InvoiceStatus;

public record InvoiceResponse(
        UUID id,
        String invoiceNumber,
        InvoiceStatus status,
        UUID ownerId,
        String customerPhoneNumber,
        String customerFirstName,
        String customerLastName,
        String customerAddress,
        String customerPostalCode,
        List<InvoiceItem> items,
        long total,
        String notes,
        UUID publicToken,
        Instant paidAt,
        Instant dispatchedAt,
        Instant createdAt) {

    public static InvoiceResponse from(Invoice invoice) {
        return new InvoiceResponse(
                invoice.getId(),
                invoice.getInvoiceNumber(),
                invoice.getStatus(),
                invoice.getOwnerId(),
                invoice.getCustomerPhoneNumber(),
                invoice.getCustomerFirstName(),
                invoice.getCustomerLastName(),
                invoice.getCustomerAddress(),
                invoice.getCustomerPostalCode(),
                invoice.getItems(),
                invoice.getTotal(),
                invoice.getNotes(),
                invoice.getPublicToken(),
                invoice.getPaidAt(),
                invoice.getDispatchedAt(),
                invoice.getCreatedAt());
    }
}
