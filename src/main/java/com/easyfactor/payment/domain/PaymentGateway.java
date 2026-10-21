package com.easyfactor.payment.domain;

import java.time.Instant;

import com.easyfactor.invoice.domain.Invoice;
import com.easyfactor.shared.domain.PaymentProvider;

/**
 * Provider payment strategy contract (Strategy pattern).
 */
public interface PaymentGateway {

    PaymentProvider provider();

    InitiationResult initiate(Invoice invoice);

    record InitiationResult(String providerReference, Instant initiatedAt) {
    }
}
