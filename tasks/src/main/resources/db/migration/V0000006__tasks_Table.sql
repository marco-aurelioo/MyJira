CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    sequencia INTEGER,
    ordem INTEGER,
    titulo VARCHAR(255),
    descricao TEXT,
    data_inicio DATE,
    data_fim DATE,
    project_id INTEGER NOT NULL REFERENCES projects(id),
    task_owner_id INTEGER REFERENCES user_profile(id),
    created_by INTEGER REFERENCES user_profile(id)
);