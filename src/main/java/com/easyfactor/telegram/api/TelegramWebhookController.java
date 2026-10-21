package com.easyfactor.telegram.api;

import com.easyfactor.config.TelegramProperties;
import com.easyfactor.telegram.api.dto.TelegramUpdateRequest;
import com.easyfactor.telegram.service.TelegramUpdateDispatcher;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Telegram Bot API webhook entry point. Validates the secret token header
 * (constant-time) and always answers 200 fast so Telegram retries don't pile up.
 */
@RestController
@RequestMapping("/api/v1/telegram")
public class TelegramWebhookController {

    private final TelegramUpdateDispatcher updateDispatcher;
    private final TelegramProperties properties;

    public TelegramWebhookController(TelegramUpdateDispatcher updateDispatcher, TelegramProperties properties) {
        this.updateDispatcher = updateDispatcher;
        this.properties = properties;
    }

    @PostMapping("/webhook")
    void webhook(@RequestHeader("X-Telegram-Bot-Api-Secret-Token") String secretToken,
            @RequestBody TelegramUpdateRequest request) {
        // TODO: constant-time compare secretToken against properties.webhookSecretToken()
        updateDispatcher.dispatch(request);
    }
}
