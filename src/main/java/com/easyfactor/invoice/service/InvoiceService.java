package com.easyfactor.invoice.service;

import java.time.Clock;
import java.util.UUID;

import com.easyfactor.invoice.domain.Invoice;
import com.easyfactor.invoice.domain.InvoiceDraft;
import com.easyfactor.invoice.domain.InvoiceNumberGenerator;
import com.easyfactor.invoice.domain.InvoiceRepository;
import com.easyfactor.invoice.domain.InvoiceStatus;
import com.easyfactor.invoice.domain.event.InvoiceCreatedEvent;
import com.easyfactor.invoice.domain.event.InvoicePaidEvent;
import com.easyfactor.shared.error.BusinessException;
import com.easyfactor.shared.error.ErrorCode;
import com.easyfactor.shared.error.NotFoundException;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create/update/pay/dispatch/cancel/query invoices (application service / orchestration).
 */
@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceNumberGenerator invoiceNumberGenerator;
    private final ApplicationEventPublisher eventPublisher;
    private final Clock clock;

    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceNumberGenerator invoiceNumberGenerator,
            ApplicationEventPublisher eventPublisher, Clock clock) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceNumberGenerator = invoiceNumberGenerator;
        this.eventPublisher = eventPublisher;
        this.clock = clock;
    }

    @Transactional
    public Invoice createInvoice(UUID ownerId, InvoiceDraft draft) {
        String number = invoiceNumberGenerator.nextNumber();
        Invoice invoice = Invoice.create(UUID.randomUUID(), number, ownerId, draft, clock.instant());
        invoiceRepository.save(invoice);
        eventPublisher.publishEvent(new InvoiceCreatedEvent(invoice.getId(), invoice.getInvoiceNumber(), ownerId,
                invoice.getTotal(), invoice.getCreatedAt()));
        return invoice;
    }

    @Transactional
    public Invoice updateInvoice(UUID ownerId, UUID invoiceId, InvoiceDraft draft) {
        Invoice invoice = getForOwner(ownerId, invoiceId);
        invoice.update(draft, clock.instant());
        return invoiceRepository.save(invoice);
    }

    @Transactional
    public Invoice markPaid(UUID ownerId, UUID invoiceId) {
        Invoice invoice = getForOwner(ownerId, invoiceId);
        invoice.markPaid(clock.instant());
        invoiceRepository.save(invoice);
        eventPublisher.publishEvent(new InvoicePaidEvent(invoice.getId(), invoice.getInvoiceNumber(), ownerId,
                invoice.getTotal(), invoice.getPaidAt()));
        return invoice;
    }

    @Transactional
    public Invoice markOnDispatching(UUID ownerId, UUID invoiceId) {
        Invoice invoice = getForOwner(ownerId, invoiceId);
        invoice.markOnDispatching(clock.instant());
        return invoiceRepository.save(invoice);
    }

    @Transactional
    public Invoice markDispatched(UUID ownerId, UUID invoiceId) {
        Invoice invoice = getForOwner(ownerId, invoiceId);
        invoice.markDispatched(clock.instant());
        return invoiceRepository.save(invoice);
    }

    @Transactional
    public Invoice cancelInvoice(UUID ownerId, UUID invoiceId, String reason) {
        Invoice invoice = getForOwner(ownerId, invoiceId);
        invoice.cancel(reason, clock.instant());
        return invoiceRepository.save(invoice);
    }

    public Invoice getForOwner(UUID ownerId, UUID invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new NotFoundException("invoice", invoiceId));
        if (!invoice.getOwnerId().equals(ownerId)) {
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }
        return invoice;
    }

    public Page<Invoice> listForOwner(UUID ownerId, InvoiceStatus status, Pageable pageable) {
        if (status == null) {
            return invoiceRepository.findByOwnerId(ownerId, pageable);
        }
        return invoiceRepository.findByOwnerIdAndStatus(ownerId, status, pageable);
    }

    public Invoice getByPublicToken(UUID publicToken) {
        return invoiceRepository.findByPublicToken(publicToken)
                .orElseThrow(() -> new NotFoundException("invoice", publicToken));
    }
}
