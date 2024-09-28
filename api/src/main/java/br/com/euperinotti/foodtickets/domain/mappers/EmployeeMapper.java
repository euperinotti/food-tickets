package br.com.euperinotti.foodtickets.domain.mappers;

import br.com.euperinotti.foodtickets.domain.dtos.request.EmployeeRequestDTO;
import br.com.euperinotti.foodtickets.domain.dtos.response.EmployeeResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.utils.StringUtils;

public class EmployeeMapper {
  public static EmployeeBO toBO(EmployeeRequestDTO dto) {
    String name = StringUtils.sanitizeName(dto.getName());
    String cpf = StringUtils.sanitizeCpf(dto.getCpf());

    EmployeeBO bo = new EmployeeBO(dto.getId(), name, cpf, dto.getStatus(), dto.getCreatedAt(),
        dto.getUpdatedAt());

    return bo;
  }

  public static EmployeeResponseDTO toResponseDTO(EmployeeBO bo) {
    EmployeeResponseDTO dto = new EmployeeResponseDTO();

    String name = StringUtils.sanitizeName(bo.getName());
    String cpf = StringUtils.sanitizeCpf(bo.getCpf());

    dto.setId(bo.getId());
    dto.setName(name);
    dto.setCpf(cpf);
    dto.setStatus(bo.getStatus());
    dto.setCreatedAt(bo.getCreatedAt());
    dto.setUpdatedAt(bo.getUpdatedAt());

    return dto;
  }
}
