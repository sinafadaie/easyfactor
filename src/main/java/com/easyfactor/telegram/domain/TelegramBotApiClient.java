package com.easyfactor.telegram.domain;

import com.easyfactor.telegram.integration.dto.TelegramAnswerPreCheckoutQueryRequest;
import com.easyfactor.telegram.integration.dto.TelegramSendInvoiceRequest;
import com.easyfactor.telegram.integration.dto.TelegramSendMessageRequest;

/**
 * Telegram Bot API output port (hexagonal boundary).
 */
public interface TelegramBotApiClient {

    void sendInvoice(TelegramSendInvoiceRequest request);

    void sendMessage(TelegramSendMessageRequest request);

    void answerPreCheckoutQuery(TelegramAnswerPreCheckoutQueryRequest request);
}
