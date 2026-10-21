package com.easyfactor.identity.service;

import java.time.Clock;

import com.easyfactor.identity.domain.CustomerRepository;
import com.easyfactor.identity.domain.OwnerRepository;
import com.easyfactor.identity.domain.UserProfile;
import com.easyfactor.security.AuthenticatedUser;
import com.easyfactor.security.JwtService;
import com.easyfactor.telegram.service.TelegramInitDataVerifier;

import org.springframework.stereotype.Service;

/**
 * Verifies Telegram initData, resolves/provisions the account and issues a role-scoped JWT.
 */
@Service
public class AuthService {

    private final TelegramInitDataVerifier initDataVerifier;
    private final OwnerRepository ownerRepository;
    private final CustomerRepository customerRepository;
    private final JwtService jwtService;
    private final Clock clock;

    public AuthService(TelegramInitDataVerifier initDataVerifier, OwnerRepository ownerRepository,
            CustomerRepository customerRepository, JwtService jwtService, Clock clock) {
        this.initDataVerifier = initDataVerifier;
        this.ownerRepository = ownerRepository;
        this.customerRepository = customerRepository;
        this.jwtService = jwtService;
        this.clock = clock;
    }

    public AuthSession authenticateOwner(String initData) {
        // TODO: verify initData, find existing Owner, issue ROLE_OWNER token
        return null;
    }

    public AuthSession authenticateCustomer(String initData) {
        // TODO: verify initData, find-or-create Customer, issue ROLE_CUSTOMER token
        return null;
    }

    public UserProfile getProfile(AuthenticatedUser user) {
        // TODO
        return null;
    }
}
