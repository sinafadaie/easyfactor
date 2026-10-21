package com.easyfactor.catalog.api.dto;

import com.easyfactor.catalog.domain.DigitalDeliveryType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UpdateProductRequest(
        @NotBlank @Size(max = 255) String name,
        @Size(max = 4000) String description,
        @NotNull @Positive long priceRials,
        @NotNull DigitalDeliveryType deliveryType,
        @Size(max = 4000) String deliveryPayload,
        @Size(max = 2048) String imageUrl,
        boolean active) {
}
