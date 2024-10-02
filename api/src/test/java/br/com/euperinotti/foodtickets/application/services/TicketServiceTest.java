package br.com.euperinotti.foodtickets.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.euperinotti.foodtickets.application.dtos.request.TicketRequestDTO;
import br.com.euperinotti.foodtickets.application.dtos.response.TicketResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.TicketBO;
import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;
import br.com.euperinotti.foodtickets.domain.repository.ITicketRepository;

class TicketServiceTest {

  @Mock
  private ITicketRepository ticketRepository;

  @Mock
  private IEmployeeRepository employeeRepository;

  @InjectMocks
  private TicketService ticketService;

  private TicketRequestDTO ticketRequestDTO;
  private TicketResponseDTO ticketResponseDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    ticketRequestDTO = new TicketRequestDTO();
    ticketRequestDTO.setEmployeeId(1L);
    ticketRequestDTO.setQuantity(5);
    ticketRequestDTO.setStatus(TicketStatus.ACTIVE);

    ticketResponseDTO = new TicketResponseDTO();
    ticketResponseDTO.setId(1L);
    ticketResponseDTO.setEmployeeId(1L);
    ticketResponseDTO.setQuantity(5);
    ticketResponseDTO.setStatus(TicketStatus.ACTIVE);
  }

  @Test
  void test_getAll_shouldReturnTicketResponseDTOList() {
    when(ticketRepository.findAll()).thenReturn(Collections
        .singletonList(new TicketBO(1L, 1L, 5, TicketStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now())));

    List<TicketResponseDTO> result = ticketService.getAll();

    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(ticketResponseDTO.getId(), result.get(0).getId());
  }

  @Test
  void test_getById_shouldReturnTicketResponseDTO() {
    when(ticketRepository.findById(1L)).thenReturn(
        Optional.of(new TicketBO(1L, 1L, 5, TicketStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now())));

    TicketResponseDTO result = ticketService.getById(1L);

    assertNotNull(result);
    assertEquals(ticketResponseDTO.getId(), result.getId());
  }

  @Test
  void test_getByStatus_shouldReturnTicketResponseDTOList() {
    when(ticketRepository.findByStatus(TicketStatus.ACTIVE)).thenReturn(Arrays.asList(
        new TicketBO(1L, 1L, 5, TicketStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now()),
        new TicketBO(2L, 1L, 3, TicketStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now())));

    List<TicketResponseDTO> result = ticketService.getByStatus(TicketStatus.ACTIVE.getName());

    assertNotNull(result);
    assertEquals(2, result.size());
  }

  @Test
  @Disabled
  void test_create_shouldReturnTicketResponseDTO() {
    when(ticketRepository.save(any()))
        .thenReturn(new TicketBO(1L, 1L, 5, TicketStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now()));

    TicketResponseDTO result = ticketService.create(ticketRequestDTO);

    assertNotNull(result);
    assertEquals(ticketResponseDTO.getId(), result.getId());
    assertEquals(ticketResponseDTO.getQuantity(), result.getQuantity());
  }

  @Test
  void test_update_shouldReturnTicketResponseDTO() {
    when(ticketRepository.findById(1L)).thenReturn(
        Optional.of(new TicketBO(1L, 1L, 5, TicketStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now())));
    when(ticketRepository.updateById(anyLong(), any()))
        .thenReturn(new TicketBO(1L, 1L, 5, TicketStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now()));

    TicketResponseDTO result = ticketService.update(1L, ticketRequestDTO);

    assertNotNull(result);
    assertEquals(ticketResponseDTO.getId(), result.getId());
    assertEquals(ticketResponseDTO.getQuantity(), result.getQuantity());
  }
}
