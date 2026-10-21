package com.easyfactor.telegram.domain;

/**
 * Verified Telegram identity extracted from signed initData.
 */
public record TelegramUser(
        long id,
        String username,
        String firstName,
        String lastName,
        String languageCode,
        boolean premium) {
}
