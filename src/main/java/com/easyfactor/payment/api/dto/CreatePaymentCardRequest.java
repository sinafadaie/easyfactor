package com.easyfactor.payment.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePaymentCardRequest(
        @NotBlank @Size(max = 32) String cardNumber,
        @Size(max = 64) String bankName) {
}
