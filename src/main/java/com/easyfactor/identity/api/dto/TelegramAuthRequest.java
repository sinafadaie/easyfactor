package com.easyfactor.identity.api.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Raw Telegram Mini App initData as signed by Telegram.
 */
public record TelegramAuthRequest(
        @NotBlank String initData) {
}
