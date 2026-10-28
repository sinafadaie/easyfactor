package com.easyfactor.invoice.persistence;

import java.util.Optional;
import java.util.UUID;

import com.easyfactor.invoice.domain.Invoice;
import com.easyfactor.invoice.domain.InvoiceRepository;
import com.easyfactor.invoice.domain.InvoiceStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Adapter implementing the Invoice persistence "PORT" over Spring Data JPA.
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
    @Transactional
    public Invoice save(Invoice invoice) {
        InvoiceJpaEntity entity = jpaRepository.findById(invoice.getId())
                .map(existing -> mapper.applyTo(existing, invoice))
                .orElseGet(() -> mapper.toEntity(invoice));
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Invoice> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Invoice> findByInvoiceNumber(String invoiceNumber) {
        return jpaRepository.findByInvoiceNumber(invoiceNumber).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Invoice> findByPublicToken(UUID publicToken) {
        return jpaRepository.findByPublicToken(publicToken).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Invoice> findByOwnerId(UUID ownerId, Pageable pageable) {
        return jpaRepository.findByOwnerId(ownerId, pageable).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Invoice> findByOwnerIdAndStatus(UUID ownerId, InvoiceStatus status, Pageable pageable) {
        return jpaRepository.findByOwnerIdAndStatus(ownerId, status.name(), pageable).map(mapper::toDomain);
    }
}
