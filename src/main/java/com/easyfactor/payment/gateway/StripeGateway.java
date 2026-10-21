package com.easyfactor.payment.gateway;

import com.easyfactor.invoice.domain.Invoice;
import com.easyfactor.payment.domain.PaymentGateway;
import com.easyfactor.shared.domain.PaymentProvider;
import com.easyfactor.shared.error.BusinessException;
import com.easyfactor.shared.error.ErrorCode;

import org.springframework.stereotype.Component;

/**
 * Stripe gateway strategy placeholder — fails with GATEWAY_UNAVAILABLE until implemented.
 */
@Component
public class StripeGateway implements PaymentGateway {

    @Override
    public PaymentProvider provider() {
        return PaymentProvider.STRIPE;
    }

    @Override
    public InitiationResult initiate(Invoice invoice) {
        // TODO: real Stripe integration (PaymentIntent / provider_token via BotFather)
        throw new BusinessException(ErrorCode.GATEWAY_UNAVAILABLE);
    }
}
