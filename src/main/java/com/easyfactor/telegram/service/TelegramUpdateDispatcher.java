package com.easyfactor.telegram.service;

import java.util.List;

import com.easyfactor.telegram.api.dto.TelegramUpdateRequest;

import org.springframework.stereotype.Service;

/**
 * Selects and invokes the first handler that supports the update (Chain of Responsibility).
 * Handler order is significant: successful_payment arrives nested inside message.
 */
@Service
public class TelegramUpdateDispatcher {

    private final List<TelegramUpdateHandler> handlers;

    public TelegramUpdateDispatcher(List<TelegramUpdateHandler> handlers) {
        this.handlers = handlers;
    }

    public void dispatch(TelegramUpdateRequest update) {
        // TODO: find first handler where supports(update) is true and call handle(update)
    }
}
