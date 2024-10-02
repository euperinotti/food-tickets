package br.com.euperinotti.foodtickets.domain.usecases.ticket;

import java.time.LocalDate;

import br.com.euperinotti.foodtickets.application.dtos.request.TicketRequestDTO;
import br.com.euperinotti.foodtickets.application.dtos.response.TicketResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.entities.TicketBO;
import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;
import br.com.euperinotti.foodtickets.domain.exceptions.AppExceptions;
import br.com.euperinotti.foodtickets.domain.exceptions.enums.EmployeeExceptions;
import br.com.euperinotti.foodtickets.domain.mappers.TicketMapper;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;
import br.com.euperinotti.foodtickets.domain.repository.ITicketRepository;

public class CreateTicket {

  private ITicketRepository repository;
  private IEmployeeRepository employeeRepository;

  public CreateTicket(ITicketRepository repository, IEmployeeRepository employeeRepository) {
    this.repository = repository;
    this.employeeRepository = employeeRepository;
  }

  public TicketResponseDTO execute(TicketRequestDTO dto) {
    validate(dto);

    TicketBO bo = TicketMapper.toBO(dto);
    TicketBO created = repository.save(bo);

    return TicketMapper.toResponseDTO(created);
  }

  public void validate(TicketRequestDTO dto) {
    EmployeeBO employee = employeeRepository.findById(dto.getEmployeeId())
        .orElseThrow(() -> new AppExceptions(EmployeeExceptions.EMPLOYEE_NOT_FOUND.getMessage()));

    dto.setId(employee.getId());
    dto.setStatus(TicketStatus.ACTIVE);
    dto.setCreatedAt(LocalDate.now());
    dto.setUpdatedAt(LocalDate.now());
  }

}
