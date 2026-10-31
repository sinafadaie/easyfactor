package com.easyfactor.payment.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentCardJpaRepository extends JpaRepository<PaymentCardJpaEntity, UUID> {

    List<PaymentCardJpaEntity> findByOwnerIdOrderByCreatedAtAsc(UUID ownerId);
}
