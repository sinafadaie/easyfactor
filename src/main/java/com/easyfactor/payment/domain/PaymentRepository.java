package com.easyfactor.payment.domain;

import java.util.Optional;
import java.util.UUID;

/**
 * Payment persistence port (hexagonal boundary).
 */
public interface PaymentRepository {

    Payment save(Payment payment);

    Optional<Payment> findById(UUID id);

    Optional<Payment> findByTelegramPaymentChargeId(String telegramPaymentChargeId);

    Optional<Payment> findLatestByInvoiceId(UUID invoiceId);

    boolean existsByTelegramPaymentChargeId(String telegramPaymentChargeId);
}
