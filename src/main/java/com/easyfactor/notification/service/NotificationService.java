package com.easyfactor.notification.service;

import com.easyfactor.invoice.domain.event.InvoiceCreatedEvent;
import com.easyfactor.invoice.domain.event.InvoicePaidEvent;
import com.easyfactor.payment.domain.event.PaymentFailedEvent;
import com.easyfactor.telegram.domain.TelegramBotApiClient;

import org.springframework.stereotype.Service;

/**
 * Messaging facade: builds and sends invoice status messages, receipts and owner alerts.
 */
@Service
public class NotificationService {

    private final TelegramBotApiClient botApiClient;

    public NotificationService(TelegramBotApiClient botApiClient) {
        this.botApiClient = botApiClient;
    }

    public void sendInvoiceCreatedNotice(InvoiceCreatedEvent event) {
        // TODO
    }

    public void sendPaymentReceipt(InvoicePaidEvent event) {
        // TODO: receipt message to the customer chat
    }

    public void notifyOwnerPaymentReceived(InvoicePaidEvent event) {
        // TODO: alert message to the owner
    }

    public void notifyPaymentFailed(PaymentFailedEvent event) {
        // TODO
    }
}
