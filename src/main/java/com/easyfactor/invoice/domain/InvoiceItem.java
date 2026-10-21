package com.easyfactor.invoice.domain;

import java.util.UUID;

/**
 * Line item entity inside the Invoice aggregate.
 */
public class InvoiceItem {

    private final UUID id;
    private final UUID productId;
    private final String description;
    private final int quantity;
    private final long unitPriceRials;
    private final long lineTotalRials;

    public InvoiceItem(UUID id, UUID productId, String description, int quantity, long unitPriceRials, long lineTotalRials) {
        this.id = id;
        this.productId = productId;
        this.description = description;
        this.quantity = quantity;
        this.unitPriceRials = unitPriceRials;
        this.lineTotalRials = lineTotalRials;
    }

    public static InvoiceItem create(UUID productId, String description, int quantity, long unitPriceRials) {
        // TODO: lineTotalRials = unitPriceRials * quantity
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

    public long getUnitPriceRials() {
        return unitPriceRials;
    }

    public long getLineTotalRials() {
        return lineTotalRials;
    }
}
