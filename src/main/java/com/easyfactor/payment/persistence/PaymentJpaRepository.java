package com.easyfactor.payment.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<PaymentJpaEntity, UUID> {

    Optional<PaymentJpaEntity> findByTelegramPaymentChargeId(String telegramPaymentChargeId);

    Optional<PaymentJpaEntity> findFirstByInvoiceIdOrderByCreatedAtDesc(UUID invoiceId);

    boolean existsByTelegramPaymentChargeId(String telegramPaymentChargeId);
}
