package com.easyfactor.invoice.persistence;

import com.easyfactor.invoice.domain.Invoice;
import com.easyfactor.invoice.domain.InvoiceItem;

import org.springframework.stereotype.Component;

/**
 * Maps Invoice/InvoiceItem between domain aggregates and JPA entities.
 */
@Component
public class InvoicePersistenceMapper {

    public Invoice toDomain(InvoiceJpaEntity entity) {
        // TODO
        return null;
    }

    public InvoiceJpaEntity toEntity(Invoice domain) {
        // TODO
        return null;
    }

    InvoiceItem toDomainItem(InvoiceItemJpaEntity entity) {
        // TODO
        return null;
    }

    InvoiceItemJpaEntity toEntityItem(InvoiceItem domain, InvoiceJpaEntity invoice, int position) {
        // TODO
        return null;
    }
}
