package com.easyfactor.catalog.persistence;

import java.util.Optional;
import java.util.UUID;

import com.easyfactor.catalog.domain.Product;
import com.easyfactor.catalog.domain.ProductRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * Adapter implementing the Product persistence port over Spring Data JPA.
 */
@Repository
public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductJpaRepository jpaRepository;
    private final ProductPersistenceMapper mapper;

    public ProductRepositoryAdapter(ProductJpaRepository jpaRepository, ProductPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Product save(Product product) {
        // TODO
        return null;
    }

    @Override
    public Optional<Product> findById(UUID id) {
        // TODO
        return Optional.empty();
    }

    @Override
    public Optional<Product> findByIdAndOwnerId(UUID id, UUID ownerId) {
        // TODO
        return Optional.empty();
    }

    @Override
    public Page<Product> findByOwnerId(UUID ownerId, Pageable pageable) {
        // TODO
        return null;
    }

    @Override
    public Page<Product> findActiveByOwnerId(UUID ownerId, Pageable pageable) {
        // TODO
        return null;
    }

    @Override
    public Optional<Product> findActiveById(UUID id) {
        // TODO
        return Optional.empty();
    }
}
