package com.easyfactor.payment.domain;

import java.time.Instant;
import java.util.UUID;

import com.easyfactor.shared.domain.Money;
import com.easyfactor.shared.domain.PaymentProvider;

/**
 * Payment aggregate root.
 */
public class Payment {

    private final UUID id;
    private final UUID invoiceId;
    private final PaymentProvider provider;
    private PaymentStatus status;
    private String providerReference;
    private String telegramPaymentChargeId;
    private final Money amount;
    private String rawPayload;
    private String failureReason;
    private final Instant createdAt;
    private Instant completedAt;

    public Payment(UUID id, UUID invoiceId, PaymentProvider provider, PaymentStatus status, String providerReference,
            String telegramPaymentChargeId, Money amount, String rawPayload, String failureReason, Instant createdAt,
            Instant completedAt) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.provider = provider;
        this.status = status;
        this.providerReference = providerReference;
        this.telegramPaymentChargeId = telegramPaymentChargeId;
        this.amount = amount;
        this.rawPayload = rawPayload;
        this.failureReason = failureReason;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
    }

    public static Payment initiate(UUID invoiceId, PaymentProvider provider, Money amount, Instant now) {
        // TODO
        return null;
    }

    public void markSucceeded(String telegramPaymentChargeId, String providerChargeId, String rawPayload, Instant now) {
        // TODO
    }

    public void markFailed(String reason, Instant now) {
        // TODO
    }

    public void markRefunded(Instant now) {
        // TODO
    }

    public UUID getId() {
        return id;
    }

    public UUID getInvoiceId() {
        return invoiceId;
    }

    public PaymentProvider getProvider() {
        return provider;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getProviderReference() {
        return providerReference;
    }

    public String getTelegramPaymentChargeId() {
        return telegramPaymentChargeId;
    }

    public Money getAmount() {
        return amount;
    }

    public String getRawPayload() {
        return rawPayload;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }
}
