package com.easyfactor.invoice.persistence;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceJpaRepository extends JpaRepository<InvoiceJpaEntity, UUID> {

    Optional<InvoiceJpaEntity> findByInvoiceNumber(String invoiceNumber);

    Page<InvoiceJpaEntity> findByOwnerId(UUID ownerId, Pageable pageable);

    Page<InvoiceJpaEntity> findByCustomerId(UUID customerId, Pageable pageable);

    @EntityGraph(attributePaths = "items")
    Page<InvoiceJpaEntity> findByStatusInAndDueDateBefore(String[] statuses, LocalDate dueDate, Pageable pageable);
}
