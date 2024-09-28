package br.com.euperinotti.foodtickets.domain.usecases.employee;

import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.exceptions.AppExceptions;
import br.com.euperinotti.foodtickets.domain.exceptions.enums.EmployeeExceptions;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;

public class FindByCpf {
  private IEmployeeRepository repository;

  public FindByCpf(IEmployeeRepository repository) {
    this.repository = repository;
  }

  public EmployeeBO execute(String cpf) {
    return repository.findByCpf(cpf)
        .orElseThrow(() -> new AppExceptions(EmployeeExceptions.EMPLOYEE_NOT_FOUND.getMessage()));
  }
}
