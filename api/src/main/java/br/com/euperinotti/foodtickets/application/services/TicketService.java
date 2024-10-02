package br.com.euperinotti.foodtickets.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.euperinotti.foodtickets.application.dtos.request.TicketRequestDTO;
import br.com.euperinotti.foodtickets.application.dtos.response.TicketResponseDTO;
import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;
import br.com.euperinotti.foodtickets.domain.repository.ITicketRepository;
import br.com.euperinotti.foodtickets.domain.usecases.ticket.CreateTicket;
import br.com.euperinotti.foodtickets.domain.usecases.ticket.FindAll;
import br.com.euperinotti.foodtickets.domain.usecases.ticket.FindById;
import br.com.euperinotti.foodtickets.domain.usecases.ticket.FindByStatus;
import br.com.euperinotti.foodtickets.domain.usecases.ticket.UpdateById;

@Service
public class TicketService {

  private ITicketRepository repository;

  private IEmployeeRepository employeeRepository;

  @Autowired
  public TicketService(ITicketRepository repository, IEmployeeRepository employeeRepository) {
    this.repository = repository;
    this.employeeRepository = employeeRepository;
  }

  public List<TicketResponseDTO> getAll() {
    FindAll usecase = new FindAll(repository);
    return usecase.execute();
  }

  public TicketResponseDTO getById(Long id) {
    FindById usecase = new FindById(repository);
    return usecase.execute(id);
  }

  public List<TicketResponseDTO> getByStatus(String status) {
    FindByStatus usecase = new FindByStatus(repository);
    return usecase.execute(TicketStatus.fromName(status));
  }

  public TicketResponseDTO create(TicketRequestDTO dto) {
    CreateTicket usecase = new CreateTicket(repository, employeeRepository);
    return usecase.execute(dto);
  }

  public TicketResponseDTO update(Long id, TicketRequestDTO dto) {
    UpdateById usecase = new UpdateById(repository);
    return usecase.execute(id, dto);
  }
}
