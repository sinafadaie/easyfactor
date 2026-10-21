package com.easyfactor.payment.persistence;

import com.easyfactor.payment.domain.Payment;

import org.springframework.stereotype.Component;

/**
 * Maps Payment between domain aggregate and JPA entity (including Money <-> amount/currency).
 */
@Component
public class PaymentPersistenceMapper {

    public Payment toDomain(PaymentJpaEntity entity) {
        // TODO
        return null;
    }

    public PaymentJpaEntity toEntity(Payment domain) {
        // TODO
        return null;
    }
}
