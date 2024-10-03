package br.com.euperinotti.foodtickets.infra.pgsql.repository.contracts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import br.com.euperinotti.foodtickets.infra.pgsql.entities.PgSqlEmployeeEntity;

@Repository
public interface PgSqlEmployeeRepository extends JpaRepository<PgSqlEmployeeEntity, Long> {
  Optional<PgSqlEmployeeEntity> findByCpf(String cpf);

  @Query("SELECT e FROM PgSqlEmployeeEntity e WHERE e.status = :status")
  List<PgSqlEmployeeEntity> findByStatus(EmployeeStatus status);

  @Query("SELECT COUNT(e) FROM PgSqlEmployeeEntity e WHERE e.status = :status")
  Integer countByStatus(@Param("status") EmployeeStatus status);

  @Query("""
      SELECT t.employee FROM PgSqlTicketEntity t GROUP BY t.employee ORDER BY SUM(t.quantity) DESC
      """)
  PgSqlEmployeeEntity findEmployeeWithMostTickets();
}
