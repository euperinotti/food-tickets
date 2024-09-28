package br.com.euperinotti.foodtickets.infra.pgsql.entities;

import java.time.LocalDate;

import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;
import br.com.euperinotti.foodtickets.infra.pgsql.converters.EmployeeStatusConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tickets")
public class PgSqlTicketEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Long employeeId;

  @Column(nullable = false)
  @Convert(converter = EmployeeStatusConverter.class)
  private TicketStatus status;

  @Column(nullable = false, name = "created_at")
  private LocalDate createdAt;

  @Column(nullable = false, name = "updated_at")
  private LocalDate updatedAt;
}
