CREATE SEQUENCE invoice_number_seq START 1;

CREATE TABLE invoices (
    id                     uuid PRIMARY KEY,
    invoice_number         varchar(64) NOT NULL UNIQUE,
    owner_id               uuid NOT NULL REFERENCES owners (id),
    customer_phone_number  varchar(32),
    customer_first_name    varchar(255),
    customer_last_name     varchar(255),
    customer_address       varchar(512),
    customer_postal_code   varchar(32),
    status                 varchar(32) NOT NULL,
    total                  bigint NOT NULL,
    notes                  text,
    public_token           uuid NOT NULL UNIQUE,
    cancel_reason          varchar(512),
    paid_at                timestamptz,
    dispatched_at          timestamptz,
    created_at             timestamptz NOT NULL,
    updated_at             timestamptz NOT NULL,
    version                bigint NOT NULL
);

CREATE INDEX idx_invoices_owner_status ON invoices (owner_id, status);

CREATE TABLE invoice_items (
    id                  uuid PRIMARY KEY,
    invoice_id          uuid NOT NULL REFERENCES invoices (id) ON DELETE CASCADE,
    product_id          uuid REFERENCES products (id),
    description         varchar(512) NOT NULL,
    quantity            integer NOT NULL CHECK (quantity > 0),
    unit_price          bigint NOT NULL,
    line_total          bigint NOT NULL,
    position            integer NOT NULL,       /*order of items*/
    created_at          timestamptz NOT NULL,
    updated_at          timestamptz NOT NULL,
    version             bigint NOT NULL
);

CREATE INDEX idx_invoice_items_invoice ON invoice_items (invoice_id);
CREATE INDEX idx_invoice_items_product ON invoice_items (product_id);
