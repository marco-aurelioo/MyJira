# MyJira

## O que é o projeto?

## Como rodar:
A estrutura de diretorios do projeto segue:

1 - docker-compose: Diretorio com a estrutura basica necessária para o desenvolvimento sendo uma instancia de postgresql e a plataforma keycloak para autenticação
para executar deve rodar o comando :

´´´ docker-compose up ´´´

Pré requisitos: Docker compose 


2 - front-tasks: Diretorio com a aplicação de exemplo angula integrada com a plataforma de autenticação e com o backend java

´´´ ng serve ´´´

Pré requisitos: Docker compose, node 16, npm, angular 16, keycloak 25.

3 - tasks: Diretorio com aplicação backend java ira aplicar regras de comportamento da aplataforma tasks.

´´´ java -jar tasks-0.0.1-SNAPSHOT.jar ´´´

Pré requisitos: Docker compose, java 21, maven 3

