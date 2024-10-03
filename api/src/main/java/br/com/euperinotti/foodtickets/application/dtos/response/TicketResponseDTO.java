package br.com.euperinotti.foodtickets.application.dtos.response;

import java.time.LocalDateTime;

import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDTO {
  private Long id;
  private Long employeeId;
  private EmployeeResponseDTO employee;
  private Integer quantity;
  private TicketStatus status;
  private LocalDateTime updatedAt;
  private LocalDateTime createdAt;
}
