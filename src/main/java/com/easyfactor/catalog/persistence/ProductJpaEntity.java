package com.easyfactor.catalog.persistence;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

import com.easyfactor.shared.persistence.AuditableJpaEntity;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
@Getter
@Setter
public class ProductJpaEntity extends AuditableJpaEntity {

    @Column(name = "owner_id", nullable = false)
    private UUID ownerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "category", nullable = false, length = 64)
    private String category;

    @Column(name = "unit", nullable = false, length = 32)
    private String unit;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "attributes", nullable = false, columnDefinition = "jsonb")
    private String attributes;

    @Column(name = "price", nullable = false)
    private long price;

    @Column(name = "status", nullable = false, length = 32)
    private String status;

    @Column(name = "digital_delivery_type", nullable = false, length = 32)
    private String digitalDeliveryType;

    @Column(name = "digital_delivery_payload", columnDefinition = "text")
    private String digitalDeliveryPayload;

    @Column(name = "image_url", columnDefinition = "text")
    private String imageUrl;
}
