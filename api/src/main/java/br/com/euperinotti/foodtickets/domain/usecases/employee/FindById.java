package br.com.euperinotti.foodtickets.domain.usecases.employee;

import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;

public class FindById {
  private IEmployeeRepository repository;

  public FindById(IEmployeeRepository repository) {
    this.repository = repository;
  }

  public EmployeeBO execute(Long id) {
    return repository.findById(id);
  }
}
