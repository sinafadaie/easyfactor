package com.easyfactor.payment.service;

import java.time.Clock;

import com.easyfactor.invoice.domain.Invoice;
import com.easyfactor.invoice.domain.InvoiceRepository;
import com.easyfactor.payment.domain.PaymentRepository;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Payment initiation, pre-checkout validation and idempotent successful-payment handling.
 * Gateway payments are a post-MVP feature; MVP invoices are paid manually.
 */
@Service
public class PaymentService {

    private final InvoiceRepository invoiceRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentGatewayFactory gatewayFactory;
    private final ApplicationEventPublisher eventPublisher;
    private final Clock clock;

    public PaymentService(InvoiceRepository invoiceRepository, PaymentRepository paymentRepository,
            PaymentGatewayFactory gatewayFactory, ApplicationEventPublisher eventPublisher, Clock clock) {
        this.invoiceRepository = invoiceRepository;
        this.paymentRepository = paymentRepository;
        this.gatewayFactory = gatewayFactory;
        this.eventPublisher = eventPublisher;
        this.clock = clock;
    }

    public PreCheckoutResult handlePreCheckout(String payload, long totalAmount, String currency) {
        // TODO: fast validation — invoice exists, isPayable, amount/currency match, provider enabled
        return null;
    }

    public void handleSuccessfulPayment(String payload, String telegramPaymentChargeId, String providerChargeId,
            long totalAmount, String currency, String rawPayload) {
        // TODO: idempotency via telegramPaymentChargeId; Payment.markSucceeded; Invoice.markPaid;
        // TODO: save both; publish InvoicePaidEvent after commit
    }

    public void failPayment(java.util.UUID paymentId, String reason) {
        // TODO: Payment.markFailed; publish PaymentFailedEvent
    }

    public record PreCheckoutResult(boolean ok, String errorMessage) {
    }
}
