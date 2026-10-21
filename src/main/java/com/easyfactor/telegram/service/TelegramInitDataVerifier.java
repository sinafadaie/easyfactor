package com.easyfactor.telegram.service;

import java.time.Clock;

import com.easyfactor.config.TelegramProperties;
import com.easyfactor.telegram.domain.InvalidInitDataException;
import com.easyfactor.telegram.domain.TelegramUser;
import tools.jackson.databind.json.JsonMapper;

import org.springframework.stereotype.Component;

/**
 * Parses and HMAC-verifies Mini App initData and returns the Telegram user.
 * Secret = HMAC_SHA256("WebAppData", botToken); hash = HMAC_SHA256(secret, dataCheckString).
 */
@Component
public class TelegramInitDataVerifier {

    private final TelegramProperties properties;
    private final JsonMapper jsonMapper;
    private final Clock clock;

    public TelegramInitDataVerifier(TelegramProperties properties, JsonMapper jsonMapper, Clock clock) {
        this.properties = properties;
        this.jsonMapper = jsonMapper;
        this.clock = clock;
    }

    public TelegramUser verify(String rawInitData) {
        // TODO: parse query string, exclude 'hash', sort keys, build data-check-string
        // TODO: compute secret key and HMAC, constant-time compare with provided hash
        // TODO: validate auth_date within properties.initDataMaxAge()
        // TODO: parse 'user' JSON into TelegramUser
        throw new InvalidInitDataException("initData verification not implemented");
    }
}
