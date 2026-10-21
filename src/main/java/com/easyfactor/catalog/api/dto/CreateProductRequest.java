package com.easyfactor.catalog.api.dto;

import java.math.BigDecimal;

import com.easyfactor.catalog.domain.DigitalDeliveryType;
import com.easyfactor.shared.domain.CurrencyCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateProductRequest(
        @NotBlank @Size(max = 255) String name,
        @Size(max = 4000) String description,
        @NotNull @Positive BigDecimal priceAmount,
        @NotNull CurrencyCode currency,
        @NotNull DigitalDeliveryType deliveryType,
        @Size(max = 4000) String deliveryPayload,
        @Size(max = 2048) String imageUrl,
        boolean active) {
}
