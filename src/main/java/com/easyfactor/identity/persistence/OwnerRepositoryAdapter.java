package com.easyfactor.identity.persistence;

import java.util.Optional;
import java.util.UUID;

import com.easyfactor.identity.domain.Owner;
import com.easyfactor.identity.domain.OwnerRepository;

import org.springframework.stereotype.Repository;

/**
 * Adapter implementing the Owner persistence port over Spring Data JPA.
 */
@Repository
public class OwnerRepositoryAdapter implements OwnerRepository {

    private final OwnerJpaRepository jpaRepository;
    private final IdentityPersistenceMapper mapper;

    public OwnerRepositoryAdapter(OwnerJpaRepository jpaRepository, IdentityPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Owner> findById(UUID id) {
        // TODO
        return Optional.empty();
    }

    @Override
    public Optional<Owner> findByTelegramUserId(Long telegramUserId) {
        // TODO
        return Optional.empty();
    }

    @Override
    public Owner save(Owner owner) {
        // TODO
        return null;
    }

    @Override
    public boolean existsByTelegramUserId(Long telegramUserId) {
        // TODO
        return false;
    }
}
