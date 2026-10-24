package com.easyfactor.catalog.domain;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Product persistence port (hexagonal boundary).
 *  we need this interface because of the Dependency Inversion Principle
 *  It says: the important code must not depend on the infrastructure; instead,
 *  infrastructure depends on the important code.
 */
public interface ProductRepository {

    Product save(Product product);

    Optional<Product> findById(UUID id);

    Optional<Product> findByIdAndOwnerId(UUID id, UUID ownerId);

    Page<Product> findByOwnerId(UUID ownerId, Pageable pageable);

    Page<Product> findActiveByOwnerId(UUID ownerId, Pageable pageable);

    Optional<Product> findActiveById(UUID id);
}
