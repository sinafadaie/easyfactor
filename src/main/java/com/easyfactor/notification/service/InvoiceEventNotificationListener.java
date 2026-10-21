package com.easyfactor.notification.service;

import com.easyfactor.invoice.domain.event.InvoiceCreatedEvent;
import com.easyfactor.invoice.domain.event.InvoicePaidEvent;
import com.easyfactor.payment.domain.event.PaymentFailedEvent;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * Observes invoice/payment domain events after the transaction commits (Observer pattern).
 */
@Component
public class InvoiceEventNotificationListener {

    private final NotificationService notificationService;

    public InvoiceEventNotificationListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onInvoiceCreated(InvoiceCreatedEvent event) {
        // TODO: notificationService.sendInvoiceCreatedNotice(event)
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onInvoicePaid(InvoicePaidEvent event) {
        // TODO: notificationService.sendPaymentReceipt(event) + notifyOwnerPaymentReceived(event)
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onPaymentFailed(PaymentFailedEvent event) {
        // TODO: notificationService.notifyPaymentFailed(event)
    }
}
