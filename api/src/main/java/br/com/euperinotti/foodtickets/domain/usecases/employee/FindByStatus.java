package br.com.euperinotti.foodtickets.domain.usecases.employee;

import java.util.List;

import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;

public class FindByStatus {
  private IEmployeeRepository repository;

  public FindByStatus(IEmployeeRepository repository) {
    this.repository = repository;
  }

  public List<EmployeeBO> execute(EmployeeStatus status) {
    return repository.findByStatus(status);
  }
}
