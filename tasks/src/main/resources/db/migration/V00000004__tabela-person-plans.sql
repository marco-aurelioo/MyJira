CREATE TABLE person_plans (
    id SERIAL PRIMARY KEY,
    person_id INTEGER NOT NULL,
    plan_id INTEGER NOT NULL,
    start_date TIMESTAMP,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modify_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_person FOREIGN KEY (person_id) REFERENCES persons(id),
    CONSTRAINT fk_plan FOREIGN KEY (plan_id) REFERENCES subscription_plans(id)
);