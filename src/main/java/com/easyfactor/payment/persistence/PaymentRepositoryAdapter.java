package com.easyfactor.payment.persistence;

import java.util.Optional;
import java.util.UUID;

import com.easyfactor.payment.domain.Payment;
import com.easyfactor.payment.domain.PaymentRepository;

import org.springframework.stereotype.Repository;

/**
 * Adapter implementing the Payment persistence port over Spring Data JPA.
 */
@Repository
public class PaymentRepositoryAdapter implements PaymentRepository {

    private final PaymentJpaRepository jpaRepository;
    private final PaymentPersistenceMapper mapper;

    public PaymentRepositoryAdapter(PaymentJpaRepository jpaRepository, PaymentPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Payment save(Payment payment) {
        // TODO
        return null;
    }

    @Override
    public Optional<Payment> findById(UUID id) {
        // TODO
        return Optional.empty();
    }

    @Override
    public Optional<Payment> findByTelegramPaymentChargeId(String telegramPaymentChargeId) {
        // TODO
        return Optional.empty();
    }

    @Override
    public Optional<Payment> findLatestByInvoiceId(UUID invoiceId) {
        // TODO
        return Optional.empty();
    }

    @Override
    public boolean existsByTelegramPaymentChargeId(String telegramPaymentChargeId) {
        // TODO
        return false;
    }
}
