CREATE TABLE owners (
    id                  uuid PRIMARY KEY,
    telegram_user_id    bigint NOT NULL UNIQUE,
    telegram_username   varchar(64),
    first_name          varchar(255),
    last_name           varchar(255),
    language_code       varchar(16),
    shop_name           varchar(255),
    created_at          timestamptz NOT NULL,
    updated_at          timestamptz NOT NULL,
    version             bigint NOT NULL
);

CREATE TABLE customers (
    id                  uuid PRIMARY KEY,
    telegram_user_id    bigint UNIQUE,
    telegram_username   varchar(64),
    first_name          varchar(255),
    last_name           varchar(255),
    phone_number        varchar(32) UNIQUE,
    language_code       varchar(16),
    created_at          timestamptz NOT NULL,
    updated_at          timestamptz NOT NULL,
    version             bigint NOT NULL
);

CREATE TABLE addresses (
    id                  uuid PRIMARY KEY,
    customer_id         uuid NOT NULL REFERENCES customers (id) ON DELETE CASCADE,
    label               varchar(64),
    line1               varchar(255) NOT NULL,
    line2               varchar(255),
    city                varchar(128),
    state               varchar(128),
    postal_code         varchar(32),
    country             varchar(128),
    created_at          timestamptz NOT NULL,
    updated_at          timestamptz NOT NULL,
    version             bigint NOT NULL
);

CREATE INDEX idx_addresses_customer ON addresses (customer_id);
