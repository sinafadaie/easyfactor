package com.easyfactor.invoice.domain;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Invoice persistence port (hexagonal boundary).
 */
public interface InvoiceRepository {

    Invoice save(Invoice invoice);

    Optional<Invoice> findById(UUID id);

    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);

    Page<Invoice> findByOwnerId(UUID ownerId, Pageable pageable);

    Page<Invoice> findByCustomerId(UUID customerId, Pageable pageable);

    Page<Invoice> findDueForExpiration(LocalDate dueDate, Pageable pageable);
}
