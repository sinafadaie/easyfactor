package com.easyfactor.identity.persistence;

import com.easyfactor.identity.domain.Customer;
import com.easyfactor.identity.domain.Owner;

import org.springframework.stereotype.Component;

/**
 * Maps Owner/Customer between domain aggregates and JPA entities.
 */
@Component
public class IdentityPersistenceMapper {

    public Owner toDomain(OwnerJpaEntity entity) {
        return new Owner(
                entity.getId(),
                entity.getTelegramUserId(),
                entity.getTelegramUsername(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getLanguageCode(),
                entity.getShopName(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }

    public OwnerJpaEntity toEntity(Owner domain) {
        return applyTo(new OwnerJpaEntity(), domain);
    }

    public OwnerJpaEntity applyTo(OwnerJpaEntity entity, Owner domain) {
        entity.setId(domain.getId());
        entity.setTelegramUserId(domain.getTelegramUserId());
        entity.setTelegramUsername(domain.getTelegramUsername());
        entity.setFirstName(domain.getFirstName());
        entity.setLastName(domain.getLastName());
        entity.setLanguageCode(domain.getLanguageCode());
        entity.setShopName(domain.getShopName());
        return entity;
    }

    public Customer toDomain(CustomerJpaEntity entity) {
        // TODO
        return null;
    }

    public CustomerJpaEntity toEntity(Customer domain) {
        // TODO
        return null;
    }
}
