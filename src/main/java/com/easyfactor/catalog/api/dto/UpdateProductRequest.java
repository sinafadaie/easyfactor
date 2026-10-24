package com.easyfactor.catalog.api.dto;

import java.util.Map;

import com.easyfactor.catalog.domain.DigitalDeliveryType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UpdateProductRequest(
        @NotBlank @Size(max = 255) String name,
        @Size(max = 4000) String description,
        @NotBlank @Size(max = 64) String category,
        @NotBlank @Size(max = 32) String unit,
        Map<String, Object> attributes,
        @NotNull @Positive long price,
        @NotNull DigitalDeliveryType deliveryType,
        @Size(max = 4000) String deliveryPayload,
        @Size(max = 2048) String imageUrl,
        boolean active) {
}
