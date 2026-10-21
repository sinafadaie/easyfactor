package com.easyfactor.catalog.persistence;

import com.easyfactor.catalog.domain.Product;

import org.springframework.stereotype.Component;

/**
 * Maps Product between domain aggregate and JPA entity.
 */
@Component
public class ProductPersistenceMapper {

    public Product toDomain(ProductJpaEntity entity) {
        // TODO
        return null;
    }

    public ProductJpaEntity toEntity(Product domain) {
        // TODO
        return null;
    }
}
