package com.easyfactor.invoice.domain;

/**
 * Hexagonal output port decoupling invoice sending from the payment implementation.
 * Implemented by PaymentService so the invoice package never depends on the payment package.
 */
public interface InvoicePaymentInitiator {

    void initiatePayment(Invoice invoice);
}
