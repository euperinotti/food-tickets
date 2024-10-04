package br.com.euperinotti.foodtickets.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.euperinotti.foodtickets.application.dtos.request.TicketRequestDTO;
import br.com.euperinotti.foodtickets.application.dtos.response.TicketResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
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
  private TicketService sut;

  private TicketRequestDTO requestDTO;
  private TicketResponseDTO responseDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    requestDTO = new TicketRequestDTO();
    requestDTO.setEmployeeId(1L);
    requestDTO.setQuantity(5);
    requestDTO.setStatus(TicketStatus.ACTIVE);

    responseDTO = new TicketResponseDTO();
    responseDTO.setId(1L);
    responseDTO.setEmployeeId(1L);
    responseDTO.setQuantity(5);
    responseDTO.setStatus(TicketStatus.ACTIVE);
  }

  @Test
  void test_getAll_shouldReturnTicketResponseDTOList() {
    when(ticketRepository.findAll()).thenReturn(Collections
        .singletonList(new TicketBO(1L, 1L, 5, TicketStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now())));

    List<TicketResponseDTO> result = sut.getAll();

    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(responseDTO.getId(), result.get(0).getId());
  }

  @Test
  void test_getById_shouldReturnTicketResponseDTO() {
    when(ticketRepository.findById(1L)).thenReturn(
        Optional.of(new TicketBO(1L, 1L, 5, TicketStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now())));

    TicketResponseDTO result = sut.getById(1L);

    assertNotNull(result);
    assertEquals(responseDTO.getId(), result.getId());
  }

  @Test
  void test_getByStatus_shouldReturnTicketResponseDTOList() {
    when(ticketRepository.findByStatus(TicketStatus.ACTIVE)).thenReturn(List.of(
        new TicketBO(1L, 1L, 5, TicketStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now()),
        new TicketBO(2L, 1L, 3, TicketStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now())));

    List<TicketResponseDTO> result = sut.getByStatus(TicketStatus.ACTIVE.getName());

    assertNotNull(result);
    assertEquals(2, result.size());
  }

  @Test
  void test_create_shouldReturnTicketResponseDTO() {
    when(ticketRepository.save(any()))
        .thenReturn(new TicketBO(1L, 1L, 5, TicketStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now()));

    when(employeeRepository.findById(anyLong()))
        .thenReturn(Optional.of(new EmployeeBO(1L, "Tony Strickland", "93958740067")));

    TicketResponseDTO result = sut.create(requestDTO);

    assertNotNull(result);
    assertEquals(responseDTO.getId(), result.getId());
    assertEquals(responseDTO.getQuantity(), result.getQuantity());
    verify(employeeRepository, times(1)).findById(anyLong());
  }

  @Test
  void test_update_shouldReturnTicketResponseDTO() {
    when(ticketRepository.findById(1L)).thenReturn(
        Optional.of(new TicketBO(1L, 1L, 5, TicketStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now())));
    when(ticketRepository.updateById(anyLong(), any()))
        .thenReturn(new TicketBO(1L, 1L, 5, TicketStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now()));

    TicketResponseDTO result = sut.update(1L, requestDTO);

    assertNotNull(result);
    assertEquals(responseDTO.getId(), result.getId());
    assertEquals(responseDTO.getQuantity(), result.getQuantity());
  }
}
