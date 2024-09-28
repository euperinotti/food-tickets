package br.com.euperinotti.foodtickets.infra.pgsql.entities;

import java.time.LocalDate;

import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "employees")
public class PgSqlEmployeeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String name;

  @Column(unique = true, nullable = false)
  private String cpf;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private EmployeeStatus status;

  @Column(nullable = false, name = "created_at")
  private LocalDate createdAt;

  @Column(nullable = false, name = "updated_at")
  private LocalDate updatedAt;
}
