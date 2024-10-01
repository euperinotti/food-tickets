package br.com.euperinotti.foodtickets.infra.pgsql.entities;

import java.time.LocalDate;

import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;
import br.com.euperinotti.foodtickets.infra.pgsql.converters.TicketStatusConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
  @JoinColumn(name = "employee_id", nullable = false)
  private PgSqlEmployeeEntity employee;

  private Integer quantity;

  @Column(nullable = false)
  @Convert(converter = TicketStatusConverter.class)
  private TicketStatus status;

  @Column(nullable = false, name = "created_at")
  private LocalDate createdAt;

  @Column(nullable = false, name = "updated_at")
  private LocalDate updatedAt;
}
