package com.easyfactor.identity.persistence;

import java.util.Optional;
import java.util.UUID;

import com.easyfactor.identity.domain.Customer;
import com.easyfactor.identity.domain.CustomerRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public Optional<Customer> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> findByTelegramUserId(Long telegramUserId) {
        return jpaRepository.findByTelegramUserId(telegramUserId)
                .map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> findByOwnerIdAndPhoneNumber(UUID ownerId, String phoneNumber) {
        return jpaRepository.findByOwnerIdAndPhoneNumber(ownerId, phoneNumber).map(mapper::toDomain);
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
        CustomerJpaEntity jpaEntity = jpaRepository.findById(customer.getId())
                .map(existing -> mapper.applyTo(existing, customer))    //if the customer is not new
                .orElseGet(()->mapper.toEntity(customer));  //difference of  orElseGet and orElse?

        return mapper.toDomain(jpaRepository.save(jpaEntity));
    }
}
