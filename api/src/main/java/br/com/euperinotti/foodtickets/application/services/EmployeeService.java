package br.com.euperinotti.foodtickets.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.euperinotti.foodtickets.application.dtos.request.EmployeeRequestDTO;
import br.com.euperinotti.foodtickets.application.dtos.response.EmployeeResponseDTO;
import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;
import br.com.euperinotti.foodtickets.domain.usecases.employee.CreateEmployee;
import br.com.euperinotti.foodtickets.domain.usecases.employee.FindAll;
import br.com.euperinotti.foodtickets.domain.usecases.employee.FindById;
import br.com.euperinotti.foodtickets.domain.usecases.employee.FindByStatus;
import br.com.euperinotti.foodtickets.domain.usecases.employee.UpdateById;

@Service
public class EmployeeService {

  private IEmployeeRepository repository;

  @Autowired
  public EmployeeService(IEmployeeRepository repository) {
    this.repository = repository;
  }

  public List<EmployeeResponseDTO> getAll() {
    FindAll usecase = new FindAll(repository);
    return usecase.execute();
  }

  public EmployeeResponseDTO getById(Long id) {
    FindById usecase = new FindById(repository);
    return usecase.execute(id);
  }

  public List<EmployeeResponseDTO> getByStatus(String status) {
    FindByStatus usecase = new FindByStatus(repository);
    return usecase.execute(EmployeeStatus.fromName(status));
  }

  public EmployeeResponseDTO create(EmployeeRequestDTO dto) {
    CreateEmployee usecase = new CreateEmployee(repository);
    return usecase.execute(dto);
  }

  public EmployeeResponseDTO update(Long id, EmployeeRequestDTO dto) {
    UpdateById usecase = new UpdateById(repository);
    return usecase.execute(id, dto);
  }
}
