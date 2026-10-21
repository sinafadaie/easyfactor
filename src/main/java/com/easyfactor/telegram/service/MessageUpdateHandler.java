package com.easyfactor.telegram.service;

import com.easyfactor.telegram.api.dto.TelegramUpdateRequest;
import com.easyfactor.telegram.domain.TelegramBotApiClient;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Handles /start and plain messages. Ordered last: successful_payment
 * and pre_checkout_query take precedence.
 */
@Component
@Order(30)
public class MessageUpdateHandler implements TelegramUpdateHandler {

    private final TelegramBotApiClient botApiClient;

    public MessageUpdateHandler(TelegramBotApiClient botApiClient) {
        this.botApiClient = botApiClient;
    }

    @Override
    public boolean supports(TelegramUpdateRequest update) {
        // TODO
        return false;
    }

    @Override
    public void handle(TelegramUpdateRequest update) {
        // TODO: answer /start with a welcome message
    }
}
