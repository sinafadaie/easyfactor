package com.easyfactor.invoice.domain;

import java.util.List;
import java.util.UUID;

/**
 * Creation input value for an Invoice. Customer data is a snapshot embedded in
 * the invoice (not a FK) — the customer entity section comes later and can be
 * linked then.
 */
public record InvoiceDraft(                 //A record in Java is a special kind of class used to
        String customerPhoneNumber,         //hold data in a simple, compact, mostly immutable way.
        String customerFirstName,
        String customerLastName,
        String customerAddress,
        String customerPostalCode,
        String notes,
        List<Item> items) {

    public record Item(
            UUID productId,
            String description,
            int quantity,
            long unitPrice) {
    }
}
