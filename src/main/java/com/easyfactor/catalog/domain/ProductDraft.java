package com.easyfactor.catalog.domain;

/**
 * Creation/update input value for a Product.
 */
public record ProductDraft(
        String name,
        String description,
        long priceRials,
        DigitalDeliveryType deliveryType,
        String deliveryPayload,
        String imageUrl,
        boolean active) {
}
