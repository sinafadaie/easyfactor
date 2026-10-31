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
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Owner> findByTelegramUserId(Long telegramUserId) {
        return jpaRepository.findByTelegramUserId(telegramUserId).map(mapper::toDomain);
    }

    @Override
    public Owner save(Owner owner) {
        OwnerJpaEntity entity = jpaRepository.findById(owner.getId())
                .map(existing -> mapper.applyTo(existing, owner))
                .orElseGet(() -> mapper.toEntity(owner));
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public boolean existsByTelegramUserId(Long telegramUserId) {
        return jpaRepository.existsByTelegramUserId(telegramUserId);
    }
}
