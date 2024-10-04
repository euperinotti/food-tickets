#!/bin/bash

set -e

echo "Iniciando análise de testes"
./mvnw clean test jacoco:report

wait

echo "Cobertura de testes gerada com sucesso..."
