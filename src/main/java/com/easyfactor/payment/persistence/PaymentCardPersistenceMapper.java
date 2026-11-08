package com.easyfactor.payment.persistence;

import com.easyfactor.payment.domain.PaymentCard;

import org.springframework.stereotype.Component;

/**
 * Maps PaymentCard between domain aggregate and JPA entity.
 */
@Component
public class PaymentCardPersistenceMapper {

    public PaymentCard toDomain(PaymentCardJpaEntity entity) {
        return new PaymentCard(
                entity.getId(),
                entity.getOwnerId(),
                entity.getCardNumber(),
                entity.getBankName(),
                entity.getCreatedAt());
    }

    public PaymentCardJpaEntity toEntity(PaymentCard domain) {
        PaymentCardJpaEntity entity = new PaymentCardJpaEntity();
        //entity.setId(domain.getId());       //create bug
                                              //Hibernate see id is not null so he thinks it's a old record
                                              //so he uses merge instead of persist and we get .StaleObjectStateException
        entity.setOwnerId(domain.getOwnerId());
        entity.setCardNumber(domain.getCardNumber());
        entity.setBankName(domain.getBankName());
        return entity;
    }
}
