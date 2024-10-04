-- Criação da tabela subscription_plans
CREATE TABLE subscription_plans (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    alias VARCHAR(255) NOT NULL,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modify_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Criação da tabela features_plataform
CREATE TABLE features_plataform (
    id SERIAL PRIMARY KEY,
    role VARCHAR(255) NOT NULL,
    description TEXT,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modify_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de junção para a relação N:N entre subscription_plans e features_plataform
CREATE TABLE subscription_plan_features (
    subscription_plan_id INTEGER REFERENCES subscription_plans(id) ON DELETE CASCADE,
    features_plataform_id INTEGER REFERENCES features_plataform(id) ON DELETE CASCADE,
    PRIMARY KEY (subscription_plan_id, features_plataform_id),
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modify_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
