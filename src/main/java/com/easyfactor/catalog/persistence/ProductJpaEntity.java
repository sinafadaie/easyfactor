package com.easyfactor.catalog.persistence;

import java.math.BigDecimal;
import java.util.UUID;

import com.easyfactor.shared.persistence.AuditableJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class ProductJpaEntity extends AuditableJpaEntity {

    @Column(name = "owner_id", nullable = false)
    private UUID ownerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "price_amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal priceAmount;

    @Column(name = "currency", nullable = false, length = 8)
    private String currency;

    @Column(name = "status", nullable = false, length = 32)
    private String status;

    @Column(name = "digital_delivery_type", nullable = false, length = 32)
    private String digitalDeliveryType;

    @Column(name = "digital_delivery_payload", columnDefinition = "text")
    private String digitalDeliveryPayload;

    @Column(name = "image_url", columnDefinition = "text")
    private String imageUrl;

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(BigDecimal priceAmount) {
        this.priceAmount = priceAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDigitalDeliveryType() {
        return digitalDeliveryType;
    }

    public void setDigitalDeliveryType(String digitalDeliveryType) {
        this.digitalDeliveryType = digitalDeliveryType;
    }

    public String getDigitalDeliveryPayload() {
        return digitalDeliveryPayload;
    }

    public void setDigitalDeliveryPayload(String digitalDeliveryPayload) {
        this.digitalDeliveryPayload = digitalDeliveryPayload;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
