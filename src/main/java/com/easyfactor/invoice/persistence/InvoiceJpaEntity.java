package com.easyfactor.invoice.persistence;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

import com.easyfactor.shared.persistence.AuditableJpaEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoices")
@Getter
@Setter
public class InvoiceJpaEntity extends AuditableJpaEntity {

    @Column(name = "invoice_number", nullable = false, unique = true, length = 64)
    private String invoiceNumber;

    @Column(name = "owner_id", nullable = false)
    private UUID ownerId;

    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @Column(name = "customer_telegram_user_id")
    private Long customerTelegramUserId;

    @Column(name = "customer_display_name")
    private String customerDisplayName;

    @Column(name = "status", nullable = false, length = 32)
    private String status;

    @Column(name = "total", nullable = false)
    private long total;

    @Column(name = "payment_provider", nullable = false, length = 32)
    private String paymentProvider;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "notes", columnDefinition = "text")
    private String notes;

    @Column(name = "sent_at")
    private Instant sentAt;

    @Column(name = "paid_at")
    private Instant paidAt;

    @Column(name = "cancel_reason", length = 512)
    private String cancelReason;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("position ASC")
    private List<InvoiceItemJpaEntity> items = new ArrayList<>();
}
