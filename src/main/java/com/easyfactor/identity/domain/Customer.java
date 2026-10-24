package com.easyfactor.identity.domain;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.easyfactor.telegram.domain.TelegramUser;

import lombok.Getter;

/**
 * Customer aggregate root. A customer may have no Telegram account
 * (telegramUserId is nullable) — owners can create invoices for phone-based customers too.
 */
@Getter
public class Customer {

    private final UUID id;
    private final Long telegramUserId;
    private String telegramUsername;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String languageCode;
    private List<Address> addresses;
    private final Instant createdAt;
    private Instant updatedAt;

    public Customer(UUID id, Long telegramUserId, String telegramUsername, String firstName, String lastName,
            String phoneNumber, String languageCode, List<Address> addresses, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.telegramUserId = telegramUserId;
        this.telegramUsername = telegramUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.languageCode = languageCode;
        this.addresses = addresses;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Customer register(TelegramUser user, Instant now) {
        // TODO
        return null;
    }

    public static Customer registerManual(String firstName, String lastName, String phoneNumber, Instant now) {
        // TODO: create customer without Telegram identity (telegramUserId = null)
        return null;
    }

    public void updateTelegramProfile(TelegramUser user) {
        // TODO
    }

    public void addAddress(Address address) {
        // TODO
    }

    public void removeAddress(UUID addressId) {
        // TODO
    }
}
