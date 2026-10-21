package com.easyfactor.identity.api.dto;

import java.util.UUID;

import com.easyfactor.identity.domain.UserProfile;
import com.easyfactor.identity.domain.UserRole;

public record UserProfileResponse(
        UUID id,
        Long telegramUserId,
        UserRole role,
        String displayName,
        String username,
        String shopName) {

    public static UserProfileResponse from(UserProfile profile) {
        // TODO
        return null;
    }
}
