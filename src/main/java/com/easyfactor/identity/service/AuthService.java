package com.easyfactor.identity.service;

import java.time.Clock;
import java.time.Instant;

import com.easyfactor.identity.domain.Owner;
import com.easyfactor.identity.domain.OwnerRepository;
import com.easyfactor.identity.domain.UserProfile;
import com.easyfactor.identity.domain.UserRole;
import com.easyfactor.security.AuthenticatedUser;
import com.easyfactor.security.IssuedToken;
import com.easyfactor.security.JwtService;
import com.easyfactor.shared.error.NotFoundException;
import com.easyfactor.telegram.domain.TelegramUser;
import com.easyfactor.telegram.service.TelegramInitDataVerifier;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Verifies Telegram initData, resolves/provisions the account and issues a role-scoped JWT.
 * MVP: owners only — customers reach invoices via the public token link.
 */
@Service
public class AuthService {

    private final TelegramInitDataVerifier initDataVerifier;
    private final OwnerRepository ownerRepository;
    private final JwtService jwtService;
    private final Clock clock;

    public AuthService(TelegramInitDataVerifier initDataVerifier, OwnerRepository ownerRepository,
            JwtService jwtService, Clock clock) {
        this.initDataVerifier = initDataVerifier;
        this.ownerRepository = ownerRepository;
        this.jwtService = jwtService;
        this.clock = clock;
    }

    @Transactional
    public AuthSession authenticateOwner(String initData) {
        TelegramUser telegramUser = initDataVerifier.verify(initData);
        Instant now = clock.instant();
        Owner owner = ownerRepository.findByTelegramUserId(telegramUser.id())
                .map(existing -> {
                    existing.updateProfile(telegramUser, now);
                    return existing;
                })
                .orElseGet(() -> Owner.register(telegramUser, now));
        ownerRepository.save(owner);

        AuthenticatedUser principal = new AuthenticatedUser(
                owner.getId(), owner.getTelegramUserId(), UserRole.OWNER, displayNameOf(owner));
        IssuedToken token = jwtService.issue(principal);
        return new AuthSession(token.value(), "Bearer", token.expiresAt(), principal, toProfile(owner));
    }

    public UserProfile getProfile(AuthenticatedUser user) {
        Owner owner = ownerRepository.findById(user.accountId())
                .orElseThrow(() -> new NotFoundException("owner", user.accountId()));
        return toProfile(owner);
    }

    private static UserProfile toProfile(Owner owner) {
        return new UserProfile(
                owner.getId(),
                owner.getTelegramUserId(),
                UserRole.OWNER,
                owner.getFirstName(),
                owner.getTelegramUsername(),
                owner.getShopName());
    }

    private static String displayNameOf(Owner owner) {
        if (owner.getFirstName() != null && !owner.getFirstName().isBlank()) {
            return owner.getFirstName();
        }
        return owner.getTelegramUsername() != null ? owner.getTelegramUsername() : "Owner";
    }
}
