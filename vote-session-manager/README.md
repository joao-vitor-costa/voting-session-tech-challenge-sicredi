
# Gerenciador da sessão de votação

## Contexto

Aplicação para gerenciar uma sessão de votação , onde podemos criar uma pauta de votação e lista as pautas criadas e para realizar uma votação é preciso abrir uma sessão de votação em cima de uma pauta, a sessão por default terá um tempo de 1 minuto, mas caso deseja ter um tempo maior é possível configurar o tempo da sessão no ato da criação. Para poder votar é preciso ter o id do associado que é gerado no [ms-associate-registration-manager]( https://github.com/joao-vitor-costa/voting-session-tech-challenge/tree/main/associate-registration-manager) e também é preciso do id da pauta, com isso pode ser efetuado uma votação, na votação é validado se o associado pe hapto a votar, apoós a votação acabar é possivel obter o resultado de um votação passando o id da pauta que foi votada.

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
- Flyway

## Banco de dados
- Mysql 8.0

### Rodar localmente o banco de dados
```
 docker-compose up -d
```
### Diagrama de entidade e relacionamento do banco de dados
![der_vote_session_manager](https://user-images.githubusercontent.com/23365048/182729963-bd621414-cbd9-4d51-b6c5-d348060cf3f6.png)
#### A aplicação possui o flyway, portando quando aplicação é iniciada é implementado os scripts de migração no banco de dados, caso o nome do banco de dados não exista irá ser criado, para mais informações consultar o arquivo de [migration](https://github.com/joao-vitor-costa/voting-session-tech-challenge/tree/main/vote-session-manager/src/main/resources/db/migration).
Por padrão a aplicação não está com a configuração de configuração de criação ou atualização de ddl do JPA caso precise alterar uma tabela utilizar a pasta migration.
```
 jpa:
    hibernate:
      ddl-auto: none
```

## Utilizando a aplicação via swagger
Acesse em um browser o [swagger-ui](http://localhost:8089/swagger-ui/index.html)

## Health Check
Acesse em um browser o [health](http://localhost:8089/actuator/health)

