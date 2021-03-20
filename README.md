# Microservices Template

Template para criação de microservices com SpringBoot.

## Introdução

Estas instruções possibilitarão ao desenvolvedor executar uma cópia deste projeto
localmente em seu computador.

## Índice
* [Stack](#pre-requisitos)
* [Instalação](#instalacao)
* [Deploy (AWS, IaC, Serverless)](#Deploy)
* [Endpoints](#endpoints)
* [Banco de dados (H2 Database)](#banco-de-dados-(h2-database))
* [Testes](#testes-)

## Stack

Tecnologias utilizadas:
* [Java JDK 1.8](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html) - Versão Java utilizada.
* [Maven 3](https://maven.apache.org/) - Gerenciamento de dependências e build.
* [Spring Boot 2.3](https://spring.io/projects/spring-boot) - Framework Java para a criação dos endpoints.
* [Lombok Plugin](https://projectlombok.org/) - Plugin para redução de verbosidade nos modelos.
* [IntelliJ IDEA](https://www.jetbrains.com/) - Ou outra IDE da sua escolha.
* [Git](https://git-scm.com/) - Sistema de versionamento.
* [NodeJS 12+](https://nodejs.org/en/download/) - Pré-requisito para *Serverless Framework* e scripts de deploy.
* [Serverless Framework](https://www.serverless.com/) - Infraestrutura como código, arquitetura serverless.

## Instalação
Todos os passos a seguir deverão ser executados na prompt de comando do sistema operacional.

Faça uma cópia local deste repositório:

```
git clone <SOLUTION_GIT_URI>
```

Instale o NodeJS caso ainda não tenha feito.

Instale o *Serverless Framework*:
```
npm install -g serverless
```

Navegue até a pasta do projeto e instale as dependências:
```
npm install
```

## Registro de credenciais

Obtenha ou gere as credenciais AWS do serviço (**ACCESS KEY ID** e **SECRET ACCESS KEY**).

Registre as credenciais localmente (atenção especial à sintaxe com espaços e traçoes duplos):

```
npm run set-service-credentials -- --key <ACCESS_KEY_ID> --secret <SECRET_ACCESS_KEY>
```

## Deploy
Execute o deploy a partir da pasta raíz do projeto
```
npm run deploy
```
Para remover a stack da AWS, execute:
```
npm run remove
```

## Endpoints
Documentação UI (Swagger/OpenApi)
```
http://localhost:8080/swagger-ui.html
```
Definição OpenApi (JSON)
```
http://localhost:8080/api-docs
```
Definição OpenApi (YML)
```
http://localhost:8080/api-docs.yaml
```

## Testes
Testes unitários:
```
npm run test
```

Testes de integração:
```
npm run test-integration-local
npm run test-integration-cloud
```
