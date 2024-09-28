package br.com.euperinotti.foodtickets.domain.usecases.employee;

import java.util.List;

import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;

public class FindAll {
  private IEmployeeRepository repository;

  public FindAll(IEmployeeRepository repository) {
    this.repository = repository;
  }

  public List<EmployeeBO> execute() {
    return repository.findAll();
  }
}
