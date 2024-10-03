package br.com.euperinotti.foodtickets.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.euperinotti.foodtickets.application.dtos.response.AnalyticsResponseDTO;
import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import br.com.euperinotti.foodtickets.domain.mappers.EmployeeMapper;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;
import br.com.euperinotti.foodtickets.domain.repository.ITicketRepository;

@Service
public class AnalyticsService {

  private ITicketRepository ticketRepository;
  private IEmployeeRepository employeeRepository;

  @Autowired
  public AnalyticsService(ITicketRepository ticketRepository, IEmployeeRepository employeeRepository) {
    this.ticketRepository = ticketRepository;
    this.employeeRepository = employeeRepository;
  }

  public AnalyticsResponseDTO getData() {
    AnalyticsResponseDTO dto = new AnalyticsResponseDTO();

    dto.setActiveEmployees(employeeRepository.countByStatus(EmployeeStatus.ACTIVE));
    dto.setTicketsRetrieved(ticketRepository.countTickets());
    dto.setDayWithMostTickets(ticketRepository.findDayWithMaxTickets());
    dto.setEmployeeWithMostTickets(EmployeeMapper.toResponseDTO(employeeRepository.findEmployeeWithMostTickets()));

    return dto;
  }

}
