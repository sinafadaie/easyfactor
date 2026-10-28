package com.easyfactor.invoice.persistence;

import java.time.Clock;
import java.time.Year;

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
    public String nextNumber() {                                                                                    //we can't use hibernate to load sequence from DB
        Long nextValue = jdbcTemplate.queryForObject("SELECT nextval('invoice_number_seq')", Long.class);       //so we used jdbcTemplate.queryForObject()
        int year = Year.now(clock).getValue();                                                                      //JdbcTemplate is Spring's lightweight wrapper around raw JDBC,
        return "INV-%d-%08d".formatted(year, nextValue);                                                            //and queryForObject is its method for running a query that returns exactly one row with one value
    }
}
