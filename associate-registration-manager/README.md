
# Gerenciador de registro do associado

## Contexto

Aplicação para gerenciar o registro do associado, onde é possivel cadastrar novos associados ou bucar um associado por um id unico.

## Tecnologias  
- Java 17
- Spring Boot 2.7.2
- Spring Cloud
- Maven 3.8.1
- Junit 5
- Mockito
- MapsStruct
- Lombok
- Docker

## Banco de dados
- MongoDB 3.4

### Rodar localmente o banco de dados
```
 docker-compose up -d
```

## Utilizando a aplicação via swagger
Acesse em um browser a url http://localhost:8080/swagger-ui/index.html

## Health Check
Acesse em um browser a url http://localhost:8080/actuator/health
