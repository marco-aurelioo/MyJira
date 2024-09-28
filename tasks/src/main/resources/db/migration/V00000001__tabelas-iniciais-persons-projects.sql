CREATE TABLE persons (
    id SERIAL PRIMARY KEY,             -- ID gerado automaticamente
    user_id VARCHAR(255) NOT NULL,      -- Id de referencia de user autenticados
    name VARCHAR(255) NOT NULL,        -- Nome da pessoa
    avatar VARCHAR(255),               -- Avatar da pessoa
    create_date TIMESTAMP,             -- Data de criação (herdada de BaseEntity)
    modify_date TIMESTAMP              -- Data de modificação (herdada de BaseEntity)
);

CREATE TABLE projects (
    id SERIAL PRIMARY KEY,             -- ID gerado automaticamente
    project_name VARCHAR(255) NOT NULL,-- Nome do projeto
    project_alias VARCHAR(255),        -- Alias do projeto
    description TEXT,                  -- Descrição do projeto
    owner_id INTEGER,                  -- ID do proprietário (referência a persons)
    create_date TIMESTAMP,             -- Data de criação (herdada de BaseEntity)
    modify_date TIMESTAMP,             -- Data de modificação (herdada de BaseEntity)
    CONSTRAINT fk_owner FOREIGN KEY (owner_id) REFERENCES persons(id) -- Chave estrangeira para o proprietário
);


CREATE TABLE person_projects (
    person_id INTEGER NOT NULL,
    project_id INTEGER NOT NULL,
    CONSTRAINT fk_person FOREIGN KEY (person_id) REFERENCES persons(id),
    CONSTRAINT fk_project FOREIGN KEY (project_id) REFERENCES projects(id),
    PRIMARY KEY (person_id, project_id)
);
