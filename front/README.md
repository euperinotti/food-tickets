# Estrutura do Projeto

O front-end foi projetado utilizando **Next.js** para otimizar o desempenho, com rotas dinâmicas e renderização server-side. A estilização é feita utilizando **TailwindCSS** para agilizar a criação de componentes visuais responsivos.

### Estrutura de pastas:
```
/src
  ├── axios
  ├── components
  ├── pages
  ├── context
  ├── hooks
  ├── reducer
  ├── template
  ├── styles
  ├── utils
  └── @types
```

## Pré-requisitos

- **Node.js** (20 ou superior)
- **npm** ou **yarn**

## Configuração e Execução do Projeto

1. **Instalar as dependências**:
   Execute o comando abaixo para instalar as dependências:
   ```bash
   npm install
   # ou
   yarn install
   ```

2. **Build**:
   Para iniciar a build do projeto execute:
   ```bash
   npm run build
   # ou
   yarn build
   ```

3. **Executar o projeto**:
   Para iniciar o projeto em produção:
   ```bash
   npm run start
   # ou
   yarn start
   ```

   O projeto estará disponível em: `http://localhost:3000`

## Scripts Disponíveis

- `npm run dev`: Executa o projeto em modo de desenvolvimento.
- `npm run build`: Compila o projeto para produção.
- `npm run start`: Inicia a aplicação em produção após a compilação.
- `npm run lint`: Executa o linter do TypeScript para verificar erros de código.

## Tipagem com TypeScript

O projeto é totalmente tipado utilizando **TypeScript** para garantir a segurança e consistência do código. Tipos personalizados para as requisições e respostas da API são definidos na pasta `src/types`.

## Tecnologias Utilizadas

- **Next.js** - Framework React com renderização server-side
- **TypeScript** - Superset de JavaScript para tipagem estática
- **TailwindCSS** - Framework de estilização utilitária
- **Axios** - Cliente HTTP para consumo da API
- **React Query** - Gerenciamento de estado de requisições assíncronas