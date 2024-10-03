package br.com.euperinotti.foodtickets.domain.usecases.ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.euperinotti.foodtickets.application.dtos.response.TicketResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.TicketBO;
import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;
import br.com.euperinotti.foodtickets.domain.repository.ITicketRepository;

class FindByStatusTest {

  @Mock
  private ITicketRepository repository;

  @InjectMocks
  private FindByStatus findByStatus;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void test_execute_shouldReturnListOfTicketResponseDTO() {
    TicketBO ticket1 = new TicketBO(1L, 1L, 5, TicketStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now());
    TicketBO ticket2 = new TicketBO(2L, 2L, 3, TicketStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now());

    when(repository.findByStatus(TicketStatus.ACTIVE)).thenReturn(List.of(ticket1, ticket2));

    List<TicketResponseDTO> result = findByStatus.execute(TicketStatus.ACTIVE);

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(ticket1.getId(), result.get(0).getId());
    assertEquals(ticket2.getId(), result.get(1).getId());
    verify(repository, times(1)).findByStatus(TicketStatus.ACTIVE);
  }

  @Test
  void test_execute_shouldReturnEmptyListWhenNoTicketsFound() {
    when(repository.findByStatus(TicketStatus.INACTIVE)).thenReturn(Collections.emptyList());

    List<TicketResponseDTO> result = findByStatus.execute(TicketStatus.INACTIVE);

    assertNotNull(result);
    assertTrue(result.isEmpty());
    verify(repository, times(1)).findByStatus(TicketStatus.INACTIVE);
  }
}
