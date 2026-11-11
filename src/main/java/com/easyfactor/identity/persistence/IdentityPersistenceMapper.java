package com.easyfactor.identity.persistence;

import com.easyfactor.identity.domain.Address;
import com.easyfactor.identity.domain.Customer;
import com.easyfactor.identity.domain.Owner;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        entity.getAddresses().clear();                                                              //we are erasing all the collection, and reinserting again, is that normal?
        for (int i = 0; i < domain.getAddresses().size(); i++) {                                    //It's the standard pattern for value-object children:
            entity.getAddresses().add(toEntityAddress(domain.getAddresses().get(i), entity));       //lines that have no meaningful life of their own (DDD)
        }                                                                                           //but we should generate new id each time because Hibernate could make mistake
        return entity;
    }

    public AddressJpaEntity toEntityAddress(Address address, CustomerJpaEntity entity) {
        AddressJpaEntity jpa = new AddressJpaEntity();
        jpa.setId(UUID.randomUUID());                   //new id for Hibernate
        jpa.setLabel(address.label());
        jpa.setLine1(address.line1());
        jpa.setLine2(address.line2());
        jpa.setCity(address.city());
        jpa.setState(address.state());
        jpa.setPostalCode(address.postalCode());
        jpa.setCountry(address.country());
        jpa.setCustomer(entity);
        return jpa;
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
