package br.com.euperinotti.foodtickets.application.dtos.response;

import java.time.LocalDateTime;

import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDTO {
  private Long id;
  private String name;
  private String cpf;
  private EmployeeStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public EmployeeResponseDTO(Long id, String name, String cpf) {
    this.id = id;
    this.name = name;
    this.cpf = cpf;
    this.status = EmployeeStatus.ACTIVE;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  public EmployeeResponseDTO(Long id, String name, String cpf, EmployeeStatus status) {
    this.id = id;
    this.name = name;
    this.cpf = cpf;
    this.status = status;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }
}
