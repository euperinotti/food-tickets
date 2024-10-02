package br.com.euperinotti.foodtickets.domain.usecases.ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.euperinotti.foodtickets.application.dtos.request.TicketRequestDTO;
import br.com.euperinotti.foodtickets.application.dtos.response.TicketResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.TicketBO;
import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;
import br.com.euperinotti.foodtickets.domain.exceptions.AppExceptions;
import br.com.euperinotti.foodtickets.domain.exceptions.enums.TicketExceptions;
import br.com.euperinotti.foodtickets.domain.repository.ITicketRepository;

class UpdateByIdTest {

  @Mock
  private ITicketRepository repository;

  @InjectMocks
  private UpdateById updateById;

  private TicketRequestDTO ticketRequestDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    ticketRequestDTO = new TicketRequestDTO();
    ticketRequestDTO.setEmployeeId(1L);
    ticketRequestDTO.setQuantity(5);
    ticketRequestDTO.setStatus(TicketStatus.ACTIVE);
  }

  @Test
  void test_execute_shouldReturnUpdatedTicketResponseDTO() {
    TicketBO existingTicket = new TicketBO(1L, 1L, 5, TicketStatus.ACTIVE, null, null);
    TicketBO updatedTicket = new TicketBO(1L, 1L, 10, TicketStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now());

    when(repository.findById(1L)).thenReturn(Optional.of(existingTicket));
    when(repository.updateById(eq(1L), Mockito.any(TicketBO.class))).thenReturn(updatedTicket);

    TicketResponseDTO result = updateById.execute(1L, ticketRequestDTO);

    assertNotNull(result);
    assertEquals(updatedTicket.getId(), result.getId());
    assertEquals(updatedTicket.getQuantity(), result.getQuantity());
    assertNotNull(result.getUpdatedAt());
    verify(repository, times(1)).findById(1L);
    verify(repository, times(1)).updateById(eq(1L), Mockito.any(TicketBO.class));
  }

  @Test
  void test_execute_shouldThrowExceptionWhenTicketNotFound() {
    when(repository.findById(1L)).thenReturn(Optional.empty());

    AppExceptions exception = assertThrows(AppExceptions.class, () -> updateById.execute(1L, ticketRequestDTO));
    assertEquals(TicketExceptions.TICKET_NOT_FOUND.getMessage(), exception.getMessage());
    verify(repository, times(1)).findById(1L);
    verify(repository, never()).updateById(anyLong(), any());
  }
}
