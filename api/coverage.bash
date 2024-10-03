#!/bin/bash

set -e

echo "Iniciando análise de testes"
./mvnw jacoco:prepare-agent test install jacoco:report

wait

echo "Cobertura de testes gerada com sucesso..."
