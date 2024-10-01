package br.com.euperinotti.foodtickets.domain.entities;

import java.time.LocalDate;

import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;

public class EmployeeBO {
  private Long id;
  private String name;
  private String cpf;
  private EmployeeStatus status;
  private LocalDate createdAt;
  private LocalDate updatedAt;

  public EmployeeBO(Long id, String name, String cpf, EmployeeStatus status, LocalDate createdAt, LocalDate updatedAt) {
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
    this.createdAt = LocalDate.now();
    this.updatedAt = LocalDate.now();

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

  public LocalDate getCreatedAt() {
    return this.createdAt;
  }

  public LocalDate getUpdatedAt() {
    return this.updatedAt;
  }

}
