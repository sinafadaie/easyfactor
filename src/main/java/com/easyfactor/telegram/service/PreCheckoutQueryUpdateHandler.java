package com.easyfactor.telegram.service;

import com.easyfactor.payment.service.PaymentService;
import com.easyfactor.telegram.api.dto.TelegramUpdateRequest;
import com.easyfactor.telegram.domain.TelegramBotApiClient;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Validates pre_checkout_query and answers within Telegram's 10-second deadline.
 * Must stay fast: no notification/delivery work in this path.
 */
@Component
@Order(20)
public class PreCheckoutQueryUpdateHandler implements TelegramUpdateHandler {

    private final PaymentService paymentService;
    private final TelegramBotApiClient botApiClient;

    public PreCheckoutQueryUpdateHandler(PaymentService paymentService, TelegramBotApiClient botApiClient) {
        this.paymentService = paymentService;
        this.botApiClient = botApiClient;
    }

    @Override
    public boolean supports(TelegramUpdateRequest update) {
        // TODO
        return false;
    }

    @Override
    public void handle(TelegramUpdateRequest update) {
        // TODO: paymentService.handlePreCheckout(...) then botApiClient.answerPreCheckoutQuery(...)
    }
}
