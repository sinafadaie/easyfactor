CREATE TABLE products (
    id                      uuid PRIMARY KEY,
    owner_id                uuid NOT NULL REFERENCES owners (id),
    name                    varchar(255) NOT NULL,
    description             text,
    price_rials             bigint NOT NULL,
    status                  varchar(32) NOT NULL,
    digital_delivery_type   varchar(32) NOT NULL,
    digital_delivery_payload text,
    image_url               text,
    created_at              timestamptz NOT NULL,
    updated_at              timestamptz NOT NULL,
    version                 bigint NOT NULL
);

CREATE INDEX idx_products_owner_status ON products (owner_id, status);
