package br.com.euperinotti.foodtickets.application.dtos.response;

import java.time.LocalDateTime;

import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;

public class EmployeeResponseDTO {
  private Long id;
  private String name;
  private String cpf;
  private EmployeeStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public EmployeeResponseDTO() {}

  public EmployeeResponseDTO(Long id, String name, String cpf, EmployeeStatus status, LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    this.id = id;
    this.name = name;
    this.cpf = cpf;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
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

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
