package com.easyfactor.payment.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Instant;
import java.util.UUID;

import com.easyfactor.shared.error.BusinessException;
import com.easyfactor.shared.error.ErrorCode;

import org.junit.jupiter.api.Test;

class PaymentCardTest {

    private static final UUID OWNER_ID = UUID.randomUUID();
    private static final Instant NOW = Instant.parse("2026-10-31T10:00:00Z");

    @Test
    void acceptsSixteenDigits() {
        PaymentCard card = PaymentCard.create(UUID.randomUUID(), OWNER_ID, "6037991234567890", "Mellat", NOW);

        assertThat(card.getCardNumber()).isEqualTo("6037991234567890");
        assertThat(card.getBankName()).isEqualTo("Mellat");
        assertThat(card.getOwnerId()).isEqualTo(OWNER_ID);
    }

    @Test
    void normalizesSpacesAndDashes() {
        PaymentCard card = PaymentCard.create(UUID.randomUUID(), OWNER_ID, "6037-9912-3456-7890", null, NOW);

        assertThat(card.getCardNumber()).isEqualTo("6037991234567890");
    }

    @Test
    void rejectsNonSixteenDigits() {
        assertThatThrownBy(() -> PaymentCard.create(UUID.randomUUID(), OWNER_ID, "603799123456789", null, NOW))
                .isInstanceOfSatisfying(BusinessException.class, e ->
                        assertThat(e.errorCode()).isEqualTo(ErrorCode.VALIDATION_FAILED));
    }

    @Test
    void rejectsNonDigitCharacters() {
        assertThatThrownBy(() -> PaymentCard.create(UUID.randomUUID(), OWNER_ID, "60379912345678ab", null, NOW))
                .isInstanceOfSatisfying(BusinessException.class, e ->
                        assertThat(e.errorCode()).isEqualTo(ErrorCode.VALIDATION_FAILED));
    }
}
