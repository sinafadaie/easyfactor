package com.easyfactor.identity.domain;

import java.util.UUID;

/**
 * Customer address value object (part of the Customer aggregate).
 */

//DDD
//In the domain model, Address does not need a Customer reference because it is not treated as an independent object.
//It exists as part of Customer, through Customer -> List<Address>.
//If you already have an address from that list, you already know which customer it belongs to.
//it means you never need address obj separately!
//In the JPA/database model, AddressJpaEntity does need a CustomerJpaEntity reference
//because each address is stored as a separate row in the database,
//and the database needs a foreign key like customer_id to know which customer owns that row.
//So AddressJpaEntity has customer field
//JPA → Domain: no need to set customer on Address
//Domain → JPA: you must set jpaAddress.setCustomer(customerEntity)

public record Address(
        UUID id,
        String label,
        String line1,
        String line2,
        String city,
        String state,
        String postalCode,
        String country) {
}
