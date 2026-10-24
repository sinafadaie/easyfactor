package com.easyfactor.identity.domain;

import java.time.Instant;
import java.util.UUID;

import com.easyfactor.telegram.domain.TelegramUser;

import lombok.Getter;

/**
 * Owner (shop owner) aggregate root.
 */
@Getter
public class Owner {

    private final UUID id;
    private final Long telegramUserId;
    private String telegramUsername;
    private String firstName;
    private String lastName;
    private String languageCode;
    private String shopName;
    private final Instant createdAt;
    private Instant updatedAt;

    public Owner(UUID id, Long telegramUserId, String telegramUsername, String firstName, String lastName,
            String languageCode, String shopName, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.telegramUserId = telegramUserId;
        this.telegramUsername = telegramUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.languageCode = languageCode;
        this.shopName = shopName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Owner register(TelegramUser user, Instant now) {
        // TODO
        return null;
    }

    public void updateProfile(TelegramUser user) {
        // TODO
    }

    public void renameShop(String newShopName) {
        // TODO
    }
}
