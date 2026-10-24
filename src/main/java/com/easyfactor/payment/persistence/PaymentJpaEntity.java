package com.easyfactor.payment.persistence;

import java.time.Instant;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

import com.easyfactor.shared.persistence.AuditableJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
@Getter
@Setter
public class PaymentJpaEntity extends AuditableJpaEntity {

    @Column(name = "invoice_id", nullable = false)
    private UUID invoiceId;

    @Column(name = "provider", nullable = false, length = 32)
    private String provider;

    @Column(name = "status", nullable = false, length = 32)
    private String status;

    @Column(name = "provider_reference")
    private String providerReference;

    @Column(name = "telegram_payment_charge_id")
    private String telegramPaymentChargeId;

    @Column(name = "amount", nullable = false)
    private long amount;

    @Column(name = "raw_payload", columnDefinition = "jsonb")
    private String rawPayload;

    @Column(name = "failure_reason", length = 512)
    private String failureReason;

    @Column(name = "completed_at")
    private Instant completedAt;
}
