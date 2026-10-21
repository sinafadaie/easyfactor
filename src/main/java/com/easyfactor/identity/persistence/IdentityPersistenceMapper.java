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
        // TODO
        return null;
    }

    public OwnerJpaEntity toEntity(Owner domain) {
        // TODO
        return null;
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
