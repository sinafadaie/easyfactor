package com.easyfactor.catalog.domain;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import com.easyfactor.shared.error.BusinessException;
import com.easyfactor.shared.error.ErrorCode;

import lombok.Getter;

/**
 * Product (catalog offer) aggregate root.
 * Deletion is a soft archive. (to store old invoices!)
 */
@Getter
public class Product {

    private final UUID id;
    private final UUID ownerId;
    private String name;
    private String description;
    private String category;
    private String unit;
    private Map<String, Object> attributes;
    private long price;
    private ProductStatus status;
    private DigitalDeliveryType deliveryType;
    private String deliveryPayload;
    private String imageUrl;
    private final Instant createdAt;
    private Instant updatedAt;

    public Product(UUID id, UUID ownerId, String name, String description, String category, String unit,
            Map<String, Object> attributes, long price, ProductStatus status,
            DigitalDeliveryType deliveryType, String deliveryPayload, String imageUrl, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.description = description;
        this.category = category;
        this.unit = unit;
        this.attributes = attributes;
        this.price = price;
        this.status = status;
        this.deliveryType = deliveryType;
        this.deliveryPayload = deliveryPayload;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Product create(UUID ownerId, ProductDraft draft, Instant now) {       //it is factory method
        requireValid(draft);                                                                     //it should be static
        ProductStatus status = draft.active() ? ProductStatus.ACTIVE : ProductStatus.ARCHIVED;

        return new Product(UUID.randomUUID(), ownerId, draft.name(), draft.description(), draft.category(), draft.unit(),
                attributesOrEmpty(draft), draft.price(), status, draft.deliveryType(),
                draft.deliveryPayload(), draft.imageUrl(), now, now);
    }

    public void update(ProductDraft draft, Instant now) {
        requireValid(draft);
        this.name = draft.name();
        this.description = draft.description();
        this.category = draft.category();
        this.unit = draft.unit();
        this.attributes = attributesOrEmpty(draft);
        this.price = draft.price();
        this.deliveryType = draft.deliveryType();
        this.deliveryPayload = draft.deliveryPayload();
        this.imageUrl = draft.imageUrl();
        this.status = draft.active() ? ProductStatus.ACTIVE : ProductStatus.ARCHIVED;
        this.updatedAt = now;
    }

    public void archive(Instant now) {
        this.status = ProductStatus.ARCHIVED;
        this.updatedAt = now;
    }

    public boolean isActive() {
        return status == ProductStatus.ACTIVE;
    }

    public boolean belongsTo(UUID ownerId) {
        return this.ownerId.equals(ownerId);
    }

    private static void requireValid(ProductDraft draft) {
        if (draft == null || isBlank(draft.name()) || isBlank(draft.category()) || isBlank(draft.unit())) {
            throw new BusinessException(ErrorCode.VALIDATION_FAILED, "name, category and unit are required");
        }
        if (draft.price() <= 0) {
            throw new BusinessException(ErrorCode.VALIDATION_FAILED, "price must be positive");
        }
    }

    private static Map<String, Object> attributesOrEmpty(ProductDraft draft) { //to prevent NullPointerException
        return draft.attributes() == null ? Map.of() : draft.attributes();
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}