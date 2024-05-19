-- Arquivo: init.sql

DO
$$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'database') THEN
        CREATE DATABASE database;
    END IF;
END
$$;

-- Criação do usuário keycloak se não existir
DO
$$
BEGIN
    BEGIN
        CREATE USER keycloak WITH PASSWORD 'keycloak';
    EXCEPTION WHEN others THEN
        RAISE NOTICE 'User keycloak already exists';
    END;
END
$$;

-- Criação do usuário taskTrack se não existir
DO
$$
BEGIN
    BEGIN
        CREATE USER taskTrack WITH PASSWORD 'taskTrack';
    EXCEPTION WHEN others THEN
        RAISE NOTICE 'User taskTrack already exists';
    END;
END
$$;

-- Criação do esquema keycloak se não existir
DO
$$
BEGIN
    BEGIN
        CREATE SCHEMA keycloak;
    EXCEPTION WHEN others THEN
        RAISE NOTICE 'Schema keycloak already exists';
    END;
END
$$;

-- Criação do esquema taskTrack se não existir
DO
$$
BEGIN
    BEGIN
        CREATE SCHEMA taskTrack;
    EXCEPTION WHEN others THEN
        RAISE NOTICE 'Schema taskTrack already exists';
    END;
END
$$;

-- Concede permissões para os usuários nos esquemas desejados
GRANT ALL PRIVILEGES ON SCHEMA keycloak TO keycloak;
GRANT ALL PRIVILEGES ON SCHEMA taskTrack TO taskTrack;
