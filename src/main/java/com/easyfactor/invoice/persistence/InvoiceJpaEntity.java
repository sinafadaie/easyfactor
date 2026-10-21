package com.easyfactor.invoice.persistence;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Column(name = "currency", nullable = false, length = 8)
    private String currency;

    @Column(name = "total_amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal totalAmount;

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

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerTelegramUserId() {
        return customerTelegramUserId;
    }

    public void setCustomerTelegramUserId(Long customerTelegramUserId) {
        this.customerTelegramUserId = customerTelegramUserId;
    }

    public String getCustomerDisplayName() {
        return customerDisplayName;
    }

    public void setCustomerDisplayName(String customerDisplayName) {
        this.customerDisplayName = customerDisplayName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentProvider() {
        return paymentProvider;
    }

    public void setPaymentProvider(String paymentProvider) {
        this.paymentProvider = paymentProvider;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Instant getSentAt() {
        return sentAt;
    }

    public void setSentAt(Instant sentAt) {
        this.sentAt = sentAt;
    }

    public Instant getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Instant paidAt) {
        this.paidAt = paidAt;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public List<InvoiceItemJpaEntity> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItemJpaEntity> items) {
        this.items = items;
    }
}
