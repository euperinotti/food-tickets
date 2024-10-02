package br.com.euperinotti.foodtickets.domain.entities;

import java.time.LocalDateTime;

import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;

public class EmployeeBO {
  private Long id;
  private String name;
  private String cpf;
  private EmployeeStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public EmployeeBO(Long id, String name, String cpf, EmployeeStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.name = name;
    this.cpf = cpf;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;

    validate();
  }

  public EmployeeBO(Long id, String name, String cpf) {
    this.id = id;
    this.name = name;
    this.cpf = cpf;
    this.status = EmployeeStatus.ACTIVE;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();

    validate();
  }

  private void validate() {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name is required");
    }

    if (cpf == null || cpf.isEmpty()) {
      throw new IllegalArgumentException("CPF is required");
    }
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getCpf() {
    return this.cpf;
  }

  public EmployeeStatus getStatus() {
    return this.status;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return this.updatedAt;
  }

}
