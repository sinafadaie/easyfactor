package com.easyfactor.notification.service;

import com.easyfactor.catalog.domain.ProductRepository;
import com.easyfactor.invoice.domain.InvoiceRepository;
import com.easyfactor.invoice.domain.event.InvoicePaidEvent;
import com.easyfactor.telegram.domain.TelegramBotApiClient;

import org.springframework.stereotype.Service;

/**
 * Delivers digital goods (link/text/file) for product-backed line items after payment.
 * MVP: in-memory delivery, no persisted attempts (see outbox risk note).
 */
@Service
public class DigitalDeliveryService {

    private final InvoiceRepository invoiceRepository;
    private final ProductRepository productRepository;
    private final TelegramBotApiClient botApiClient;

    public DigitalDeliveryService(InvoiceRepository invoiceRepository, ProductRepository productRepository,
            TelegramBotApiClient botApiClient) {
        this.invoiceRepository = invoiceRepository;
        this.productRepository = productRepository;
        this.botApiClient = botApiClient;
    }

    public void deliver(InvoicePaidEvent event) {
        // TODO: load invoice items with productId, send each product's deliveryPayload to the customer chat
    }
}
