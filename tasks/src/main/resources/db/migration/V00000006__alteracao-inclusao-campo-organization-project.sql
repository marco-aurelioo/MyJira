ALTER TABLE projects
ADD COLUMN organization_id INT NOT NULL;

ALTER TABLE projects
ADD CONSTRAINT fk_organization
FOREIGN KEY (organization_id)
REFERENCES Organization(id);