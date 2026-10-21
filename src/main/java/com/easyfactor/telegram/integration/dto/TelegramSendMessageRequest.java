package com.easyfactor.telegram.integration.dto;

import tools.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import tools.jackson.databind.annotation.JsonNaming;

/**
 * sendMessage Bot API payload (Builder pattern).
 */
@JsonNaming(SnakeCaseStrategy.class)
public class TelegramSendMessageRequest {

    private final Long chatId;
    private final String text;
    private final String parseMode;

    private TelegramSendMessageRequest(Builder builder) {
        this.chatId = builder.chatId;
        this.text = builder.text;
        this.parseMode = builder.parseMode;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getChatId() {
        return chatId;
    }

    public String getText() {
        return text;
    }

    public String getParseMode() {
        return parseMode;
    }

    public static class Builder {

        private Long chatId;
        private String text;
        private String parseMode;

        public Builder chatId(Long chatId) {
            this.chatId = chatId;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder parseMode(String parseMode) {
            this.parseMode = parseMode;
            return this;
        }

        public TelegramSendMessageRequest build() {
            // TODO: validate chatId present and text length (1-4096)
            return new TelegramSendMessageRequest(this);
        }
    }
}
