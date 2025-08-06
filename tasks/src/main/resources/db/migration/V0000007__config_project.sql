CREATE SEQUENCE project_step_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE project_task_type_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE project_steps (
    id INTEGER PRIMARY KEY DEFAULT nextval('project_step_seq'),
    status_name VARCHAR(255),
    color_code VARCHAR(50),
    step_order INTEGER,
    max_whip INTEGER,
    project_id INTEGER,
    editable BOOLEAN,
    CONSTRAINT fk_project_step_project
        FOREIGN KEY (project_id)
        REFERENCES projects (id)
);

CREATE TABLE project_task_type (
    id INTEGER PRIMARY KEY DEFAULT nextval('project_task_type_seq'),
    type_name VARCHAR(255),
    color_code VARCHAR(50),
    project_id INTEGER ,
    CONSTRAINT fk_task_type_project
        FOREIGN KEY (project_id)
        REFERENCES projects (id)
);