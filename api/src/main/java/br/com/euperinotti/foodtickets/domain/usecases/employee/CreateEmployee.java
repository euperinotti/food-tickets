package br.com.euperinotti.foodtickets.domain.usecases.employee;

import java.util.Optional;

import br.com.euperinotti.foodtickets.application.dtos.request.EmployeeRequestDTO;
import br.com.euperinotti.foodtickets.application.dtos.response.EmployeeResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import br.com.euperinotti.foodtickets.domain.exceptions.AppExceptions;
import br.com.euperinotti.foodtickets.domain.exceptions.enums.EmployeeExceptions;
import br.com.euperinotti.foodtickets.domain.mappers.EmployeeMapper;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;
import br.com.euperinotti.foodtickets.domain.utils.StringUtils;
import br.com.euperinotti.foodtickets.domain.validators.CpfValidator;

public class CreateEmployee {

  private IEmployeeRepository repository;

  public CreateEmployee(IEmployeeRepository repository) {
    this.repository = repository;
  }

  public EmployeeResponseDTO execute(EmployeeRequestDTO dto) {
    validate(dto);

    EmployeeBO bo = EmployeeMapper.toBO(dto);
    EmployeeBO created = repository.save(bo);

    return EmployeeMapper.toResponseDTO(created);
  }

  public void validate(EmployeeRequestDTO dto) {
    boolean isValidCpf = CpfValidator.isValidCPF(dto.getCpf());

    if (!isValidCpf) {
      throw new AppExceptions(EmployeeExceptions.EMPLOYEE_INVALID_CPF.getMessage());
    }

    Optional<EmployeeBO> bo = repository.findByCpf(StringUtils.sanitizeCpf(dto.getCpf()));

    if (bo.isPresent()) {
      throw new AppExceptions(EmployeeExceptions.CPF_ALREADY_EXISTS.getMessage());
    }


    dto.setStatus(EmployeeStatus.ACTIVE);
  }

}
