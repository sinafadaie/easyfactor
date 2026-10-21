package com.easyfactor.invoice.domain;

import java.util.UUID;

import com.easyfactor.shared.domain.Money;

/**
 * Line item entity inside the Invoice aggregate.
 */
public class InvoiceItem {

    private final UUID id;
    private final UUID productId;
    private final String description;
    private final int quantity;
    private final Money unitPrice;
    private final Money lineTotal;

    public InvoiceItem(UUID id, UUID productId, String description, int quantity, Money unitPrice, Money lineTotal) {
        this.id = id;
        this.productId = productId;
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lineTotal = lineTotal;
    }

    public static InvoiceItem create(UUID productId, String description, int quantity, Money unitPrice) {
        // TODO: lineTotal = unitPrice * quantity
        return null;
    }

    public UUID getId() {
        return id;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getUnitPrice() {
        return unitPrice;
    }

    public Money getLineTotal() {
        return lineTotal;
    }
}
