#!/bin/bash
set -e

# Verifica se o PostgreSQL já está em execução
if pg_isready -q -h localhost -p 5432; then
    echo "PostgreSQL is already running."
else
    # Inicia o PostgreSQL em segundo plano
    docker-entrypoint.sh postgres &

    # Usa wait-for-it.sh para aguardar o PostgreSQL estar pronto
    /docker-entrypoint-initdb.d/wait-for-it.sh localhost:5432 --timeout=60 --strict -- echo "PostgreSQL is up"
fi

# Executa o script de inicialização
/docker-entrypoint-initdb.d/init.sh

# Mantém o contêiner ativo
wait
