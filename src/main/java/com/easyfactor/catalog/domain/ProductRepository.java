package com.easyfactor.catalog.domain;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Product persistence port (hexagonal boundary).
 */
public interface ProductRepository {

    Product save(Product product);

    Optional<Product> findById(UUID id);

    Optional<Product> findByIdAndOwnerId(UUID id, UUID ownerId);

    Page<Product> findByOwnerId(UUID ownerId, Pageable pageable);

    Page<Product> findActiveByOwnerId(UUID ownerId, Pageable pageable);

    Optional<Product> findActiveById(UUID id);
}
