package com.easyfactor.invoice.domain;

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

    Optional<Invoice> findByPublicToken(UUID publicToken);

    Page<Invoice> findByOwnerId(UUID ownerId, Pageable pageable);

    Page<Invoice> findByOwnerIdAndStatus(UUID ownerId, InvoiceStatus status, Pageable pageable);
}
