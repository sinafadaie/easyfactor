CREATE SEQUENCE invoice_number_seq START 1;

CREATE TABLE invoices (
    id                        uuid PRIMARY KEY,
    invoice_number            varchar(64) NOT NULL UNIQUE,
    owner_id                  uuid NOT NULL REFERENCES owners (id),
    customer_id               uuid NOT NULL REFERENCES customers (id),
    customer_telegram_user_id bigint,
    customer_display_name     varchar(255),
    status                    varchar(32) NOT NULL,
    total                      bigint NOT NULL,
    payment_provider          varchar(32) NOT NULL,
    due_date                  date,
    notes                     text,
    sent_at                   timestamptz,
    paid_at                   timestamptz,
    cancel_reason             varchar(512),
    created_at                timestamptz NOT NULL,
    updated_at                timestamptz NOT NULL,
    version                   bigint NOT NULL
);

CREATE INDEX idx_invoices_owner_status ON invoices (owner_id, status);
CREATE INDEX idx_invoices_customer ON invoices (customer_id);
CREATE INDEX idx_invoices_status_due_date ON invoices (status, due_date);

CREATE TABLE invoice_items (
    id                  uuid PRIMARY KEY,
    invoice_id          uuid NOT NULL REFERENCES invoices (id) ON DELETE CASCADE,
    product_id          uuid REFERENCES products (id),
    description         varchar(512) NOT NULL,
    quantity            integer NOT NULL CHECK (quantity > 0),
    unit_price          bigint NOT NULL,
    line_total          bigint NOT NULL,
    position            integer NOT NULL,
    created_at          timestamptz NOT NULL,
    updated_at          timestamptz NOT NULL,
    version             bigint NOT NULL
);

CREATE INDEX idx_invoice_items_invoice ON invoice_items (invoice_id);
CREATE INDEX idx_invoice_items_product ON invoice_items (product_id);
