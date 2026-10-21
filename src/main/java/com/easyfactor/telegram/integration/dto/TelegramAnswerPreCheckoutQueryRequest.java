package com.easyfactor.telegram.integration.dto;

import tools.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import tools.jackson.databind.annotation.JsonNaming;

/**
 * answerPreCheckoutQuery Bot API payload.
 */
@JsonNaming(SnakeCaseStrategy.class)
public record TelegramAnswerPreCheckoutQueryRequest(
        String preCheckoutQueryId,
        boolean ok,
        String errorMessage) {
}
