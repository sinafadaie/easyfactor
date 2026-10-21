package com.easyfactor.security;

import java.time.Clock;
import java.time.Instant;

import com.easyfactor.config.JwtProperties;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final JwtProperties properties;
    private final Clock clock;

    public JwtService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder, JwtProperties properties, Clock clock) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
        this.properties = properties;
        this.clock = clock;
    }

    public IssuedToken issue(AuthenticatedUser user) {
        Instant now = clock.instant();
        Instant expiresAt = now.plus(properties.accessTokenTtl());
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(properties.issuer())
                .subject(user.accountId().toString())
                .issuedAt(now)
                .expiresAt(expiresAt)
                .claim("telegramUserId", user.telegramUserId())
                .claim("role", user.role().name())
                .build();
        String value = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new IssuedToken(value, expiresAt);
    }

    public AuthenticatedUser parse(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        // TODO: map claims back to AuthenticatedUser (subject, telegramUserId, role, displayName)
        return null;
    }
}
