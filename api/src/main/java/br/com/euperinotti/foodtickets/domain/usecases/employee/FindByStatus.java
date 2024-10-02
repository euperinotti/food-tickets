package br.com.euperinotti.foodtickets.domain.usecases.employee;

import java.util.List;
import java.util.stream.Collectors;

import br.com.euperinotti.foodtickets.application.dtos.response.EmployeeResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import br.com.euperinotti.foodtickets.domain.mappers.EmployeeMapper;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;

public class FindByStatus {
  private IEmployeeRepository repository;

  public FindByStatus(IEmployeeRepository repository) {
    this.repository = repository;
  }

  public List<EmployeeResponseDTO> execute(EmployeeStatus status) {
    List<EmployeeBO> employees = repository.findByStatus(status);

    return employees.stream().map(EmployeeMapper::toResponseDTO).collect(Collectors.toList());
  }
}
