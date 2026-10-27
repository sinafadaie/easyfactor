package com.easyfactor.catalog.service;

import java.time.Instant;
import java.util.UUID;

import com.easyfactor.catalog.domain.Product;
import com.easyfactor.catalog.domain.ProductDraft;
import com.easyfactor.catalog.domain.ProductRepository;
import com.easyfactor.shared.error.NotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Owner product CRUD and public catalog browsing.
 *
 * if the product exists but belongs to someone else,
 * we throw the same 404 &mdash; not a 403 "forbidden." Why?
 * A 403 would tell an attacker "this UUID exists
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(UUID ownerId, ProductDraft draft) {                //Why Instant.now() is passed in instead of the domain calling it itself: the domain stays pure — no clock dependency. In a test you can call Product.create(ownerId, draft,fixedInstant)
        Product product = Product.create(ownerId, draft, Instant.now());     //and assert createdAt equals exactly what you passed. If the domain grabbed Instant.now() internally, every test would need fuzzy time comparisons. This technique is
        return productRepository.save(product);                              //called DEPENDENCY INJECTION of time.
    }

    public Product update(UUID ownerId, UUID productId, ProductDraft draft) {
        Product product = getOwned(ownerId, productId);
        product.update(draft, Instant.now());
        return productRepository.save(product);
    }

    public Product archive(UUID ownerId, UUID productId) {  //soft delete
        Product product = getOwned(ownerId, productId);
        product.archive(Instant.now());
        return productRepository.save(product);
    }

    public Product getOwned(UUID ownerId, UUID productId) {
        return productRepository.findByIdAndOwnerId(productId, ownerId)
                .orElseThrow(() -> new NotFoundException("product", productId));
    }

    public Page<Product> listOwned(UUID ownerId, Pageable pageable) {
        return productRepository.findByOwnerId(ownerId, pageable);
    }
}
