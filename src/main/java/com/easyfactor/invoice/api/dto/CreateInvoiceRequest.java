package com.easyfactor.invoice.api.dto;

import java.util.List;

import com.easyfactor.invoice.domain.InvoiceDraft;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * Manual invoice creation input (owner-created). Customer data is an embedded
 * snapshot; the frontend can pre-fill it from a saved customer.
 */
public record CreateInvoiceRequest(
        @NotBlank @Size(max = 32) String customerPhoneNumber,
        @Size(max = 255) String customerFirstName,
        @Size(max = 255) String customerLastName,
        @Size(max = 512) String customerAddress,
        @Size(max = 32) String customerPostalCode,
        @Size(max = 2000) String notes,
        @NotEmpty @Valid List<CreateInvoiceItemRequest> items) {

    public InvoiceDraft toDraft() {
        return new InvoiceDraft(
                customerPhoneNumber,
                customerFirstName,
                customerLastName,
                customerAddress,
                customerPostalCode,
                notes,
                items.stream().map(CreateInvoiceItemRequest::toItem).toList());
    }
}
