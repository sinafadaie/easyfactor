package com.easyfactor.identity.domain;

import java.util.UUID;

/**
 * API-neutral profile view of an account.
 */
public record UserProfile(
        UUID id,
        Long telegramUserId,
        UserRole role,
        String displayName,
        String username,
        String shopName) {
}
