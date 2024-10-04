# Estrutura do Projeto

A estrutura do projeto segue os princípios de Clean Architecture, separando responsabilidades e mantendo o código desacoplado. O projeto está dividido nas seguintes camadas principais:

- **domain**: Contém as regras de negócios e entidades.
- **application**: Contém os casos de uso (services) que coordenam as operações do sistema.
- **infrastructure**: Contém a implementação de repositórios, configurações de banco de dados e integrações externas.
- **presentation**: Contém os controllers que expõem as APIs e fazem a orquestração entre a camada de aplicação e infraestrutura.
- **config**: Contém arquivos responsáveis pela configuração do projeto como captura de exceptions, CORS e outras configurações.

### Estrutura de pastas:
```
/src
  ├── main
  │   ├── java
  │   │   └── br.com.euperinotti.foodtickets
  │   │       ├── presentation
  │   │       │   ├── controllers
  │   │       ├── application
  │   │       │   └── services
  │   │       │   └── dtos
  │   │       ├── domain
  │   │       │   ├── entities
  │   │       │   └── usecases
  │   │       │   └── enums
  │   │       │   └── exceptions
  │   │       │   └── mappers
  │   │       │   └── repository
  │   │       │   └── validators
  │   │       │   └── utils
  │   │       ├── infrastructure
  │   │       │   ├── pgsql
  │   │       │     └── converters
  │   │       │     └── entities
  │   │       │     └── mappers
  │   │       │     └── repository
  │   └── resources
  │       ├── application.properties
  └── test
      ├── java
          ├── br.com.euperinotti.foodtickets
```

## Pré-requisitos

- **Java 17+**
- **Maven 3.8+**
- **PostgreSQL**

## Configuração e Execução do Projeto

1. **Configurar o banco de dados**:
   Atualize o arquivo `src/main/resources/application.properties` com as informações do banco de dados:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/foodtickets
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```

2. **Instalar as dependências**:
   Execute o comando abaixo para instalar as dependências do projeto:
   ```bash
   mvn clean install
   ```

3. **Executar a aplicação**:
   Para iniciar a API localmente:
   ```bash
   mvn spring-boot:run
   ```
   A API estará disponível em: `http://localhost:8080`

## Executando Testes

O projeto inclui testes unitários para as principais funcionalidades. Para rodar os testes:

```bash
mvn test
```

## Gerando Relatório de Cobertura de Testes

A cobertura de testes é gerada utilizando o **JaCoCo**. Para gerar o relatório de cobertura:

```bash
mvn clean test jacoco:report
```

O relatório estará disponível no diretório `target/site/jacoco/index.html`.

## Tecnologias Utilizadas

- **Spring Boot** - Framework para construção da aplicação
- **Maven** - Gerenciador de dependências
- **JaCoCo** - Ferramenta de cobertura de testes
- **JUnit 5** - Framework de testes
- **PostgreSQL** - Banco de dados relacional