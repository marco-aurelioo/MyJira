CREATE TABLE invite_person (
    id SERIAL PRIMARY KEY,
    external_person_id VARCHAR(50) NOT NULL,
    project_id INT NOT NULL,
    from_person_id VARCHAR(50) NOT NULL,
    template TEXT NOT NULL,
    response TEXT,
    status VARCHAR(50) NOT NULL,
    CONSTRAINT fk_project FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE
);