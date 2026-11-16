package com.easyfactor.identity.persistence;

import com.easyfactor.identity.domain.Address;
import com.easyfactor.identity.domain.Customer;
import com.easyfactor.identity.domain.Owner;

import com.easyfactor.shared.persistence.AuditableJpaEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Maps Owner/Customer between domain aggregates and JPA entities.
 */
@Component
public class IdentityPersistenceMapper {

    public Owner toDomain(OwnerJpaEntity entity) {  //when we need new obj
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

    public OwnerJpaEntity toEntity(Owner domain) {         //when we have old obj
        OwnerJpaEntity entity = applyTo(new OwnerJpaEntity(), domain);
        entity.setId(domain.getId());
        return entity;
    }

    public OwnerJpaEntity applyTo(OwnerJpaEntity entity, Owner domain) {    //when we have old obj
        //entity.setId(domain.getId());                                                      //so applyTo should be id free
        entity.setTelegramUserId(domain.getTelegramUserId());
        entity.setTelegramUsername(domain.getTelegramUsername());
        entity.setFirstName(domain.getFirstName());
        entity.setLastName(domain.getLastName());
        entity.setLanguageCode(domain.getLanguageCode());
        entity.setShopName(domain.getShopName());
        return entity;
    }

    //-------------------------

    public Customer toDomain(CustomerJpaEntity entity) {
        return new Customer(
                entity.getId(),
                entity.getTelegramUserId(),
                entity.getTelegramUsername(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPhoneNumber(),
                entity.getLanguageCode(),
                new ArrayList<>(entity.getAddresses().stream().map(this::toDomainAddress).toList()),  //return mutable list! because we have add()
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getOwnerId()
        );
    }

    public CustomerJpaEntity toEntity(Customer domain) {
        CustomerJpaEntity entity = applyTo(new CustomerJpaEntity(), domain);
        entity.setId(domain.getId());
        return entity;
    }

    public CustomerJpaEntity applyTo(CustomerJpaEntity entity, Customer domain) {
        //entity.setId(domain.getId());
        entity.setFirstName(domain.getFirstName());
        entity.setLastName(domain.getLastName());
        entity.setLanguageCode(domain.getLanguageCode());
        entity.setPhoneNumber(domain.getPhoneNumber());
        entity.setTelegramUserId(domain.getTelegramUserId());
        entity.setTelegramUsername(domain.getTelegramUsername());
        entity.setOwnerId(domain.getOwnerId());

        Map<UUID, AddressJpaEntity> existing = entity.getAddresses().stream()               //Map for finding based on id!
                .collect(Collectors.toMap(AddressJpaEntity::getId, Function.identity()));

        List<AddressJpaEntity> merged = new ArrayList<>();
        for (Address address : domain.getAddresses()) {
            AddressJpaEntity jpa = existing.remove(address.id());   // "check off": found = update, null = new
            if (jpa == null) {
                jpa = new AddressJpaEntity();
                jpa.setId(address.id());
                jpa.setCustomer(entity);
            }
            jpa.setLabel(address.label());
            jpa.setLine1(address.line1());
            jpa.setLine2(address.line2());
            jpa.setCity(address.city());
            jpa.setState(address.state());
            jpa.setPostalCode(address.postalCode());
            jpa.setCountry(address.country());
            merged.add(jpa);
        }

        entity.getAddresses().clear();
        entity.getAddresses().addAll(merged);
        return entity;
    }

    public Address toDomainAddress(AddressJpaEntity entity) {   //due to DDD principle
        return new Address(                                              //we don't need set customer here!
                entity.getId(),                                          //read Address class
                entity.getLabel(),
                entity.getLine1(),
                entity.getLine2(),
                entity.getCity(),
                entity.getState(),
                entity.getPostalCode(),
                entity.getCountry()
        );
    }
}
