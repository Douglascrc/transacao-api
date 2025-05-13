# API  de Transações

Este é projeto é uma API REST simples para gerenciar transações e calcular as estatíscas das transações. Desenvolvida em Java com o framework Spring Boot.


## Tencologias usadas 
  - Java 21
  - Spring Boot
  - Lombok
  - Swagger/OpenAPI para documentação
  - JUnit e Mockito

## Pré-requisitos
 - Java JDK 21
 - Maven
 - Docker (Opcional)

## Como executar o projeto
  1. Clone o repositório
  ````bash
    git clone <url-do-repositório>
 ````
  2. Compile o projeto
  ```bash
    mvn clean install
  ``` 
3. Execute o projeto
  ```bash
    mvn spring-boot:run
  ```

## Executando com Docker
  1. Construa a imagem:  
  ```bash
    docker build -t <nome-da-imagem> .
  ```
  2. Execute o container:  
 ```bash
    docker run -p 8080:8080 <nome-da-imagem>
  ```

## Documentação da API
 A documentação de todos os endpoints está disponível através do Swagger-UI:
 http://localhost:8080/swagger-ui/index.html
