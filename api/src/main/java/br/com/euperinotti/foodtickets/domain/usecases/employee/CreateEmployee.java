package br.com.euperinotti.foodtickets.domain.usecases.employee;

import br.com.euperinotti.foodtickets.domain.dtos.request.EmployeeRequestDTO;
import br.com.euperinotti.foodtickets.domain.dtos.response.EmployeeResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.mappers.EmployeeMapper;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;

public class CreateEmployee {

  private IEmployeeRepository repository;

  public CreateEmployee(IEmployeeRepository repository) {
    this.repository = repository;
  }

  public EmployeeResponseDTO execute(EmployeeRequestDTO dto) {
    EmployeeBO bo = EmployeeMapper.toBO(dto);
    EmployeeBO created = repository.save(bo);

    return EmployeeMapper.toResponseDTO(created);
  }

}
