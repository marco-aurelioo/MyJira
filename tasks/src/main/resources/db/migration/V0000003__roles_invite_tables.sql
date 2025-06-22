CREATE TABLE project_role (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE permission (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);


CREATE TABLE role_permission (
    id BIGSERIAL PRIMARY KEY,
    role_id BIGINT REFERENCES project_role(id) ON DELETE CASCADE,
    permission_id BIGINT REFERENCES permission(id) ON DELETE CASCADE,
    UNIQUE(role_id, permission_id)
);


CREATE TABLE project_member (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES user_profile(id) ON DELETE CASCADE,
    project_id BIGINT REFERENCES projects(id) ON DELETE CASCADE,
    role_id BIGINT REFERENCES project_role(id) ON DELETE CASCADE,
    UNIQUE(user_id, project_id)
);

-- Inserção de dados de exemplo (para cargos e permissões)
-- Cargos no projeto
INSERT INTO project_role (name) VALUES ('Manager');
INSERT INTO project_role (name) VALUES  ('Developer');
INSERT INTO project_role (name) VALUES ('Designer');

-- Permissões no sistema
INSERT INTO permission (name) VALUES ('Approve Participation');
INSERT INTO permission (name) VALUES ('Invite Users');
INSERT INTO permission (name) VALUES ('Manage Tasks');
INSERT INTO permission (name) VALUES ('View Statistics');

-- Atribuição de permissões aos cargos
INSERT INTO role_permission (role_id, permission_id)
VALUES
    ((SELECT id FROM project_role WHERE name = 'Manager'),
    (SELECT id FROM permission WHERE name = 'Approve Participation'));

INSERT INTO role_permission (role_id, permission_id)
    VALUES
    ((SELECT id FROM project_role WHERE name = 'Manager'),
    (SELECT id FROM permission WHERE name = 'Invite Users'));

INSERT INTO role_permission (role_id, permission_id)
    VALUES
    ((SELECT id FROM project_role WHERE name = 'Developer'),
    (SELECT id FROM permission WHERE name = 'Manage Tasks'));
INSERT INTO role_permission (role_id, permission_id)
    VALUES
    ((SELECT id FROM project_role WHERE name = 'Designer'),
     (SELECT id FROM permission WHERE name = 'View Statistics'));
