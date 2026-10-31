package com.easyfactor.payment.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.easyfactor.payment.domain.PaymentCard;
import com.easyfactor.payment.domain.PaymentCardRepository;

import org.springframework.stereotype.Repository;

/**
 * Adapter implementing the PaymentCard persistence port over Spring Data JPA.
 */
@Repository
public class PaymentCardRepositoryAdapter implements PaymentCardRepository {

    private final PaymentCardJpaRepository jpaRepository;
    private final PaymentCardPersistenceMapper mapper;

    public PaymentCardRepositoryAdapter(PaymentCardJpaRepository jpaRepository, PaymentCardPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public PaymentCard save(PaymentCard card) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(card)));
    }

    @Override
    public List<PaymentCard> findByOwnerId(UUID ownerId) {
        return jpaRepository.findByOwnerIdOrderByCreatedAtAsc(ownerId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<PaymentCard> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}
