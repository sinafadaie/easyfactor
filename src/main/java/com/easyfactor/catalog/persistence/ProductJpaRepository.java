package com.easyfactor.catalog.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, UUID> {

    Optional<ProductJpaEntity> findByIdAndOwnerId(UUID id, UUID ownerId);

    Page<ProductJpaEntity> findByOwnerId(UUID ownerId, Pageable pageable);

    Page<ProductJpaEntity> findByOwnerIdAndStatus(UUID ownerId, String status, Pageable pageable);

    Optional<ProductJpaEntity> findByIdAndStatus(UUID id, String status);
}
