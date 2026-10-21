package com.easyfactor.invoice.persistence;

import java.time.Clock;
import java.util.UUID;

import com.easyfactor.invoice.domain.InvoiceNumberGenerator;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Postgres-sequence-backed invoice numbering: INV-{year}-{seq:08d}.
 */
@Component
public class DatabaseInvoiceNumberFactory implements InvoiceNumberGenerator {

    private final JdbcTemplate jdbcTemplate;
    private final Clock clock;

    public DatabaseInvoiceNumberFactory(JdbcTemplate jdbcTemplate, Clock clock) {
        this.jdbcTemplate = jdbcTemplate;
        this.clock = clock;
    }

    @Override
    public String nextNumber(UUID ownerId) {
        // TODO: SELECT nextval('invoice_number_seq')
        return null;
    }
}
