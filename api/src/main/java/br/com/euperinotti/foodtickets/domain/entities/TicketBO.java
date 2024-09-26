package br.com.euperinotti.foodtickets.domain.entities;

import java.util.Date;

import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;

public class TicketBO {
  private Long id;
  private Long employeeId;
  private TicketStatus status;
  private Date createdAt;
  private Date updatedAt;

  public TicketBO(Long id, Long employeeId, TicketStatus status, Date createdAt, Date updatedAt) {
    this.id = id;
    this.employeeId = employeeId;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;

    validate();
  }

  private void validate() {
    if (employeeId == null) {
      throw new IllegalArgumentException("Employee ID is required");
    }
  }


  public Long getId() {
    return this.id;
  }

  public Long getEmployeeId() {
    return this.employeeId;
  }

  public TicketStatus getStatus() {
    return this.status;
  }

  public Date getCreatedAt() {
    return this.createdAt;
  }

  public Date getUpdatedAt() {
    return this.updatedAt;
  }

}
