package br.com.euperinotti.foodtickets.domain.usecases.employee;

import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;

public class DeleteById {
  private IEmployeeRepository repository;

  public DeleteById(IEmployeeRepository repository) {
    this.repository = repository;
  }

  public void execute(Long id) {
    repository.deleteById(id);
    return;
  }
}
