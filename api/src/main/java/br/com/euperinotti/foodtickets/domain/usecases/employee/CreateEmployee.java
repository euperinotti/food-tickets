package br.com.euperinotti.foodtickets.domain.usecases.employee;

import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;

public class CreateEmployee {

  private IEmployeeRepository repository;

  public CreateEmployee(IEmployeeRepository repository) {
    this.repository = repository;
  }

  public EmployeeBO execute(EmployeeBO bo) {
    EmployeeBO created = repository.save(bo);
    return created;
  }

}
