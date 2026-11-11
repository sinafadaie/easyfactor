ALTER TABLE customers ADD COLUMN owner_id uuid;                        -- nullable first
ALTER TABLE customers ALTER COLUMN owner_id SET NOT NULL;

ALTER TABLE customers DROP CONSTRAINT customers_phone_number_key;      -- remove global unique
ALTER TABLE customers ADD CONSTRAINT uq_customers_owner_phone UNIQUE (owner_id, phone_number);

ALTER TABLE invoices ADD COLUMN customer_id uuid REFERENCES customers (id);  -- nullable on purpose