package com.easyfactor.identity.api.dto;

import java.time.Instant;

import com.easyfactor.identity.service.AuthSession;

public record AuthResponse(
        String accessToken,
        String tokenType,
        Instant expiresAt,
        UserProfileResponse user) {

    public static AuthResponse from(AuthSession session) {
        return new AuthResponse(
                session.accessToken(),
                session.tokenType(),
                session.expiresAt(),
                UserProfileResponse.from(session.profile()));
    }
}
