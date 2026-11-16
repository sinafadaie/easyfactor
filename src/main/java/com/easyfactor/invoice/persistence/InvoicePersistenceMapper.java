package com.easyfactor.invoice.persistence;

import com.easyfactor.invoice.domain.Invoice;
import com.easyfactor.invoice.domain.InvoiceItem;
import com.easyfactor.invoice.domain.InvoiceStatus;

import com.easyfactor.shared.persistence.AuditableJpaEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

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

        Map<UUID, InvoiceItemJpaEntity> existing = entity.getItems()
                .stream()
                        .collect(Collectors.toMap(InvoiceItemJpaEntity::getId, Function.identity()));

        ArrayList<InvoiceItemJpaEntity> merge = new ArrayList<>();

        Integer maxPosition = entity.getItems()
                .stream()
                .map(InvoiceItemJpaEntity::getPosition)
                .max(Integer::compareTo)
                .orElse(0);

        boolean isNewItem ;
        for (var item : domain.getItems()){
            isNewItem = false;
            InvoiceItemJpaEntity itemJpa = existing.remove(item.getId());   //if item is new, itemJpa will be null
            if(itemJpa == null){
                itemJpa = new InvoiceItemJpaEntity();
                itemJpa.setId(item.getId());
                itemJpa.setInvoice(entity);
                isNewItem = true;
            }
            itemJpa.setProductId(item.getProductId());
            itemJpa.setDescription(item.getDescription());
            itemJpa.setQuantity(item.getQuantity());
            itemJpa.setUnitPrice(item.getUnitPrice());
            itemJpa.setLineTotal(item.getLineTotal());
            if (isNewItem)
                itemJpa.setPosition(++maxPosition);     //else the position is correct right now!


            merge.add(itemJpa);
        }

        entity.getItems().clear();
        entity.getItems().addAll(merge);
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
}
