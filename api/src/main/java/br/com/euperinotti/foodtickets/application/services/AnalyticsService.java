package br.com.euperinotti.foodtickets.application.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.euperinotti.foodtickets.application.dtos.response.AnalyticsResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.entities.TicketBO;
import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import br.com.euperinotti.foodtickets.domain.mappers.EmployeeMapper;
import br.com.euperinotti.foodtickets.domain.mappers.TicketMapper;
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
    List<TicketBO> tickets = ticketRepository.findAllTicketsFromPeriod(LocalDateTime.now().minusWeeks(2));

    Optional<EmployeeBO> employee = employeeRepository.findEmployeeWithMostTickets();

    dto.setActiveEmployees(employeeRepository.countByStatus(EmployeeStatus.ACTIVE));
    dto.setTicketsRetrieved(ticketRepository.countTickets());
    dto.setDayWithMostTickets(ticketRepository.findDayWithMaxTickets());
    dto.setEmployeeWithMostTickets(employee.isPresent() ? EmployeeMapper.toResponseDTO(employee.get()) : null);
    dto.setTwoWeeksTicketsHistory(tickets.stream().map(TicketMapper::toResponseDTO).collect(Collectors.toList()));

    return dto;
  }

}
