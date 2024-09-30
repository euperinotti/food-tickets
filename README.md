# Sistema de Gestão de Tickets de Refeições

Este projeto é uma aplicação desenvolvida para gerenciar a quantidade de tickets de refeições distribuídos a funcionários em uma empresa. A aplicação permite que os administradores controlem o total de tickets distribuídos e possam verificar a quantidade por pessoa. O sistema conta com uma API em Java/Spring e uma interface frontend desenvolvida em Next.js com TypeScript e Tailwind CSS.

## Tecnologias Utilizadas

### Backend

- **Java** com **Spring Boot** para a criação da API
- **PostgreSQL** (ou outro banco de dados relacional) para armazenamento de dados

### Frontend

- **Next.js** com **TypeScript**
- **Tailwind CSS** para estilização da interface

## Estrutura do Projeto

### Backend (API)

```
src/
├── controller/
├── service/
├── repository/
├── model/
├── dto/
└── config/
```

### Frontend (Interface)

```
src/
├── pages/
├── components/
├── styles/
└── hooks/
```

## Funcionalidades

- **Cadastro de funcionários**: Permite adicionar e gerenciar funcionários que recebem os tickets.
- **Controle de distribuição de tickets**: O administrador pode visualizar e adicionar a quantidade de tickets distribuídos para cada funcionário.
- **Relatório**: O sistema gera relatórios com o total de tickets distribuídos e detalha quantos foram entregues por pessoa.

## Instalação e Execução

### Backend (API)

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-repositorio/api-tickets.git
   ```
2. Instale as dependências:
   ```bash
   mvn install
   ```
3. Configure o banco de dados no `application.properties`.
4. Execute o projeto:
   ```bash
   mvn spring-boot:run
   ```

### Frontend (Interface)

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-repositorio/frontend-tickets.git
   ```
2. Instale as dependências:
   ```bash
   yarn install
   ```
3. Execute o projeto:
   ```bash
   yarn dev
   ```

Acesse o frontend em [http://localhost:3000](http://localhost:3000).