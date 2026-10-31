package com.easyfactor.payment.domain;

import java.time.Instant;
import java.util.UUID;

import com.easyfactor.shared.error.BusinessException;
import com.easyfactor.shared.error.ErrorCode;

import lombok.Getter;

/**
 * Owner's bank card for manual transfers; shown on the customer's invoice page.
 * Immutable — a mistyped card is deleted and re-added, never edited.
 */
@Getter
public class PaymentCard {

    private final UUID id;
    private final UUID ownerId;
    private final String cardNumber;
    private final String bankName;
    private final Instant createdAt;

    public PaymentCard(UUID id, UUID ownerId, String cardNumber, String bankName, Instant createdAt) {
        this.id = id;
        this.ownerId = ownerId;
        this.cardNumber = cardNumber;
        this.bankName = bankName;
        this.createdAt = createdAt;
    }

    public static PaymentCard create(UUID id, UUID ownerId, String rawCardNumber, String bankName, Instant now) {
        String digits = rawCardNumber.replaceAll("[\\s-]", "");
        if (!digits.matches("\\d{16}")) {
            throw new BusinessException(ErrorCode.VALIDATION_FAILED, "card number must be 16 digits");
        }
        return new PaymentCard(id, ownerId, digits, bankName, now);
    }
}
