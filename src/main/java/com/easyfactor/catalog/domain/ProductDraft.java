package com.easyfactor.catalog.domain;

import com.easyfactor.shared.domain.Money;

/**
 * Creation/update input value for a Product.
 */
public record ProductDraft(
        String name,
        String description,
        Money price,
        DigitalDeliveryType deliveryType,
        String deliveryPayload,
        String imageUrl,
        boolean active) {
}
