package com.easyfactor.identity.api.dto;

import java.time.Instant;

import com.easyfactor.identity.domain.UserProfile;
import com.easyfactor.identity.service.AuthSession;

public record AuthResponse(
        String accessToken,
        String tokenType,
        Instant expiresAt,
        UserProfileResponse user) {

    public static AuthResponse from(AuthSession session, UserProfile profile) {
        // TODO
        return null;
    }
}
