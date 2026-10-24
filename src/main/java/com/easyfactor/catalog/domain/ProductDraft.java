package com.easyfactor.catalog.domain;

import java.util.Map;

/**
 * Creation/update input value for a Product.
 * Product contains fields that should be created/controlled by the system, not by the user.
 * A Draft holds only the part of the data that the user is allowed to define.
 * So the data the user sends is not the actual Product yet.
 * That’s why this proposal is named ProductDraft.
 * it is different from DTO!
 */


/*      INVOICE #1024                        Customer: Ali
        ─────────────────────────────────────────────────────
        #  Description                        Qty  Unit     Unit Price    Line Total
        ─────────────────────────────────────────────────────
        1  Nike Air — size 42, black          2    pair     85,000,000    170,000,000
        2  Gold Ring — 18k, 5.2g              1    gram     92,000,000     92,000,000
        ─────────────────────────────────────────────────────
        TOTAL:      262,000,000 Rial

        Nike Air — size 42, black comes from attributes

 */

public record ProductDraft(
        String name,
        String description,
        String category,
        String unit,
        Map<String, Object> attributes,
        long price,
        DigitalDeliveryType deliveryType,
        String deliveryPayload,
        String imageUrl,
        boolean active) {
}
