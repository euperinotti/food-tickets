package br.com.euperinotti.foodtickets.domain.dtos.response;

import java.util.Date;

import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;

public class EmployeeResponseDTO {
  private Long id;
  private String name;
  private String cpf;
  private EmployeeStatus status;
  private Date createdAt;
  private Date updatedAt;

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

  public Date getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return this.updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }
}
