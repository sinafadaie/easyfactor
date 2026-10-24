package com.easyfactor.catalog.persistence;

import java.util.Map;

import com.easyfactor.catalog.domain.DigitalDeliveryType;
import com.easyfactor.catalog.domain.Product;
import com.easyfactor.catalog.domain.ProductStatus;

import tools.jackson.databind.json.JsonMapper;

import org.springframework.stereotype.Component;

/**
 * Maps Product between domain aggregate and JPA entity.
 *  The mapping is not mechanical.
 *  This mapper does real work that MapStruct would need custom config for anyway
 */

/*
this is DB boundary
it is different from controller classes that we used @Mapper
*/

@Component
public class ProductPersistenceMapper {

    private final JsonMapper jsonMapper;

    public ProductPersistenceMapper(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    public Product toDomain(ProductJpaEntity entity) {
        return new Product(
                entity.getId(),
                entity.getOwnerId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCategory(),
                entity.getUnit(),
                parseAttributes(entity.getAttributes()),
                entity.getPrice(),
                ProductStatus.valueOf(entity.getStatus()),
                DigitalDeliveryType.valueOf(entity.getDigitalDeliveryType()),
                entity.getDigitalDeliveryPayload(),
                entity.getImageUrl(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }

    public ProductJpaEntity toEntity(Product domain) {
        ProductJpaEntity entity = new ProductJpaEntity();
        return applyTo(entity, domain);
    }

    /**
     * Copies domain fields onto an already-managed entity so JPA audit
     * fields and the optimistic-lock version stay untouched.
     */
    public ProductJpaEntity applyTo(ProductJpaEntity entity, Product domain) {
        entity.setOwnerId(domain.getOwnerId());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        entity.setCategory(domain.getCategory());
        entity.setUnit(domain.getUnit());
        entity.setAttributes(toJson(domain.getAttributes()));
        entity.setPrice(domain.getPrice());
        entity.setStatus(domain.getStatus().name());
        entity.setDigitalDeliveryType(domain.getDeliveryType().name());
        entity.setDigitalDeliveryPayload(domain.getDeliveryPayload());
        entity.setImageUrl(domain.getImageUrl());
        return entity;
    }

    @SuppressWarnings("unchecked")      //@SuppressWarnings("unchecked") tells the Java compiler:
                                        //"I know about this warning, don't show it to me."
                                        //The warning source — generic type erasure
    private Map<String, Object> parseAttributes(String json) {
        try {
            return (Map<String, Object>) jsonMapper.readValue(json, Map.class);
        } catch (Exception e) {
            throw new IllegalStateException("invalid attributes json: " + json, e);
        }
    }

    private String toJson(Map<String, Object> attributes) {
        try {
            return jsonMapper.writeValueAsString(attributes);
        } catch (Exception e) {
            throw new IllegalStateException("cannot serialize attributes", e);
        }
    }
}
