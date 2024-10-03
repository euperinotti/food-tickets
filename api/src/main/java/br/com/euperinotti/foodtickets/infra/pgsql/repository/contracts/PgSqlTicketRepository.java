package br.com.euperinotti.foodtickets.infra.pgsql.repository.contracts;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;
import br.com.euperinotti.foodtickets.infra.pgsql.entities.PgSqlTicketEntity;

@Repository
public interface PgSqlTicketRepository extends JpaRepository<PgSqlTicketEntity, Long> {
  @Query("SELECT e FROM PgSqlTicketEntity e WHERE e.status = :status")
  List<PgSqlTicketEntity> findByStatus(TicketStatus status);

  @Query("SELECT SUM(t.quantity) FROM PgSqlTicketEntity t")
  Integer countTickets();

  @Query("""
      SELECT t.createdAt FROM PgSqlTicketEntity t GROUP BY t.createdAt ORDER BY SUM(t.quantity) DESC
      """)
  LocalDateTime findDayWithMaxTickets();
}
