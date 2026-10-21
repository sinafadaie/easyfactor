package com.easyfactor.invoice.service;

import java.time.Clock;
import java.time.LocalDate;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Periodically expires overdue invoices.
 */
@Component
public class InvoiceExpirationScheduler {

    private final InvoiceService invoiceService;
    private final Clock clock;

    public InvoiceExpirationScheduler(InvoiceService invoiceService, Clock clock) {
        this.invoiceService = invoiceService;
        this.clock = clock;
    }

    @Scheduled(cron = "0 7 * * * *")
    public void expireOverdueInvoices() {
        // TODO: invoiceService.expireOverdue(LocalDate.now(clock))
    }
}
