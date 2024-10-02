package br.com.euperinotti.foodtickets.domain.usecases.employee;

import java.time.LocalDate;

import br.com.euperinotti.foodtickets.application.dtos.request.EmployeeRequestDTO;
import br.com.euperinotti.foodtickets.application.dtos.response.EmployeeResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.exceptions.AppExceptions;
import br.com.euperinotti.foodtickets.domain.exceptions.enums.EmployeeExceptions;
import br.com.euperinotti.foodtickets.domain.mappers.EmployeeMapper;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;

public class UpdateById {
  private IEmployeeRepository repository;

  public UpdateById(IEmployeeRepository repository) {
    this.repository = repository;
  }

  public EmployeeResponseDTO execute(Long id, EmployeeRequestDTO dto) {
    validate(id, dto);

    EmployeeBO bo = EmployeeMapper.toBO(dto);
    EmployeeBO updated = repository.updateById(id, bo);

    return EmployeeMapper.toResponseDTO(updated);
  }

  private void validate(Long id, EmployeeRequestDTO dto) {
    EmployeeBO bo = repository.findById(id).orElseThrow(() -> new AppExceptions(EmployeeExceptions.EMPLOYEE_NOT_FOUND.getMessage()));

    dto.setId(bo.getId());
    dto.setUpdatedAt(LocalDate.now());
  }
}
