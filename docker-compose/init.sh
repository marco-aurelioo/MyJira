#!/bin/bash

# Executa o script SQL

PGPASSWORD=$POSTGRES_PASSWORD psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" -h postgres -f /docker-entrypoint-initdb.d/init.sql

