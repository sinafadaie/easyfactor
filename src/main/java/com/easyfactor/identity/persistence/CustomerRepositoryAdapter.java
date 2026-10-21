package com.easyfactor.identity.persistence;

import java.util.Optional;
import java.util.UUID;

import com.easyfactor.identity.domain.Customer;
import com.easyfactor.identity.domain.CustomerRepository;

import org.springframework.stereotype.Repository;

/**
 * Adapter implementing the Customer persistence port over Spring Data JPA.
 */
@Repository
public class CustomerRepositoryAdapter implements CustomerRepository {

    private final CustomerJpaRepository jpaRepository;
    private final IdentityPersistenceMapper mapper;

    public CustomerRepositoryAdapter(CustomerJpaRepository jpaRepository, IdentityPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        // TODO
        return Optional.empty();
    }

    @Override
    public Optional<Customer> findByTelegramUserId(Long telegramUserId) {
        // TODO
        return Optional.empty();
    }

    @Override
    public Customer save(Customer customer) {
        // TODO
        return null;
    }
}
