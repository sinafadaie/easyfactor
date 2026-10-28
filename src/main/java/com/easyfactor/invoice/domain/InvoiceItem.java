package com.easyfactor.invoice.domain;

import java.util.UUID;

import com.easyfactor.shared.error.BusinessException;
import com.easyfactor.shared.error.ErrorCode;

import lombok.Getter;

/**
 * Line item entity inside the Invoice aggregate.
 */
@Getter
public class InvoiceItem {

    private final UUID id;
    private final UUID productId;
    private final String description;
    private final int quantity;
    private final long unitPrice;
    private final long lineTotal;

    public InvoiceItem(UUID id, UUID productId, String description, int quantity, long unitPrice, long lineTotal) {
        this.id = id;
        this.productId = productId;
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lineTotal = lineTotal;
    }

    public static InvoiceItem create(UUID productId, String description, int quantity, long unitPrice) {
        if (quantity <= 0) {
            throw new BusinessException(ErrorCode.VALIDATION_FAILED, "quantity must be positive");
        }
        return new InvoiceItem(UUID.randomUUID(), productId, description, quantity, unitPrice, unitPrice * quantity);
    }
}
