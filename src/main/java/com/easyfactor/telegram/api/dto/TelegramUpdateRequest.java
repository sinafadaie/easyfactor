package com.easyfactor.telegram.api.dto;

import tools.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import tools.jackson.databind.annotation.JsonNaming;

/**
 * Telegram webhook payload (anti-corruption layer wire model, snake_case JSON).
 */
@JsonNaming(SnakeCaseStrategy.class)
public record TelegramUpdateRequest(
        Long updateId,
        Message message,
        PreCheckoutQuery preCheckoutQuery) {

    @JsonNaming(SnakeCaseStrategy.class)
    public record Message(
            Long messageId,
            User from,
            Chat chat,
            String text,
            SuccessfulPayment successfulPayment) {
    }

    @JsonNaming(SnakeCaseStrategy.class)
    public record User(
            Long id,
            String username,
            String firstName,
            String lastName,
            String languageCode,
            Boolean isPremium) {
    }

    @JsonNaming(SnakeCaseStrategy.class)
    public record Chat(
            Long id,
            String type) {
    }

    @JsonNaming(SnakeCaseStrategy.class)
    public record PreCheckoutQuery(
            String id,
            User from,
            String currency,
            Long totalAmount,
            String invoicePayload) {
    }

    @JsonNaming(SnakeCaseStrategy.class)
    public record SuccessfulPayment(
            String currency,
            Long totalAmount,
            String invoicePayload,
            String telegramPaymentChargeId,
            String providerPaymentChargeId) {
    }
}
