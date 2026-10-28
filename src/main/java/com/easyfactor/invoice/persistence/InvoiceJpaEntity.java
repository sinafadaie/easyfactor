package com.easyfactor.invoice.persistence;

import java.time.Instant;
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

    @Column(name = "customer_phone_number", length = 32)
    private String customerPhoneNumber;

    @Column(name = "customer_first_name", length = 255)
    private String customerFirstName;

    @Column(name = "customer_last_name", length = 255)
    private String customerLastName;

    @Column(name = "customer_address", length = 512)
    private String customerAddress;

    @Column(name = "customer_postal_code", length = 32)
    private String customerPostalCode;

    @Column(name = "status", nullable = false, length = 32)
    private String status;

    @Column(name = "total", nullable = false)
    private long total;

    @Column(name = "notes", columnDefinition = "text")
    private String notes;

    @Column(name = "public_token", nullable = false, unique = true)
    private UUID publicToken;

    @Column(name = "cancel_reason", length = 512)
    private String cancelReason;

    @Column(name = "paid_at")
    private Instant paidAt;

    @Column(name = "dispatched_at")
    private Instant dispatchedAt;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("position ASC")
    private List<InvoiceItemJpaEntity> items = new ArrayList<>();
}
