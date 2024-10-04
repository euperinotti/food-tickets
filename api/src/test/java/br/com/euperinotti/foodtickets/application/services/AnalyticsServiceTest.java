package br.com.euperinotti.foodtickets.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.euperinotti.foodtickets.application.dtos.response.AnalyticsResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.entities.TicketBO;
import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;
import br.com.euperinotti.foodtickets.domain.repository.ITicketRepository;

@ExtendWith(MockitoExtension.class)
public class AnalyticsServiceTest {
  @Mock
  private ITicketRepository ticketRepository;

  @Mock
  private IEmployeeRepository employeeRepository;

  @InjectMocks
  private AnalyticsService sut;

  private List<TicketBO> ticketList;

  private EmployeeBO employeeBO;

  @BeforeEach
  void setUp() {
    ticketList = List.of(
        new TicketBO(1L, 1L, 10, TicketStatus.ACTIVE, LocalDateTime.now().minusDays(1),
            LocalDateTime.now().minusDays(1)),
        new TicketBO(2L, 2L, 5, TicketStatus.ACTIVE, LocalDateTime.now().minusDays(3),
            LocalDateTime.now().minusDays(3)));

    employeeBO = new EmployeeBO(1L, "John Doe", "12345678910", EmployeeStatus.ACTIVE, LocalDateTime.now(),
        LocalDateTime.now());
  }

  @Test
  void test_getData_shouldReturnAnalyticsResponseDTO() {
    when(ticketRepository.findAllTicketsFromPeriod(any(LocalDateTime.class))).thenReturn(ticketList);
    when(employeeRepository.countByStatus(EmployeeStatus.ACTIVE)).thenReturn(10);
    when(ticketRepository.countTickets()).thenReturn(2);
    when(ticketRepository.findDayWithMaxTickets()).thenReturn(LocalDateTime.now().minusDays(1));
    when(employeeRepository.findEmployeeWithMostTickets()).thenReturn(employeeBO);

    AnalyticsResponseDTO result = sut.getData();

    assertNotNull(result);
    assertEquals(10, result.getActiveEmployees());
    assertEquals(2, result.getTicketsRetrieved());
    assertEquals(2, result.getTwoWeeksTicketsHistory().size());
    assertEquals("JOHN DOE", result.getEmployeeWithMostTickets().getName());
  }

  @Test
  @Disabled
  void test_getData_shouldReturnEmptyAnalyticsWhenNoTicketsFound() {
    when(ticketRepository.findAllTicketsFromPeriod(any(LocalDateTime.class)))
        .thenReturn(List.of());
    when(employeeRepository.countByStatus(EmployeeStatus.ACTIVE)).thenReturn(0);
    when(ticketRepository.countTickets()).thenReturn(0);
    when(ticketRepository.findDayWithMaxTickets()).thenReturn(null);
    when(employeeRepository.findEmployeeWithMostTickets()).thenReturn(null);

    AnalyticsResponseDTO result = sut.getData();

    assertNotNull(result);
    assertEquals(0, result.getActiveEmployees());
    assertEquals(0, result.getTicketsRetrieved());
    assertEquals(null, result.getDayWithMostTickets());
    assertEquals(0, result.getTwoWeeksTicketsHistory().size());
    assertEquals(null, result.getEmployeeWithMostTickets());
  }
}
