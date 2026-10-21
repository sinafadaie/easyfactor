package com.easyfactor.invoice.api.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.easyfactor.shared.domain.CurrencyCode;
import com.easyfactor.shared.domain.PaymentProvider;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Manual invoice creation input (owner-created).
 */
public record CreateInvoiceRequest(
        @NotNull UUID customerId,
        @NotNull CurrencyCode currency,
        @NotNull PaymentProvider paymentProvider,
        LocalDate dueDate,
        @Size(max = 2000) String notes,
        @NotEmpty @Valid List<CreateInvoiceItemRequest> items) {
}
