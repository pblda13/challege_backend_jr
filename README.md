# Desafio Backend Júnior(em construção)🚧 

## Descrição

Neste desafio, estou desenvolvendo uma API para gerenciar as fichas de treino dos clientes. Uma ficha de treino é uma lista de exercícios/equipamentos associados a um cliente e professor.

## Funcionalidades 

### Professor:

- Pode visualizar, incluir, alterar e excluir exercícios na ficha de treino de qualquer cliente.
- Pode pesquisar a ficha do cliente por nome e/ou matrícula.

### Cliente:

- Pode visualizar e alterar (apenas a carga) sua ficha de treino.

## Implementação

Para implementar essa API, utilizei a linguagem Java com o framework Spring Boot. Escolhi o Spring Boot devido à sua facilidade de configuração e robustez para desenvolvimento de APIs RESTful.

## Arquitetura da Solução

A solução é dividida em camadas: Controller, Service e Repository.

- **Controller:** Responsável por receber as requisições HTTP, chamar os métodos dos serviços e retornar as respostas adequadas.
- **Service:** Contém a lógica de negócio da aplicação. Realiza operações como criação, leitura, atualização e exclusão (CRUD) das entidades.
- **Repository:** Responsável pela comunicação com o banco de dados. Utiliza o Spring Data JPA para facilitar o acesso e manipulação dos dados.

## Diferenciais Implementados

- Utilização do Spring Boot para facilitar o desenvolvimento.
- Implementação de uma arquitetura escalável, dividindo a aplicação em camadas.
- Uso de banco de dados relacional para persistência dos dados.
- Tratamento de erros e exceções em toda a aplicação.
- Desenvolvimento de testes unitários para garantir a qualidade do código.
- Documentação dos endpoints utilizando o Swagger.
- Utilização do Apache Kafka para enviar mensagens quando uma nova entidade é criada.

## Endpoints

### Clientes

- **POST /client**: Cria um novo cliente.
- **PUT /client/{id}**: Atualiza um cliente existente.
- **GET /client**: Lista todos os clientes.
- **DELETE /client/{id}**: Deleta um cliente pelo ID.

### Exercícios

- **POST /exercise**: Cria um novo exercício.
- **PUT /exercise/{id}**: Atualiza um exercício existente.
- **GET /exercise**: Lista todos os exercícios.
- **DELETE /exercise/{id}**: Deleta um exercício pelo ID.

### Séries de Exercícios

- **POST /serie**: Cria uma nova série de exercícios.
- **PUT /serie/{id}**: Atualiza uma série de exercícios existente.
- **GET /serie**: Lista todas as séries de exercícios.
- **DELETE /serie/{id}**: Deleta uma série de exercícios pelo ID.

### Professores

- **POST /teacher**: Cria um novo professor.
- **PUT /teacher/{id}**: Atualiza um professor existente.
- **GET /teacher**: Lista todos os professores.
- **DELETE /teacher/{id}**: Deleta um professor pelo ID.

### Formulários de Treinamento

- **POST /trainingForm**: Cria um novo formulário de treinamento.
- **PUT /trainingForm/{id}**: Atualiza um formulário de treinamento existente.
- **GET /trainingForm**: Lista todos os formulários de treinamento.
- **DELETE /trainingForm/{id}**: Deleta um formulário de treinamento pelo ID.

### Treinamentos Realizados

- **POST /trainingPerformed**: Registra um novo treinamento realizado.
- **PUT /trainingPerformed/{id}**: Atualiza um treinamento realizado existente.
- **GET /trainingPerformed**: Lista todos os treinamentos realizados.
- **DELETE /trainingPerformed/{id}**: Deleta um treinamento realizado pelo ID.

## Desafio Original

Este desafio foi inspirado no desafio original disponível em [https://github.com/BodytechCompany/backend-challenge-jr](https://github.com/BodytechCompany/backend-challenge-jr).
