# Sistema de votação de pautas - Desafio Tecnologico da Sicredi

## Contexto
O Sistema de votação de pauta consiste em fornecer um ecossistema onde um associado de uma cooperativa podem realizar uma votação em cima de uma pauta em uma assembleia. Esse ecossistema consite em uma arquitetura de microsserviços, onde cada serviço tem um dominio especifico e com suas responsabilidas, o dominio de gerenciar um associado, ou seja, cadastrar novos associado consiste no [ms-associate-registration-manager](https://github.com/joao-vitor-costa/voting-session-tech-challenge/blob/main/associate-registration-manager/README.md) e o dominio de gerenciar a sessão de votação na assembleia consiste no [ms-vote-session-manager](https://github.com/joao-vitor-costa/voting-session-tech-challenge/blob/main/vote-session-manager/README.md), a responsabilidade desse serviço é criar pauta, criar sessões de votação em cima de pauta e permitir que associados votem, além de validar se um associado está apto a votar. Para exemplificar a arquitetura pode ser observado na imagem abaixo. Um ponto de atenção que a aplicação api-gateway não faz parte da solução que foi implementada é apenas uma exemplificação do sistema de votação em um ecossistema maior. 
![diagrama_microservicos](https://user-images.githubusercontent.com/23365048/182738369-96786655-3dc1-4a00-9f24-cd63c601a450.png)


## Arquitetura de Software 

Cada aplicação foi desenvoldida utilizando os conceitos de arquitetura limpa, portando o [ms-associate-registration-manager](https://github.com/joao-vitor-costa/voting-session-tech-challenge/blob/main/associate-registration-manager/README.md) e o [ms-vote-session-manager](https://github.com/joao-vitor-costa/voting-session-tech-challenge/blob/main/vote-session-manager/README.md) possuem a organização de pacotes se embasando no conceito de arquituta limpa. Para exemplicar melhor, segue uma imagem que mostra as camadas utilidas na arquitetura limpa.

![clean_architecture](https://user-images.githubusercontent.com/23365048/182738578-060f0724-92ca-463f-85e8-23c2bb463fcf.png)

Para este projeto utilizamos as camadas core, entrypoint e dataprovider, portanto toda a regra de negocio fica na camada de core e para gerenciar essas regras são utilizado casos de usos, já na camada dataprovider é utilizada para prover dados, ou seja, essa camada na aplicação irá buscar informações de banco de dados ou de aplicações que estejam na nuvem, já a camada entrypoint é  a camada que fica exposta para o usuario e possui as configurações da API REST.

## Tecnologias 

Para este projeto é utilizado Java 19 e Spring boot e maven para gerenciar as dependências. Para o banco de dados é usado banco de dados relacional para o [ms-vote-session-manager](https://github.com/joao-vitor-costa/voting-session-tech-challenge/blob/main/vote-session-manager/README.md), esse serviço possui banco relacional devido o relacionamento que tem entre as entidades pauta, sessão e voto, já o  [ms-associate-registration-manager](https://github.com/joao-vitor-costa/voting-session-tech-challenge/blob/main/associate-registration-manager/README.md) é utilizado um banco de dados não relacional, pois o serviço que gerencia o associado só tem a entidade associado, além disso foi tomado essa decisão também devido o volume de associado que uma cooperativa pode ter, portando com o banco não relacional é possivel ter uma melhor perfomance. Para mais informações deve consultar o README.md de cada serviço.

### Requisitos do sistema
Para executar o projeto é preciso cumprir os seguintes requisitos:
- Docker 
- JDK 19
- Maven
- IntelliJ IDEA 
- Git

### Subir aplicação localmente
#### Clonar o repositorio 
```
git clone git@github.com:joao-vitor-costa/voting-session-tech-challenge.git
```
#### Executar o docker-compose
Em cada microsserviço existe um arquivo chamado docker-compose.yml, onde esse arquivo irá subir uma imagem do banco de dados de cada microsserviço em seu respectivo ecossistema, para subir o banco de dados apenas precisa dar o comando o comando abaixo dentro da pasta raiz de cada microsserviço:
```
 docker-compose up -d
```
#### Excecutar a aplicação
Para executar aplicação será preciso utilizar o IntelliJ IDEA para subir localmente.

## Instruções para votar no sistema
Para poder votar é preciso ser um associado, portando é preciso acessa o  [ms-associate-registration-manager](https://github.com/joao-vitor-costa/voting-session-tech-challenge/blob/main/associate-registration-manager/README.md) para cadastrar um associado pode realizar as chamadas direto pelo Postman por exemplo, ou pode acessar o [swagger-ui](http://localhost:8080/swagger-ui/index.html) da aplicação.Portanto seiga os passos abaixo para criar um associado e votar em uma pauta.

#### Cadastrar um novo associado
Para cadastrar um novo associado precisamos de um nome um CPF, para o CPF podemos usar o [Gerador de CPF](https://www.4devs.com.br/gerador_de_cpf), é importante gerar o cpf sem a pontuação.
Requisição para cadastrar:
```
curl -X 'POST' \
  'http://localhost:8080/v1/associates' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "João Vitor Almeida Costa",
  "cpf": 11844420299
}'

```
Corpo da requisição:
```
{
  "name": "João Vitor Almeida Costa",
  "cpf": 11844420299
}
```
Resposta da API:
```
{
  "id": "62eb1c3e4b1d07100d4d6128",
  "name": "João Vitor Almeida Costa",
  "cpf": 11844420299
}
```
É importante salvar em algum bloco de texto o id, pois pensando na LGPD, não iremos trafegar o CPF para votação apenas o ID e em um contexto maior a aplicação Web irá ter esse id em memória.

#### Cadastrar uma pauta
Requisição para cadastrar:
Para cadastrar uma pauta é preciso de do nome da pauta e uma descrição também.
```
curl -X 'POST' \
  'http://localhost:8089/v1/agendas' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "title": "Aumento salarial",
  "description": "Aumentar 25% o salario"
}'

```
Corpo da requisição:
```
{
  "title": "Aumento salarial",
  "description": "Aumentar 25% o salario"
}
```

#### Escolher uma pauta para votar
Podemos escolher uma pauta para votar utilizando uma listagem de pautas, essa listagem utiliza paginação com isso pode se ordenar as pautas e trazer a quantidade por página como se desejar.
Requisição para obter a lista de pautas:
```
curl -X 'GET' \
  'http://localhost:8089/v1/agendas?page=0&linesPerPage=24&orderBy=createdAt&direction=DESC' \
  -H 'accept: application/json'

```
Resposta da API:
```
{
  "content": [
    {
      "id": "a492d737-555c-4917-92c4-b65a60aff757",
      "title": "Aumento salarial",
      "createdAt": "2022-08-03T22:17:31"
    }
  ],
  "pageable": {
    "sort": {
      "empty": false,
      "sorted": true,
      "unsorted": false
    },
    "offset": 0,
    "pageNumber": 0,
    "pageSize": 24,
    "unpaged": false,
    "paged": true
  },
  "last": true,
  "totalPages": 1,
  "totalElements": 1,
  "size": 24,
  "number": 0,
  "sort": {
    "empty": false,
    "sorted": true,
    "unsorted": false
  },
  "first": true,
  "numberOfElements": 1,
  "empty": false
}
```
Após obter a listagem das pautas é preciso escolher uma pauta, com isso é importante guardar o id da pauta em um bloco de notas.

#### Criar uma sessão para votar
Para poder criar uma sessão é preciso informar o id da pauta que será votada, também é possível definir o tempo que essa pauta ficará aberta para votação, caso não informe nenhum valor por padrão será um minuto. Os valores informados para sessão ficaram abertos para votação são em minutos.
Requisição para criar uma sessão:
```
curl -X 'POST' \
  'http://localhost:8089/v1/sessions' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "agendaId": "a492d737-555c-4917-92c4-b65a60aff757",
  "sessionTime": 5
}'

```
Corpo da requisição:
```
{
  "agendaId": "a492d737-555c-4917-92c4-b65a60aff757",
  "sessionTime": 5
}
```

#### Votar em uma pauta com sessão aberta
Para poder votar em uma pauta é preciso que a sessão seja aberta e também é preciso passar o id do associado que irá votar e o id da pauta que será votada, segue a requisição para realizar um voto. O voto pode ser enviado como SIM ou NAO, qualquer outro valor ocorre um erro.
Requisição para votar:
```
curl -X 'POST' \
  'http://localhost:8089/v1/votes' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "associateId": "62eb1c3e4b1d07100d4d6128",
  "agendaId": "a240327c-27c9-467e-8648-d218a6190380",
  "voteDecision": "SIM"
}'

```
Corpo da requisição:
```
{
  "agendaId": "a492d737-555c-4917-92c4-b65a60aff757",
  "sessionTime": 5
}
```
#### Tratamentos de erros no voto
Pode ocorrer um erro do associado não está apto para votar e essa validação é feita pelo CPF cadastrado, nesse caso o associado receberá a seguinte mensagem de erro:
```
{
  "timestamp": 1659576708411,
  "status": 422,
  "error": "NOT_ENABLED_VOTE",
  "message": "associate has no voting rights",
  "request": "/v1/votes",
  "path": "POST"
}
```
Também pode ocorrer um erro voto caso tentem votar em um pauta que a sessão já fechou a votação, com isso o usuário receberá a seguinte mensagem:
```
{
  "timestamp": 1659576794346,
  "status": 422,
  "error": "SESSION_CLOSED",
  "message": "the agenda session has already closed",
  "request": "/v1/votes",
  "path": "POST"
}
```
Caso tente votar em uma pauta sem sessão criada, irá ocorrer um erro, com isso o usuário receberá a seguinte mensagem:
```
{
  "timestamp": 1659577122167,
  "status": 422,
  "error": "SESSION_NOT_CREATED",
  "message": "not found a session created for agenda, please create a session for agenda",
  "request": "/v1/votes",
  "path": "POST"
}
```
Caso o associado tente votar duas vezes na mesma pauta irá receber o seguinte erro:

```
{
  "timestamp": 1659577215068,
  "status": 422,
  "error": "SECOND_ATTEMPT_VOTE",
  "message": "it is not allowed to vote twice on the same agenda",
  "request": "/v1/votes",
  "path": "POST"
}
```

#### Resultado da votação
Para obter o resultado da votação é preciso informar o id da pauta que ocorreu a votação.
Requisição para obter o resultado:

```
curl -X 'GET' \
  'http://localhost:8089/v1/votes/total-votes/a240327c-27c9-467e-8648-d218a6190380/agenda-id' \
  -H 'accept: application/json'
```
Resposta da API:

```
{
  "totalNo": 0,
  "totalYes": 1,
  "voteResultEnumeration": "APPROVED",
  "agendaId": "a240327c-27c9-467e-8648-d218a6190380",
  "title": "Aumento salarial",
  "description": "Aumentar 25% o salario"
}
```
#### Tratamento de erro ao obter o resultado
Caso ocorra uma tentativa de obter o resultado de uma pauta com sessão aberta o usuário irá receber a seguinte mensagem de erro:
```
{
  "timestamp": 1659577412336,
  "status": 422,
  "error": "SESSION_OPEN",
  "message": "the agenda session has open",
  "request": "/v1/votes/total-votes/a240327c-27c9-467e-8648-d218a6190380/agenda-id",
  "path": "GET"
}
```
