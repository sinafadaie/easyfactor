package com.easyfactor.invoice.domain;

import java.util.Set;

/**
 * Invoice lifecycle with legal transitions (enum-based state machine).
 *
 * <pre>
 * DRAFT            -> SENT, CANCELLED
 * SENT             -> PENDING_PAYMENT, CANCELLED, EXPIRED
 * PENDING_PAYMENT  -> PAID, CANCELLED, EXPIRED
 * PAID / CANCELLED / EXPIRED -> terminal
 * </pre>
 */
public enum InvoiceStatus {

    DRAFT,
    SENT,
    PENDING_PAYMENT,
    PAID,
    CANCELLED,
    EXPIRED;

    public boolean canTransitionTo(InvoiceStatus target) {
        // TODO: implement via allowedNext()
        return false;
    }

    public boolean isTerminal() {
        // TODO
        return false;
    }

    public Set<InvoiceStatus> allowedNext() {
        // TODO
        return null;
    }
}
