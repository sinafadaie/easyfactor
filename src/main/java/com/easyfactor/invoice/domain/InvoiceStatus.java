package com.easyfactor.invoice.domain;

import java.util.Set;

/**
 * Invoice lifecycle with legal transitions (enum-based state machine).
 *
 * The single chain drives both concerns: the panel's dispatch section filters
 * by this status, and "paid" is derived (WAIT_FOR_PAYMENT = not paid, anything
 * after it = paid).
 *
 * <pre>
 * WAIT_FOR_PAYMENT  -> WAIT_FOR_DISPATCH (owner marks paid), CANCELLED
 * WAIT_FOR_DISPATCH -> ON_DISPATCHING, CANCELLED
 * ON_DISPATCHING    -> DISPATCHED
 * DISPATCHED / CANCELLED -> terminal
 * </pre>
 */
public enum InvoiceStatus {

    WAIT_FOR_PAYMENT,
    WAIT_FOR_DISPATCH,
    ON_DISPATCHING,
    DISPATCHED,
    CANCELLED;

    public boolean canTransitionTo(InvoiceStatus target) {
        return allowedNext().contains(target);
    }

    public boolean isTerminal() {
        return this == DISPATCHED || this == CANCELLED;
    }

    public Set<InvoiceStatus> allowedNext() {
        return switch (this) {
            case WAIT_FOR_PAYMENT -> Set.of(WAIT_FOR_DISPATCH, CANCELLED);
            case WAIT_FOR_DISPATCH -> Set.of(ON_DISPATCHING, CANCELLED);
            case ON_DISPATCHING -> Set.of(DISPATCHED);
            case DISPATCHED, CANCELLED -> Set.of();
        };
    }
}
