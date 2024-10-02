package br.com.euperinotti.foodtickets.domain.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import br.com.euperinotti.foodtickets.application.dtos.request.TicketRequestDTO;
import br.com.euperinotti.foodtickets.application.dtos.response.TicketResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.TicketBO;
import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;

class TicketMapperTest {

  @Test
  void test_toBO_shouldMapDTOToBO() {
    TicketRequestDTO dto = new TicketRequestDTO();
    dto.setId(1L);
    dto.setEmployeeId(10L);
    dto.setQuantity(5);
    dto.setStatus(TicketStatus.ACTIVE);
    dto.setCreatedAt(LocalDateTime.now());
    dto.setUpdatedAt(LocalDateTime.now());

    TicketBO bo = TicketMapper.toBO(dto);

    assertNotNull(bo);
    assertEquals(dto.getId(), bo.getId());
    assertEquals(dto.getEmployeeId(), bo.getEmployeeId());
    assertEquals(dto.getQuantity(), bo.getQuantity());
    assertEquals(dto.getStatus(), bo.getStatus());
    assertEquals(dto.getCreatedAt(), bo.getCreatedAt());
    assertEquals(dto.getUpdatedAt(), bo.getUpdatedAt());
  }

  @Test
  void test_toResponseDTO_shouldMapBOToResponseDTO() {
    TicketBO bo = new TicketBO(1L, 10L, 5, TicketStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now());

    TicketResponseDTO dto = TicketMapper.toResponseDTO(bo);

    assertNotNull(dto);
    assertEquals(bo.getId(), dto.getId());
    assertEquals(bo.getEmployeeId(), dto.getEmployeeId());
    assertEquals(bo.getQuantity(), dto.getQuantity());
    assertEquals(bo.getStatus(), dto.getStatus());
    assertEquals(bo.getCreatedAt(), dto.getCreatedAt());
    assertEquals(bo.getUpdatedAt(), dto.getUpdatedAt());
  }
}
