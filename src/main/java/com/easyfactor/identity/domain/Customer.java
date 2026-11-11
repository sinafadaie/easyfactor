package com.easyfactor.identity.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.easyfactor.shared.error.BusinessException;
import com.easyfactor.shared.error.ErrorCode;
import com.easyfactor.telegram.domain.TelegramUser;

import lombok.Getter;

/**
 * Customer aggregate root. A customer may have no Telegram account
 * (telegramUserId is nullable) — owners can create invoices for phone-based
 * customers too.
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
    private UUID ownerId;

    public Customer(UUID id, Long telegramUserId, String telegramUsername, String firstName, String lastName,
            String phoneNumber, String languageCode, List<Address> addresses, Instant createdAt, Instant updatedAt, UUID ownerId) {
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
        this.ownerId = ownerId;
    }

    public static Customer register(TelegramUser user, Instant now) {
        // TODO
        return null;
    }

    public static Customer manualRegister(UUID ownerId, String firstName, String lastName, String phoneNumber, Instant now) {   //this is factory method, so it is static
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new BusinessException(ErrorCode.VALIDATION_FAILED, "phone number is required");
        }
        return new Customer(UUID.randomUUID(), null, null, firstName, lastName, phoneNumber, "fa", new ArrayList<>() , now, now, ownerId);
    }

    public void updateTelegramProfile(TelegramUser user) {
        // TODO
    }

    public void addAddress(Address address) {
        this.addresses.add(address);        //add user field to address?
    }

    public void removeAddress(Address address) {
        this.addresses.remove(address);
    }
}
