package com.easyfactor.payment.api.dto;

import java.util.UUID;

import com.easyfactor.payment.domain.PaymentCard;

public record PaymentCardResponse(
        UUID id,
        String cardNumber,
        String bankName) {

    public static PaymentCardResponse from(PaymentCard card) {
        return new PaymentCardResponse(card.getId(), card.getCardNumber(), card.getBankName());
    }
}
