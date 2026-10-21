package com.easyfactor.identity.service;

import java.time.Instant;

import com.easyfactor.identity.domain.UserProfile;
import com.easyfactor.security.AuthenticatedUser;

/**
 * Service-level auth result.
 */
public record AuthSession(
        String accessToken,
        String tokenType,
        Instant expiresAt,
        AuthenticatedUser principal,
        UserProfile profile) {
}
