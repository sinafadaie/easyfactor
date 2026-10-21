package com.easyfactor.notification.service;

import com.easyfactor.invoice.domain.event.InvoicePaidEvent;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * Triggers digital delivery after payment commit (Observer pattern).
 */
@Component
public class DigitalDeliveryListener {

    private final DigitalDeliveryService digitalDeliveryService;

    public DigitalDeliveryListener(DigitalDeliveryService digitalDeliveryService) {
        this.digitalDeliveryService = digitalDeliveryService;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onInvoicePaid(InvoicePaidEvent event) {
        // TODO: digitalDeliveryService.deliver(event)
    }
}
