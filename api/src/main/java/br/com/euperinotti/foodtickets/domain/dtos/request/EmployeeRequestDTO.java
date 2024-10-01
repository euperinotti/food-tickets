package br.com.euperinotti.foodtickets.domain.dtos.request;

import java.time.LocalDate;

import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;

public class EmployeeRequestDTO {
  private Long id;
  private String name;
  private String cpf;
  private EmployeeStatus status;
  private LocalDate createdAt;
  private LocalDate updatedAt;

  public EmployeeRequestDTO() {}

  public EmployeeRequestDTO(Long id, String name, String cpf) {
    this.id = id;
    this.name = name;
    this.cpf = cpf;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCpf() {
    return this.cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public EmployeeStatus getStatus() {
    return this.status;
  }

  public void setStatus(EmployeeStatus status) {
    this.status = status;
  }

  public LocalDate getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDate getUpdatedAt() {
    return this.updatedAt;
  }

  public void setUpdatedAt(LocalDate updatedAt) {
    this.updatedAt = updatedAt;
  }

}
