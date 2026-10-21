package com.easyfactor.identity.domain;

import java.util.Optional;
import java.util.UUID;

/**
 * Customer persistence port (hexagonal boundary).
 */
public interface CustomerRepository {

    Optional<Customer> findById(UUID id);

    Optional<Customer> findByTelegramUserId(Long telegramUserId);

    Customer save(Customer customer);
}
