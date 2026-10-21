package com.easyfactor.catalog.service;

import java.util.UUID;

import com.easyfactor.catalog.domain.Product;
import com.easyfactor.catalog.domain.ProductDraft;
import com.easyfactor.catalog.domain.ProductRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Owner product CRUD and public catalog browsing.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(UUID ownerId, ProductDraft draft) {
        // TODO
        return null;
    }

    public Product update(UUID ownerId, UUID productId, ProductDraft draft) {
        // TODO
        return null;
    }

    public Product archive(UUID ownerId, UUID productId) {
        // TODO
        return null;
    }

    public Product getOwned(UUID ownerId, UUID productId) {
        // TODO
        return null;
    }

    public Page<Product> listOwned(UUID ownerId, Pageable pageable) {
        // TODO
        return null;
    }

    public Page<Product> listPublic(UUID ownerId, Pageable pageable) {
        // TODO
        return null;
    }

    public Product getPublic(UUID productId) {
        // TODO
        return null;
    }
}
