package com.easyfactor.catalog.api.dto;

import java.util.UUID;

import com.easyfactor.catalog.domain.DigitalDeliveryType;
import com.easyfactor.catalog.domain.Product;
import com.easyfactor.shared.domain.Money;

/**
 * Customer-facing product view without secrets (no delivery payload).
 */
public record PublicProductResponse(
        UUID id,
        UUID ownerId,
        String name,
        String description,
        Money price,
        DigitalDeliveryType deliveryType,
        String imageUrl) {

    public static PublicProductResponse from(Product product) {
        // TODO
        return null;
    }
}
