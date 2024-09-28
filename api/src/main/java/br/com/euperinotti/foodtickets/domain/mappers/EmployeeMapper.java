package br.com.euperinotti.foodtickets.domain.mappers;

import br.com.euperinotti.foodtickets.domain.dtos.request.EmployeeRequestDTO;
import br.com.euperinotti.foodtickets.domain.dtos.response.EmployeeResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;

public class EmployeeMapper {
  public static EmployeeBO toBO(EmployeeRequestDTO dto) {
    EmployeeBO bo = new EmployeeBO(dto.getId(), dto.getName(), dto.getCpf(), dto.getStatus(), dto.getCreatedAt(),
        dto.getUpdatedAt());

    return bo;
  }

  public static EmployeeResponseDTO toResponseDTO(EmployeeBO bo) {
    EmployeeResponseDTO dto = new EmployeeResponseDTO();

    dto.setId(bo.getId());
    dto.setName(bo.getName());
    dto.setCpf(bo.getCpf());
    dto.setStatus(bo.getStatus());
    dto.setCreatedAt(bo.getCreatedAt());
    dto.setUpdatedAt(bo.getUpdatedAt());

    return dto;
  }
}
