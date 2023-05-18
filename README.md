# Wishlist

## Introdução

Uma wishlist de produtos.

## Requisitos

- Java 17
- Docker
- docker compose

Execute os comandos abaixo para subir a aplicação localmente.

1. `mvnw clean install -DskipTests` (depende da aplicação iniciada para que os testes funcionem)
2. `mvnw spring-boot:build-image`
3. `docker compose up -d` (ou `docker-compose up -d`)

## Testes

Utilização de BDD com [Cucumber](https://cucumber.io/) para definição da especificação de casos de uso.

Os arquivos de testes se encontram dentro do diretório `~/src/test/resources/steps`.

É necessário que os serviços estejam rodando para conectar na instância local e executar os casos de testes contra ela.

Para rodar os testes via linha de comando, execute o seguinte comando: `mvnw failsafe:integration-test failsafe:verify`

O resultado da execução dos testes são exibidos no console e também publicados na 
página de relatório em https://reports.cucumber.io.

## Docs

Após subir a aplicação, a documentação da API está exposta em http://localhost:8080/api/v1/docs.

## Checkstyle

Para manter o código padronizado e não criar vários estilos completamente diferentes de desenvolvimento
foi utilizado [Checkstyle](https://checkstyle.sourceforge.io/).
