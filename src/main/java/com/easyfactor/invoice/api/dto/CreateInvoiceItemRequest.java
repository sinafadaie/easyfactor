package com.easyfactor.invoice.api.dto;

import java.util.UUID;

import com.easyfactor.invoice.domain.InvoiceDraft;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateInvoiceItemRequest(
        UUID productId,
        @NotBlank @Size(max = 512) String description,
        @Min(1) int quantity,
        @Min(0) long unitPrice) {

    InvoiceDraft.Item toItem() {
        return new InvoiceDraft.Item(productId, description, quantity, unitPrice);
    }
}
