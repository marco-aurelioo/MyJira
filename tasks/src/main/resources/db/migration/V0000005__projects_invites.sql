CREATE TABLE request_participation (
    id BIGSERIAL PRIMARY KEY,
    project_id BIGINT NOT NULL REFERENCES projects(id),
    requesting_user_id BIGINT NOT NULL REFERENCES user_profile(id),
    approving_user_id BIGINT NOT NULL REFERENCES user_profile(id),
    message TEXT,
    response TEXT,
    status VARCHAR(10) NOT NULL CHECK (status IN ('PENDING', 'ACCEPTED', 'REJECTED')),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE invitation_projects (
    id BIGSERIAL PRIMARY KEY,
    project_id BIGINT NOT NULL REFERENCES projects(id),
    guest_user_id BIGINT NOT NULL REFERENCES user_profile(id),
    inviting_user_id  BIGINT NOT NULL REFERENCES user_profile(id),
    message TEXT,
    response TEXT,
    status VARCHAR(10) NOT NULL CHECK (status IN ('PENDING', 'ACCEPTED', 'REJECTED')),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);