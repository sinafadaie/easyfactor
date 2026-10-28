package com.easyfactor.invoice.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceJpaRepository extends JpaRepository<InvoiceJpaEntity, UUID> {

    Optional<InvoiceJpaEntity> findByInvoiceNumber(String invoiceNumber);

    Optional<InvoiceJpaEntity> findByPublicToken(UUID publicToken);

    @EntityGraph(attributePaths = "items")
    Page<InvoiceJpaEntity> findByOwnerId(UUID ownerId, Pageable pageable);

    @EntityGraph(attributePaths = "items")
    Page<InvoiceJpaEntity> findByOwnerIdAndStatus(UUID ownerId, String status, Pageable pageable);
}
