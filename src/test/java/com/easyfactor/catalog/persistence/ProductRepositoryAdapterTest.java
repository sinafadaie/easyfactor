package com.easyfactor.catalog.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import com.easyfactor.catalog.domain.DigitalDeliveryType;
import com.easyfactor.catalog.domain.Product;
import com.easyfactor.catalog.domain.ProductDraft;
import com.easyfactor.catalog.domain.ProductStatus;
import com.easyfactor.identity.persistence.OwnerJpaEntity;
import com.easyfactor.identity.persistence.OwnerJpaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
class ProductRepositoryAdapterTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17");

    @Autowired
    private ProductRepositoryAdapter repository;

    @Autowired
    private OwnerJpaRepository ownerJpaRepository;

    private UUID ownerId;

    @BeforeEach
    void createOwner() {
        OwnerJpaEntity owner = new OwnerJpaEntity();
        owner.setTelegramUserId(ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE));
        ownerJpaRepository.save(owner);
        ownerId = owner.getId();
    }

    private static ProductDraft draft(boolean active) {
        return new ProductDraft("Nike Air", "Running shoe", "shoe", "pair",
                Map.of("size", 42, "color", "black"), 85_000_000L,
                DigitalDeliveryType.NONE, null, "https://img.example/shoe.jpg", active);
    }

    @Test
    void savesAndLoadsProductWithJsonAttributes() {
        Product saved = repository.save(Product.create(ownerId, draft(true), Instant.now()));

        Optional<Product> loaded = repository.findById(saved.getId());

        assertThat(loaded).isPresent();
        Product product = loaded.get();
        assertThat(product.getName()).isEqualTo("Nike Air");
        assertThat(product.getCategory()).isEqualTo("shoe");
        assertThat(product.getUnit()).isEqualTo("pair");
        assertThat(product.getPrice()).isEqualTo(85_000_000L);
        assertThat(product.getAttributes()).containsEntry("size", 42).containsEntry("color", "black");
        assertThat(product.getStatus()).isEqualTo(ProductStatus.ACTIVE);
        assertThat(product.getCreatedAt()).isNotNull();
        assertThat(product.getUpdatedAt()).isNotNull();
    }

    @Test
    void updatesExistingProductWithoutVersionConflict() {
        Product saved = repository.save(Product.create(ownerId, draft(true), Instant.now()));

        saved.update(draft(false), Instant.now().plusSeconds(60));
        Product updated = repository.save(saved);

        assertThat(updated.getId()).isEqualTo(saved.getId());
        assertThat(updated.getStatus()).isEqualTo(ProductStatus.ARCHIVED);
        assertThat(repository.findById(saved.getId()))
                .get().extracting(Product::getStatus).isEqualTo(ProductStatus.ARCHIVED);
    }

    @Test
    void findActiveFiltersOutArchived() {
        repository.save(Product.create(ownerId, draft(true), Instant.now()));
        repository.save(Product.create(ownerId, draft(false), Instant.now()));

        Page<Product> active = repository.findActiveByOwnerId(ownerId, Pageable.unpaged());
        Page<Product> all = repository.findByOwnerId(ownerId, Pageable.unpaged());

        assertThat(active.getTotalElements()).isEqualTo(1);
        assertThat(all.getTotalElements()).isEqualTo(2);
    }

    @Test
    void findByIdAndOwnerIdEnforcesOwnership() {
        Product saved = repository.save(Product.create(ownerId, draft(true), Instant.now()));

        assertThat(repository.findByIdAndOwnerId(saved.getId(), ownerId)).isPresent();
        assertThat(repository.findByIdAndOwnerId(saved.getId(), UUID.randomUUID())).isEmpty();
    }
}
