package br.com.euperinotti.foodtickets.application.dtos.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnalyticsResponseDTO {
  private Integer activeEmployees;
  private Integer ticketsRetrieved;
  private LocalDateTime dayWithMostTickets;
  private EmployeeResponseDTO employeeWithMostTickets;
  private List<TicketResponseDTO> twoWeeksTicketsHistory;
}
