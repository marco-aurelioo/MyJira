CREATE TABLE project_request (
    id BIGSERIAL PRIMARY KEY,
    project_id BIGINT NOT NULL REFERENCES projects(id),
    sender_id BIGINT NOT NULL REFERENCES user_profile(id),
    receiver_id BIGINT NOT NULL REFERENCES user_profile(id),
    status VARCHAR(10) NOT NULL CHECK (status IN ('PENDING', 'ACCEPTED', 'REJECTED')),
    message TEXT,
    response TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);