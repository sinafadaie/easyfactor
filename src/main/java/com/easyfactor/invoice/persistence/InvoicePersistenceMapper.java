package com.easyfactor.invoice.persistence;

import com.easyfactor.invoice.domain.Invoice;
import com.easyfactor.invoice.domain.InvoiceItem;
import com.easyfactor.invoice.domain.InvoiceStatus;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Maps Invoice/InvoiceItem between domain aggregates and JPA entities.
 */
@Component
public class InvoicePersistenceMapper {

    public Invoice toDomain(InvoiceJpaEntity entity) {
        return new Invoice(
                entity.getId(),
                entity.getInvoiceNumber(),
                entity.getOwnerId(),
                entity.getCustomerPhoneNumber(),
                entity.getCustomerFirstName(),
                entity.getCustomerLastName(),
                entity.getCustomerAddress(),
                entity.getCustomerPostalCode(),
                entity.getCustomerId(),
                entity.getItems().stream().map(this::toDomainItem).toList(),    //to list return immutable list, so we can not add any new item in future!
                entity.getTotal(),
                entity.getNotes(),
                InvoiceStatus.valueOf(entity.getStatus()),
                entity.getPublicToken(),
                entity.getCancelReason(),
                entity.getCreatedAt(),
                entity.getPaidAt(),
                entity.getDispatchedAt(),
                entity.getUpdatedAt());
    }

    public InvoiceJpaEntity toEntity(Invoice domain) {
        InvoiceJpaEntity entity = applyTo(new InvoiceJpaEntity(), domain);
        entity.setId(domain.getId());
        return entity;
    }

    /**
     * Copies domain state onto an already-managed entity so Hibernate updates the
     * existing row and its items collection (orphanRemoval drops removed lines).
     */
    public InvoiceJpaEntity applyTo(InvoiceJpaEntity entity, Invoice domain) {
        //entity.setId(domain.getId());                         //applyTo should be id free
        entity.setInvoiceNumber(domain.getInvoiceNumber());
        entity.setOwnerId(domain.getOwnerId());
        entity.setCustomerPhoneNumber(domain.getCustomerPhoneNumber());
        entity.setCustomerFirstName(domain.getCustomerFirstName());
        entity.setCustomerLastName(domain.getCustomerLastName());
        entity.setCustomerAddress(domain.getCustomerAddress());
        entity.setCustomerPostalCode(domain.getCustomerPostalCode());
        entity.setCustomerId(domain.getCustomerId());
        entity.setStatus(domain.getStatus().name());
        entity.setTotal(domain.getTotal());
        entity.setNotes(domain.getNotes());
        entity.setPublicToken(domain.getPublicToken());
        entity.setCancelReason(domain.getCancelReason());
        entity.setPaidAt(domain.getPaidAt());
        entity.setDispatchedAt(domain.getDispatchedAt());
        entity.getItems().clear();                                                      //we should use old collection
        for (int i = 0; i < domain.getItems().size(); i++) {                            //if replaces the persistent collection on a managed entity by new one—
            entity.getItems().add(toEntityItem(domain.getItems().get(i), entity, i));   //Hibernate throws its classic "collection no longer referenced by the owning entity instance"
        }
        return entity;
    }

    InvoiceItem toDomainItem(InvoiceItemJpaEntity entity) {
        return new InvoiceItem(
                entity.getId(),
                entity.getProductId(),
                entity.getDescription(),
                entity.getQuantity(),
                entity.getUnitPrice(),
                entity.getLineTotal());
    }

    InvoiceItemJpaEntity toEntityItem(InvoiceItem domain, InvoiceJpaEntity invoice, int position) {
        InvoiceItemJpaEntity entity = new InvoiceItemJpaEntity();
        entity.setId(UUID.randomUUID());                //new id for Hibernate
        entity.setInvoice(invoice);
        entity.setProductId(domain.getProductId());
        entity.setDescription(domain.getDescription());
        entity.setQuantity(domain.getQuantity());
        entity.setUnitPrice(domain.getUnitPrice());
        entity.setLineTotal(domain.getLineTotal());
        entity.setPosition(position);
        return entity;
    }
}
