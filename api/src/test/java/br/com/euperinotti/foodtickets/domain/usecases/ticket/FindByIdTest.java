package br.com.euperinotti.foodtickets.domain.usecases.ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.euperinotti.foodtickets.application.dtos.response.TicketResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.TicketBO;
import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;
import br.com.euperinotti.foodtickets.domain.exceptions.AppExceptions;
import br.com.euperinotti.foodtickets.domain.repository.ITicketRepository;

class FindByIdTest {

  @Mock
  private ITicketRepository repository;

  @InjectMocks
  private FindById findById;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void test_execute_shouldReturnTicketResponseDTO() {
    TicketBO ticketBO = new TicketBO(1L, 1L, 5, TicketStatus.ACTIVE, null, null);
    when(repository.findById(1L)).thenReturn(Optional.of(ticketBO));

    TicketResponseDTO result = findById.execute(1L);

    assertNotNull(result);
    assertEquals(ticketBO.getId(), result.getId());
    verify(repository, times(1)).findById(1L);
  }

  @Test
  void test_execute_shouldThrowExceptionWhenTicketNotFound() {
    when(repository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(AppExceptions.class, () -> findById.execute(1L));
    verify(repository, times(1)).findById(1L);
  }
}
