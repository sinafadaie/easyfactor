package com.easyfactor.identity.persistence;

import java.util.ArrayList;
import java.util.List;

import com.easyfactor.shared.persistence.AuditableJpaEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class CustomerJpaEntity extends AuditableJpaEntity {

    @Column(name = "telegram_user_id", unique = true)
    private Long telegramUserId;

    @Column(name = "telegram_username", length = 64)
    private String telegramUsername;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number", length = 32)
    private String phoneNumber;

    @Column(name = "language_code", length = 16)
    private String languageCode;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AddressJpaEntity> addresses = new ArrayList<>();

    public Long getTelegramUserId() {
        return telegramUserId;
    }

    public void setTelegramUserId(Long telegramUserId) {
        this.telegramUserId = telegramUserId;
    }

    public String getTelegramUsername() {
        return telegramUsername;
    }

    public void setTelegramUsername(String telegramUsername) {
        this.telegramUsername = telegramUsername;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public List<AddressJpaEntity> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressJpaEntity> addresses) {
        this.addresses = addresses;
    }
}
