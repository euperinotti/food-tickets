package br.com.euperinotti.foodtickets.domain.usecases.employee;

import br.com.euperinotti.foodtickets.application.dtos.response.EmployeeResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.exceptions.AppExceptions;
import br.com.euperinotti.foodtickets.domain.exceptions.enums.EmployeeExceptions;
import br.com.euperinotti.foodtickets.domain.mappers.EmployeeMapper;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;

public class FindById {
  private IEmployeeRepository repository;

  public FindById(IEmployeeRepository repository) {
    this.repository = repository;
  }

  public EmployeeResponseDTO execute(Long id) {
    EmployeeBO bo = repository.findById(id)
        .orElseThrow(() -> new AppExceptions(EmployeeExceptions.EMPLOYEE_NOT_FOUND.getMessage()));

    return EmployeeMapper.toResponseDTO(bo);
  }
}
