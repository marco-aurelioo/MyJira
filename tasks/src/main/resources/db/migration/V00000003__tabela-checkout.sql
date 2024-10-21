CREATE TABLE checkout (
    id SERIAL PRIMARY KEY,
    external_id UUID NOT NULL,
    pessoa VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    carrinho_json JSONB NOT NULL,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modify_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (external_id)
);

CREATE INDEX idx_checkout_carrinho_json ON checkout USING gin (carrinho_json);

CREATE INDEX idx_checkout_external_id ON checkout (external_id);