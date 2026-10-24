package com.easyfactor.identity.persistence;

import lombok.Getter;
import lombok.Setter;

import com.easyfactor.shared.persistence.AuditableJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "owners")
@Getter
@Setter
public class OwnerJpaEntity extends AuditableJpaEntity {

    @Column(name = "telegram_user_id", nullable = false, unique = true)
    private Long telegramUserId;

    @Column(name = "telegram_username", length = 64)
    private String telegramUsername;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "language_code", length = 16)
    private String languageCode;

    @Column(name = "shop_name")
    private String shopName;
}
