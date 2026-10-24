package com.easyfactor.payment.domain;

import java.time.Instant;
import java.util.UUID;

import com.easyfactor.shared.domain.PaymentProvider;

import lombok.Getter;

/**
 * Payment aggregate root.
 */
@Getter
public class Payment {

    private final UUID id;
    private final UUID invoiceId;
    private final PaymentProvider provider;
    private PaymentStatus status;
    private String providerReference;
    private String telegramPaymentChargeId;
    private final long amount;
    private String rawPayload;
    private String failureReason;
    private final Instant createdAt;
    private Instant completedAt;

    public Payment(UUID id, UUID invoiceId, PaymentProvider provider, PaymentStatus status, String providerReference,
            String telegramPaymentChargeId, long amount, String rawPayload, String failureReason, Instant createdAt,
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

    public static Payment initiate(UUID invoiceId, PaymentProvider provider, long amount, Instant now) {
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
}
