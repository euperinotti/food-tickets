package br.com.euperinotti.foodtickets.domain.mappers;

import br.com.euperinotti.foodtickets.domain.dtos.request.TicketRequestDTO;
import br.com.euperinotti.foodtickets.domain.dtos.response.TicketResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.TicketBO;

public class TicketMapper {
  public static TicketBO toBO(TicketRequestDTO dto) {

    TicketBO bo = new TicketBO(dto.getId(), dto.getEmployeeId(), dto.getQuantity(), dto.getStatus(), dto.getCreatedAt(),
        dto.getUpdatedAt());

    return bo;
  }

  public static TicketResponseDTO toResponseDTO(TicketBO bo) {
    TicketResponseDTO dto = new TicketResponseDTO();

    dto.setId(bo.getId());
    dto.setEmployeeId(bo.getEmployeeId());
    dto.setQuantity(bo.getQuantity());
    dto.setStatus(bo.getStatus());
    dto.setCreatedAt(bo.getCreatedAt());
    dto.setUpdatedAt(bo.getUpdatedAt());

    return dto;
  }

}
