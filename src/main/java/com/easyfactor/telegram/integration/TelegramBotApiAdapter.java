package com.easyfactor.telegram.integration;

import com.easyfactor.config.TelegramProperties;
import com.easyfactor.telegram.domain.TelegramApiException;
import com.easyfactor.telegram.domain.TelegramBotApiClient;
import com.easyfactor.telegram.integration.dto.TelegramAnswerPreCheckoutQueryRequest;
import com.easyfactor.telegram.integration.dto.TelegramSendInvoiceRequest;
import com.easyfactor.telegram.integration.dto.TelegramSendMessageRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * HTTP adapter over the Telegram Bot API (Adapter pattern). Seals the bot token
 * and maps non-ok responses to {@link TelegramApiException}.
 */
@Component
public class TelegramBotApiAdapter implements TelegramBotApiClient {

    private final RestClient restClient;
    private final TelegramProperties properties;

    public TelegramBotApiAdapter(TelegramProperties properties) {
        this.restClient = RestClient.builder().baseUrl(properties.apiBaseUrl()).build();
        this.properties = properties;
    }

    @Override
    public void sendInvoice(TelegramSendInvoiceRequest request) {
        // TODO: POST /bot{token}/sendInvoice, check ok flag
    }

    @Override
    public void sendMessage(TelegramSendMessageRequest request) {
        // TODO: POST /bot{token}/sendMessage, check ok flag
    }

    @Override
    public void answerPreCheckoutQuery(TelegramAnswerPreCheckoutQueryRequest request) {
        // TODO: POST /bot{token}/answerPreCheckoutQuery, check ok flag
    }
}
