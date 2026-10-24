CREATE TABLE payments (
    id                          uuid PRIMARY KEY,
    invoice_id                  uuid NOT NULL REFERENCES invoices (id),
    provider                    varchar(32) NOT NULL,
    status                      varchar(32) NOT NULL,
    provider_reference          varchar(255),
    telegram_payment_charge_id  varchar(255),
    amount                bigint NOT NULL,
    raw_payload                 jsonb,
    failure_reason              varchar(512),
    completed_at                timestamptz,
    created_at                  timestamptz NOT NULL,
    updated_at                  timestamptz NOT NULL,
    version                     bigint NOT NULL
);

-- Idempotency key: a Telegram charge may only be recorded once.
CREATE UNIQUE INDEX idx_payments_charge_id_unique ON payments (telegram_payment_charge_id)
    WHERE telegram_payment_charge_id IS NOT NULL;

CREATE INDEX idx_payments_invoice ON payments (invoice_id);

CREATE UNIQUE INDEX idx_payments_provider_reference_unique ON payments (provider, provider_reference)
    WHERE provider_reference IS NOT NULL;
