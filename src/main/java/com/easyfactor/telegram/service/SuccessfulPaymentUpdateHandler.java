package com.easyfactor.telegram.service;

import com.easyfactor.payment.service.PaymentService;
import com.easyfactor.telegram.api.dto.TelegramUpdateRequest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Routes successful_payment updates. Ordered first because the payment
 * arrives nested inside a message update.
 */
@Component
@Order(10)
public class SuccessfulPaymentUpdateHandler implements TelegramUpdateHandler {

    private final PaymentService paymentService;

    public SuccessfulPaymentUpdateHandler(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public boolean supports(TelegramUpdateRequest update) {
        // TODO
        return false;
    }

    @Override
    public void handle(TelegramUpdateRequest update) {
        // TODO: paymentService.handleSuccessfulPayment(payload, chargeId, providerChargeId, amount, currency, raw)
    }
}
