-- Tabela para armazenar templates de mensagens internas
CREATE TABLE templates_internal_messages (
    id SERIAL PRIMARY KEY,
    external_id VARCHAR(255) UNIQUE NOT NULL,  -- Identificador externo único do template
    owner VARCHAR(255) NOT NULL,
    content TEXT,
    template TEXT NOT NULL,                    -- Conteúdo do template
    create_date TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,  -- Data de criação
    modify_date TIMESTAMPTZ                     -- Data de modificação
);

-- Índices para consultas
CREATE INDEX idx_external_id_template ON templates_internal_messages (external_id);
CREATE INDEX idx_owner_template ON templates_internal_messages (owner);

-- Tabela para armazenar mensagens internas
CREATE TABLE internal_messages (
    id SERIAL PRIMARY KEY,
    external_id VARCHAR(255) UNIQUE NOT NULL,  -- Identificador externo único da mensagem
    from_user VARCHAR(255) NOT NULL,           -- Remetente da mensagem
    to_user VARCHAR(255) NOT NULL,
    status VARCHAR(255),
    title  VARCHAR(255),
    template_id INT REFERENCES templates_internal_messages(id) ON DELETE SET NULL,
    attributes BYTEA,                          -- Atributos da mensagem em formato BLOB
    create_date TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,  -- Data de criação
    modify_date TIMESTAMPTZ                     -- Data de modificação
);

-- Índices adicionais para consultas
CREATE INDEX idx_to_user ON internal_messages (to_user);
CREATE INDEX idx_template_id ON internal_messages (template_id);
CREATE INDEX idx_external_id_message ON internal_messages (external_id);
