# FTC.RestaurantHistory.API

Este serviço é responsável por consumir eventos de pedidos (`Order`) e reservas (`Reservation`) de um tópico Apache Kafka e persistir um histórico de pedidos e reservas para os restaurantes. Além de possuir consultas à base de históricos.

## Diagrama de Arquitetura

O diagrama abaixo ilustra a arquitetura e o fluxo de dados do serviço:

![arch-diagram.png](arch-diagram.png)

## Arquitetura do Sistema

Este sistema foi desenvolvido utilizando **Java 21**, **Spring Boot 3.4.4** e **PostgreSQL 17.4**.

### Tecnologias Utilizadas
- **Java 21** e **Spring Boot 3.4.4** para a criação da aplicação web
- **Docker** e **Docker Compose** para execução e gerenciamento de ambientes
- **GraphQL** protocolo para execução de consultas à base de histórico
- **PostgreSQL** como banco de dados, garantindo confiabilidade e desempenho
- **Flyway** para gerenciamento de migrações do banco de dados
- **H2 Database Engine** como banco de dados para ambiente de testes automatizados

### Estrutura Arquitetural
A arquitetura do sistema segue uma abordagem baseada em **Domínios**, promovendo a separação de responsabilidades e facilitando a escalabilidade. Os principais componentes dentro de cada domínio incluem:

- **Config**: Configuração de dependências necessárias para o levantamento e funcionamento do container de aplicação.
- **Controller**: Gerencia requisições HTTP e as direciona para os serviços apropriados.
- **ServiceGateway**: Coordena acessos sistêmicos como arquivos de configuração, repositórios e orquestra use cases.
- **MapperPresenter**: Prepara os dados para retorno ao cliente.
- **Repository**: Interface para acesso e manipulação de dados armazenados no banco de dados.
- **Entity**: Representação das tabelas do banco de dados como classes Java (ORM) e core de domínio.
- **UseCases**: Implementações de regras de negócio para cada caso de uso de cada domínio.

### Benefícios da Arquitetura
Essa estrutura modular possibilita:
- Desenvolvimento mais organizado
- Manutenção facilitada
- Maior flexibilidade para futuras expansões

## Como rodar? 🚀
Para executar o projeto utilizando Docker Compose:
1. Rode `docker compose --profile docker up --build` na raiz do projeto
2. Acesse o playground: [link](http://localhost:8086/restaurant-history/playground.html)
3. Acesse a documentação Postman: [link](https://documenter.getpostman.com/view/43787842/2sB2qcBfps)

