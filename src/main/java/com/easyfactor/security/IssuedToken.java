package com.easyfactor.security;

import java.time.Instant;

/**
 * JWT issued by the auth flow together with its expiry.
 */
public record IssuedToken(
        String value,
        Instant expiresAt) {
}
