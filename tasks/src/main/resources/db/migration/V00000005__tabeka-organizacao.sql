-- Tabela principal Organization
CREATE TABLE Organization (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    owner_id INTEGER,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modify_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (owner_id) REFERENCES Persons(id) ON DELETE SET NULL
);

-- Tabela de relacionamento para administradores (Many-to-Many)
CREATE TABLE organization_administrators (
    organization_id INTEGER NOT NULL,
    person_id INTEGER NOT NULL,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modify_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (organization_id, person_id),
    FOREIGN KEY (organization_id) REFERENCES Organization(id) ON DELETE CASCADE,
    FOREIGN KEY (person_id) REFERENCES Persons(id) ON DELETE CASCADE
);