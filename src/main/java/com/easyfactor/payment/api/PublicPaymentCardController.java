package com.easyfactor.payment.api;

import java.util.List;
import java.util.UUID;

import com.easyfactor.payment.api.dto.PaymentCardResponse;
import com.easyfactor.payment.service.PaymentCardService;
import com.easyfactor.shared.api.ApiResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Public card listing for the customer's invoice page. No auth by design:
 * the card number is the payment destination, like a card printed on an invoice.
 */
@RestController
@RequestMapping("/api/v1/public/owners/{ownerId}/payment-cards")
public class PublicPaymentCardController {

    private final PaymentCardService paymentCardService;

    public PublicPaymentCardController(PaymentCardService paymentCardService) {
        this.paymentCardService = paymentCardService;
    }

    @GetMapping
    ApiResponse<List<PaymentCardResponse>> list(@PathVariable UUID ownerId) {
        var cards = paymentCardService.listForOwner(ownerId).stream()
                .map(PaymentCardResponse::from)
                .toList();
        return ApiResponse.ok(cards);
    }
}
