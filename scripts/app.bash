#!/bin/bash

# Parar o script em caso de erro
set -e

# Função para build e execução do projeto Spring Boot
run_spring_boot() {
  echo "Building and running Spring Boot application..."
  cd ../api || exit
  ./mvnw clean install # Faz a build do projeto Spring Boot
  ./mvnw spring-boot:run # Executa o projeto
}

# Função para build e execução do projeto Next.js
run_nextjs() {
  echo "Building and running Next.js application..."
  cd ../front || exit
  yarn install # Instala as dependências do projeto Node
  yarn build # Faz a build do projeto Next.js
  yarn start # Executa o projeto
}

# Mudar para o diretório do script
cd "$(dirname "$0")"

# Executar o projeto backend (Spring Boot)
run_spring_boot &

# Executar o projeto frontend (Next.js)
run_nextjs &

# Aguardar ambos os processos terminarem
wait

echo "Aplicações foram iniciadas com sucesso!"
