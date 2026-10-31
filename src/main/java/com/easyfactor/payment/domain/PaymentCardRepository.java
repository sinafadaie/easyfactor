package com.easyfactor.payment.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * PaymentCard persistence port (hexagonal boundary).
 */
public interface PaymentCardRepository {

    PaymentCard save(PaymentCard card);

    List<PaymentCard> findByOwnerId(UUID ownerId);

    Optional<PaymentCard> findById(UUID id);

    void deleteById(UUID id);
}
