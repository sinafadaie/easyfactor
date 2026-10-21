package com.easyfactor.telegram.integration.dto;

import java.util.List;

import tools.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import tools.jackson.databind.annotation.JsonNaming;

/**
 * sendInvoice Bot API payload (Builder pattern).
 */
@JsonNaming(SnakeCaseStrategy.class)
public class TelegramSendInvoiceRequest {

    private final Long chatId;
    private final String title;
    private final String description;
    private final String payload;
    private final String providerToken;
    private final String currency;
    private final List<LabeledPrice> prices;

    private TelegramSendInvoiceRequest(Builder builder) {
        this.chatId = builder.chatId;
        this.title = builder.title;
        this.description = builder.description;
        this.payload = builder.payload;
        this.providerToken = builder.providerToken;
        this.currency = builder.currency;
        this.prices = builder.prices;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getChatId() {
        return chatId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPayload() {
        return payload;
    }

    public String getProviderToken() {
        return providerToken;
    }

    public String getCurrency() {
        return currency;
    }

    public List<LabeledPrice> getPrices() {
        return prices;
    }

    @JsonNaming(SnakeCaseStrategy.class)
    public record LabeledPrice(String label, long amount) {
    }

    public static class Builder {

        private Long chatId;
        private String title;
        private String description;
        private String payload;
        private String providerToken;
        private String currency;
        private List<LabeledPrice> prices;

        public Builder chatId(Long chatId) {
            this.chatId = chatId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder payload(String payload) {
            this.payload = payload;
            return this;
        }

        public Builder providerToken(String providerToken) {
            this.providerToken = providerToken;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder prices(List<LabeledPrice> prices) {
            this.prices = prices;
            return this;
        }

        public TelegramSendInvoiceRequest build() {
            // TODO: validate title length (1-32), description (1-255), non-empty prices
            return new TelegramSendInvoiceRequest(this);
        }
    }
}
