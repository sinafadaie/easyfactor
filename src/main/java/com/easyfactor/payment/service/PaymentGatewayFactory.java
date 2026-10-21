package com.easyfactor.payment.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.easyfactor.invoice.domain.Invoice;
import com.easyfactor.payment.domain.PaymentGateway;
import com.easyfactor.shared.domain.PaymentProvider;

import org.springframework.stereotype.Component;

/**
 * Selects the gateway strategy by invoice provider (Factory + Strategy selection).
 */
@Component
public class PaymentGatewayFactory {

    private final Map<PaymentProvider, PaymentGateway> gatewaysByProvider;

    public PaymentGatewayFactory(List<PaymentGateway> gateways) {
        this.gatewaysByProvider = gateways.stream()
                .collect(Collectors.toUnmodifiableMap(PaymentGateway::provider, Function.identity()));
    }

    public PaymentGateway forInvoice(Invoice invoice) {
        // TODO
        return null;
    }

    public PaymentGateway forProvider(PaymentProvider provider) {
        // TODO
        return null;
    }
}
