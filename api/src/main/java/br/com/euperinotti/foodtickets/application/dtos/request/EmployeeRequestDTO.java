package br.com.euperinotti.foodtickets.application.dtos.request;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.lang.Nullable;

import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDTO {
  private Long id;
  private String name;

  @CPF
  private String cpf;
  private EmployeeStatus status;

  @Nullable
  private LocalDateTime createdAt;

  @Nullable
  private LocalDateTime updatedAt;

  public EmployeeRequestDTO(Long id, String name, String cpf) {
    this.id = id;
    this.name = name;
    this.cpf = cpf;
    this.status = EmployeeStatus.ACTIVE;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  public EmployeeRequestDTO(Long id, String name, String cpf, EmployeeStatus status) {
    this.id = id;
    this.name = name;
    this.cpf = cpf;
    this.status = status;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }
}
