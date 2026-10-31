package com.easyfactor.payment.service;

import java.time.Clock;
import java.util.List;
import java.util.UUID;

import com.easyfactor.payment.domain.PaymentCard;
import com.easyfactor.payment.domain.PaymentCardRepository;
import com.easyfactor.shared.error.BusinessException;
import com.easyfactor.shared.error.ErrorCode;
import com.easyfactor.shared.error.NotFoundException;

import org.springframework.stereotype.Service;

/**
 * Owner-managed payment cards for manual transfers.
 */
@Service
public class PaymentCardService {

    private final PaymentCardRepository cardRepository;
    private final Clock clock;

    public PaymentCardService(PaymentCardRepository cardRepository, Clock clock) {
        this.cardRepository = cardRepository;
        this.clock = clock;
    }

    public PaymentCard addCard(UUID ownerId, String cardNumber, String bankName) {
        PaymentCard card = PaymentCard.create(UUID.randomUUID(), ownerId, cardNumber, bankName, clock.instant());
        return cardRepository.save(card);
    }

    public List<PaymentCard> listForOwner(UUID ownerId) {
        return cardRepository.findByOwnerId(ownerId);
    }

    public void deleteCard(UUID ownerId, UUID cardId) {
        PaymentCard card = cardRepository.findById(cardId)
                .orElseThrow(() -> new NotFoundException("payment card", cardId));
        if (!card.getOwnerId().equals(ownerId)) {
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }
        cardRepository.deleteById(cardId);
    }
}
