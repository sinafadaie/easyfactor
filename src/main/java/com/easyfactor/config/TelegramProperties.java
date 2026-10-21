package com.easyfactor.config;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "telegram.bot")
public record TelegramProperties(
        String token,
        String username,
        String webhookSecretToken,
        String apiBaseUrl,
        Duration initDataMaxAge) {
}
