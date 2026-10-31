package com.easyfactor.payment.persistence;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

import com.easyfactor.shared.persistence.AuditableJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment_cards")
@Getter
@Setter
public class PaymentCardJpaEntity extends AuditableJpaEntity {

    @Column(name = "owner_id", nullable = false)
    private UUID ownerId;

    @Column(name = "card_number", nullable = false, length = 16)
    private String cardNumber;

    @Column(name = "bank_name", length = 64)
    private String bankName;
}
