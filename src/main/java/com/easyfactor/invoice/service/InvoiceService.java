package com.easyfactor.invoice.service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.UUID;

import com.easyfactor.identity.domain.CustomerRepository;
import com.easyfactor.invoice.domain.Invoice;
import com.easyfactor.invoice.domain.InvoiceDraft;
import com.easyfactor.invoice.domain.InvoiceNumberGenerator;
import com.easyfactor.invoice.domain.InvoicePaymentInitiator;
import com.easyfactor.invoice.domain.InvoiceRepository;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Create/send/cancel/query invoices (application service / orchestration).
 */
@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final InvoiceNumberGenerator invoiceNumberGenerator;
    private final InvoicePaymentInitiator paymentInitiator;
    private final ApplicationEventPublisher eventPublisher;
    private final Clock clock;

    public InvoiceService(InvoiceRepository invoiceRepository, CustomerRepository customerRepository,
            InvoiceNumberGenerator invoiceNumberGenerator, InvoicePaymentInitiator paymentInitiator,
            ApplicationEventPublisher eventPublisher, Clock clock) {
        this.invoiceRepository = invoiceRepository;
        this.customerRepository = customerRepository;
        this.invoiceNumberGenerator = invoiceNumberGenerator;
        this.paymentInitiator = paymentInitiator;
        this.eventPublisher = eventPublisher;
        this.clock = clock;
    }

    public Invoice createInvoice(UUID ownerId, InvoiceDraft draft) {
        // TODO: load customer snapshot, generate number, Invoice.create, save, publish InvoiceCreatedEvent
        return null;
    }

    public Invoice sendInvoice(UUID ownerId, UUID invoiceId) {
        // TODO: ownership check, markSent, save, then paymentInitiator.initiatePayment(invoice)
        return null;
    }

    public Invoice cancelInvoice(UUID ownerId, UUID invoiceId, String reason) {
        // TODO
        return null;
    }

    public Invoice getForOwner(UUID ownerId, UUID invoiceId) {
        // TODO
        return null;
    }

    public Page<Invoice> listForOwner(UUID ownerId, Pageable pageable) {
        // TODO
        return null;
    }

    public Page<Invoice> listForCustomer(UUID customerId, Pageable pageable) {
        // TODO
        return null;
    }

    public Invoice getForCustomer(UUID customerId, UUID invoiceId) {
        // TODO
        return null;
    }

    void expireOverdue(LocalDate dueDate) {
        // TODO: expire SENT/PENDING_PAYMENT invoices past dueDate
    }
}
