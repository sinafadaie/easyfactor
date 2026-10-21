package com.easyfactor.identity.domain;

import java.time.Instant;
import java.util.UUID;

import com.easyfactor.shared.domain.CurrencyCode;
import com.easyfactor.telegram.domain.TelegramUser;

/**
 * Owner (shop owner) aggregate root.
 */
public class Owner {

    private final UUID id;
    private final Long telegramUserId;
    private String telegramUsername;
    private String firstName;
    private String lastName;
    private String languageCode;
    private String shopName;
    private CurrencyCode defaultCurrency;
    private final Instant createdAt;
    private Instant updatedAt;

    public Owner(UUID id, Long telegramUserId, String telegramUsername, String firstName, String lastName,
            String languageCode, String shopName, CurrencyCode defaultCurrency, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.telegramUserId = telegramUserId;
        this.telegramUsername = telegramUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.languageCode = languageCode;
        this.shopName = shopName;
        this.defaultCurrency = defaultCurrency;
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

    public void changeDefaultCurrency(CurrencyCode currency) {
        // TODO
    }

    public UUID getId() {
        return id;
    }

    public Long getTelegramUserId() {
        return telegramUserId;
    }

    public String getTelegramUsername() {
        return telegramUsername;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public String getShopName() {
        return shopName;
    }

    public CurrencyCode getDefaultCurrency() {
        return defaultCurrency;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
