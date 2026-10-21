package com.easyfactor.invoice.persistence;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import com.easyfactor.invoice.domain.Invoice;
import com.easyfactor.invoice.domain.InvoiceRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * Adapter implementing the Invoice persistence port over Spring Data JPA.
 */
@Repository
public class InvoiceRepositoryAdapter implements InvoiceRepository {

    private final InvoiceJpaRepository jpaRepository;
    private final InvoicePersistenceMapper mapper;

    public InvoiceRepositoryAdapter(InvoiceJpaRepository jpaRepository, InvoicePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Invoice save(Invoice invoice) {
        // TODO
        return null;
    }

    @Override
    public Optional<Invoice> findById(UUID id) {
        // TODO
        return Optional.empty();
    }

    @Override
    public Optional<Invoice> findByInvoiceNumber(String invoiceNumber) {
        // TODO
        return Optional.empty();
    }

    @Override
    public Page<Invoice> findByOwnerId(UUID ownerId, Pageable pageable) {
        // TODO
        return null;
    }

    @Override
    public Page<Invoice> findByCustomerId(UUID customerId, Pageable pageable) {
        // TODO
        return null;
    }

    @Override
    public Page<Invoice> findDueForExpiration(LocalDate dueDate, Pageable pageable) {
        // TODO
        return null;
    }
}
