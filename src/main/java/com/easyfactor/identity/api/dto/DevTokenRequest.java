package com.easyfactor.identity.api.dto;

import java.util.UUID;

/**
 * Dev-token request: identify the owner row by telegram_user_id or by id.
 */
public record DevTokenRequest(
        Long telegramUserId,
        UUID accountId) {
}
