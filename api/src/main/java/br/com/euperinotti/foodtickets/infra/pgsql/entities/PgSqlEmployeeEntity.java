package br.com.euperinotti.foodtickets.infra.pgsql.entities;

import java.time.LocalDateTime;

import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import br.com.euperinotti.foodtickets.infra.pgsql.converters.EmployeeStatusConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
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
  @Convert(converter = EmployeeStatusConverter.class)
  private EmployeeStatus status;

  @Column(nullable = false, name = "created_at")
  private LocalDateTime createdAt;

  @Column(nullable = false, name = "updated_at")
  private LocalDateTime updatedAt;
}
