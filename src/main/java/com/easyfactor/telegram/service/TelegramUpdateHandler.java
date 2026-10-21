package com.easyfactor.telegram.service;

import com.easyfactor.telegram.api.dto.TelegramUpdateRequest;

/**
 * Handler contract for webhook updates (Chain of Responsibility).
 */
public interface TelegramUpdateHandler {

    boolean supports(TelegramUpdateRequest update);

    void handle(TelegramUpdateRequest update);
}
