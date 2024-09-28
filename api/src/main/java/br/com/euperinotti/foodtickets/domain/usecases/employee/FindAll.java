package br.com.euperinotti.foodtickets.domain.usecases.employee;

import java.util.List;
import java.util.stream.Collectors;

import br.com.euperinotti.foodtickets.domain.dtos.response.EmployeeResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.mappers.EmployeeMapper;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;

public class FindAll {
  private IEmployeeRepository repository;

  public FindAll(IEmployeeRepository repository) {
    this.repository = repository;
  }

  public List<EmployeeResponseDTO> execute() {
    List<EmployeeBO> employees = repository.findAll();
    return employees.stream().map(EmployeeMapper::toResponseDTO).collect(Collectors.toList());
  }
}
