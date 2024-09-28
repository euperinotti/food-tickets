package br.com.euperinotti.foodtickets.infra.pgsql.repository.contracts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.euperinotti.foodtickets.infra.pgsql.entities.PgSqlEmployeeEntity;


public interface PgSqlEmployeeRepository extends JpaRepository<PgSqlEmployeeEntity, Long> {
  Optional<PgSqlEmployeeEntity> findByCpf(String cpf);
  List<PgSqlEmployeeEntity> findByStatus(String status);
}
