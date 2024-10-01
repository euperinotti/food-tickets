package br.com.euperinotti.foodtickets.domain.entities;

import java.time.LocalDate;

import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;
import br.com.euperinotti.foodtickets.domain.exceptions.AppExceptions;
import br.com.euperinotti.foodtickets.domain.exceptions.enums.TicketExceptions;

public class TicketBO {
  private Long id;
  private Long employeeId;
  private Integer quantity;
  private TicketStatus status;
  private LocalDate createdAt;
  private LocalDate updatedAt;

  public TicketBO(Long id, Long employeeId, Integer quantity, TicketStatus status, LocalDate createdAt, LocalDate updatedAt) {
    this.id = id;
    this.quantity = quantity;
    this.employeeId = employeeId;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;

    validate();
  }

  private void validate() {
    if (employeeId == null) {
      throw new AppExceptions(TicketExceptions.TICKET_INVALID_EMPLOYEE.getMessage());
    }
  }

  public Long getId() {
    return this.id;
  }

  public Long getEmployeeId() {
    return this.employeeId;
  }

  public Integer getQuantity() {
    return this.quantity;
  }

  public TicketStatus getStatus() {
    return this.status;
  }

  public LocalDate getCreatedAt() {
    return this.createdAt;
  }

  public LocalDate getUpdatedAt() {
    return this.updatedAt;
  }

}
