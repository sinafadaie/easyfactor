CREATE TABLE payment_cards (
    id          uuid PRIMARY KEY,
    owner_id    uuid NOT NULL REFERENCES owners (id),
    card_number varchar(16) NOT NULL,
    bank_name   varchar(64),
    created_at  timestamptz NOT NULL,
    updated_at  timestamptz NOT NULL,
    version     bigint NOT NULL
);

CREATE INDEX idx_payment_cards_owner ON payment_cards (owner_id);
