package com.easyfactor.payment.api;

import java.util.List;
import java.util.UUID;

import com.easyfactor.payment.api.dto.CreatePaymentCardRequest;
import com.easyfactor.payment.api.dto.PaymentCardResponse;
import com.easyfactor.payment.service.PaymentCardService;
import com.easyfactor.security.AuthenticatedUser;
import com.easyfactor.shared.api.ApiResponse;

import jakarta.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Owner-facing payment card management (panel's Payment section).
 */
@RestController
@RequestMapping("/api/v1/owner/payment-cards")
public class OwnerPaymentCardController {

    private final PaymentCardService paymentCardService;

    public OwnerPaymentCardController(PaymentCardService paymentCardService) {
        this.paymentCardService = paymentCardService;
    }

    @PostMapping
    ApiResponse<PaymentCardResponse> add(@AuthenticationPrincipal AuthenticatedUser owner,
            @Valid @RequestBody CreatePaymentCardRequest request) {
        var card = paymentCardService.addCard(owner.accountId(), request.cardNumber(), request.bankName());
        return ApiResponse.ok(PaymentCardResponse.from(card));
    }

    @GetMapping
    ApiResponse<List<PaymentCardResponse>> list(@AuthenticationPrincipal AuthenticatedUser owner) {
        var cards = paymentCardService.listForOwner(owner.accountId()).stream()
                .map(PaymentCardResponse::from)
                .toList();
        return ApiResponse.ok(cards);
    }

    @DeleteMapping("/{cardId}")
    ApiResponse<Void> delete(@AuthenticationPrincipal AuthenticatedUser owner, @PathVariable UUID cardId) {
        paymentCardService.deleteCard(owner.accountId(), cardId);
        return ApiResponse.ok(null);
    }
}
