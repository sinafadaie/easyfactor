package com.easyfactor.security;

import java.time.Clock;
import java.time.Instant;
import java.util.UUID;

import com.easyfactor.config.JwtProperties;
import com.easyfactor.identity.domain.UserRole;

import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwsHeader;
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

    public IssuedToken issue(AuthenticatedUser user) {          //create token for AuthenticatedUser!
        Instant now = clock.instant();
        Instant expiresAt = now.plus(properties.accessTokenTtl());
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(properties.issuer())
                .subject(user.accountId().toString())
                .issuedAt(now)
                .expiresAt(expiresAt)
                .claim("telegramUserId", user.telegramUserId())
                .claim("role", user.role().name())
                .claim("displayName", user.displayName())
                .build();
        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();
        String value = jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
        return new IssuedToken(value, expiresAt);
    }

    public AuthenticatedUser parse(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        UUID accountId = UUID.fromString(jwt.getSubject());
        String telegramUserIdClaim = jwt.getClaimAsString("telegramUserId");
        Long telegramUserId = telegramUserIdClaim == null ? null : Long.valueOf(telegramUserIdClaim);
        UserRole role = UserRole.valueOf(jwt.getClaimAsString("role"));
        String displayName = jwt.getClaimAsString("displayName");
        return new AuthenticatedUser(accountId, telegramUserId, role, displayName);
    }
}
