package com.easyfactor.telegram.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Verified Telegram identity extracted from signed initData.
 * Field names follow Telegram's JSON (snake_case); Jackson 3 maps them via @JsonProperty.
 */
public record TelegramUser(
        long id,
        @JsonProperty("username") String username,              //@JsonProperty says the field name on JSON
        @JsonProperty("first_name") String firstName,
        @JsonProperty("last_name") String lastName,
        @JsonProperty("language_code") String languageCode,
        @JsonProperty("is_premium") boolean premium) {
}
