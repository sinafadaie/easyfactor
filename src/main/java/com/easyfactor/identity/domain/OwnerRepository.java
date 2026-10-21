package com.easyfactor.identity.domain;

import java.util.Optional;
import java.util.UUID;

/**
 * Owner persistence port (hexagonal boundary).
 */
public interface OwnerRepository {

    Optional<Owner> findById(UUID id);

    Optional<Owner> findByTelegramUserId(Long telegramUserId);

    Owner save(Owner owner);

    boolean existsByTelegramUserId(Long telegramUserId);
}
