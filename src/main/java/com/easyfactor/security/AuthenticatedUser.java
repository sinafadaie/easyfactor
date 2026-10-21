package com.easyfactor.security;

import java.util.UUID;

import com.easyfactor.identity.domain.UserRole;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Authenticated principal stored in the {@link org.springframework.security.core.context.SecurityContext}.
 */
public record AuthenticatedUser(
        UUID accountId,
        Long telegramUserId,
        UserRole role,
        String displayName) {

    public GrantedAuthority authority() {
        return new SimpleGrantedAuthority("ROLE_" + role.name());
    }
}
