package com.easyfactor.catalog.api.dto;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import com.easyfactor.catalog.domain.DigitalDeliveryType;
import com.easyfactor.catalog.domain.Product;
import com.easyfactor.catalog.domain.ProductStatus;

/**
 * Owner-facing product view including delivery metadata.
 */
public record ProductResponse(
        UUID id,
        UUID ownerId,
        String name,
        String description,
        String category,
        String unit,
        Map<String, Object> attributes,
        long price,
        ProductStatus status,
        DigitalDeliveryType deliveryType,
        String deliveryPayload,
        String imageUrl,
        Instant createdAt,
        Instant updatedAt) {

    public static ProductResponse from(Product product) {
        // TODO
        return null;
    }
}
