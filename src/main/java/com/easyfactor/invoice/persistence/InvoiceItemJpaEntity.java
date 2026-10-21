package com.easyfactor.invoice.persistence;

import java.math.BigDecimal;
import java.util.UUID;

import com.easyfactor.shared.persistence.AuditableJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoice_items")
public class InvoiceItemJpaEntity extends AuditableJpaEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "invoice_id", nullable = false)
    private InvoiceJpaEntity invoice;

    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "description", nullable = false, length = 512)
    private String description;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unit_price_amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal unitPriceAmount;

    @Column(name = "line_total_amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal lineTotalAmount;

    @Column(name = "currency", nullable = false, length = 8)
    private String currency;

    @Column(name = "position", nullable = false)
    private Integer position;

    public InvoiceJpaEntity getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceJpaEntity invoice) {
        this.invoice = invoice;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPriceAmount() {
        return unitPriceAmount;
    }

    public void setUnitPriceAmount(BigDecimal unitPriceAmount) {
        this.unitPriceAmount = unitPriceAmount;
    }

    public BigDecimal getLineTotalAmount() {
        return lineTotalAmount;
    }

    public void setLineTotalAmount(BigDecimal lineTotalAmount) {
        this.lineTotalAmount = lineTotalAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
