package br.com.euperinotti.foodtickets.application.dtos.response;

import java.time.LocalDate;

import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;

public class TicketResponseDTO {
  private Long id;
  private Long employeeId;
  private Integer quantity;
  private TicketStatus status;
  private LocalDate updatedAt;
  private LocalDate createdAt;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getEmployeeId() {
    return this.employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

  public Integer getQuantity() {
    return this.quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public TicketStatus getStatus() {
    return this.status;
  }

  public void setStatus(TicketStatus status) {
    this.status = status;
  }

  public LocalDate getUpdatedAt() {
    return this.updatedAt;
  }

  public void setUpdatedAt(LocalDate updatedAt) {
    this.updatedAt = updatedAt;
  }

  public LocalDate getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
  }

}
