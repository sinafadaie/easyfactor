package com.easyfactor.catalog.domain;

import java.time.Instant;
import java.util.UUID;

/**
 * Product (catalog offer) aggregate root. Deletion is a soft archive.
 */
public class Product {

    private final UUID id;
    private final UUID ownerId;
    private String name;
    private String description;
    private long priceRials;
    private ProductStatus status;
    private DigitalDeliveryType deliveryType;
    private String deliveryPayload;
    private String imageUrl;
    private final Instant createdAt;
    private Instant updatedAt;

    public Product(UUID id, UUID ownerId, String name, String description, long priceRials, ProductStatus status,
            DigitalDeliveryType deliveryType, String deliveryPayload, String imageUrl, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.description = description;
        this.priceRials = priceRials;
        this.status = status;
        this.deliveryType = deliveryType;
        this.deliveryPayload = deliveryPayload;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Product create(UUID ownerId, ProductDraft draft, Instant now) {
        // TODO
        return null;
    }

    public void update(ProductDraft draft, Instant now) {
        // TODO
    }

    public void archive(Instant now) {
        // TODO
    }

    public boolean isActive() {
        // TODO
        return false;
    }

    public boolean belongsTo(UUID ownerId) {
        // TODO
        return false;
    }

    public UUID getId() {
        return id;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getPriceRials() {
        return priceRials;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public DigitalDeliveryType getDeliveryType() {
        return deliveryType;
    }

    public String getDeliveryPayload() {
        return deliveryPayload;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
