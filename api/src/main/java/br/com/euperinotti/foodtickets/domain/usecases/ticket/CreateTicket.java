package br.com.euperinotti.foodtickets.domain.usecases.ticket;

import br.com.euperinotti.foodtickets.domain.dtos.request.TicketRequestDTO;
import br.com.euperinotti.foodtickets.domain.dtos.response.TicketResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.TicketBO;
import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;
import br.com.euperinotti.foodtickets.domain.mappers.TicketMapper;
import br.com.euperinotti.foodtickets.domain.repository.ITicketRepository;

public class CreateTicket {

  private ITicketRepository repository;

  public CreateTicket(ITicketRepository repository) {
    this.repository = repository;
  }

  public TicketResponseDTO execute(TicketRequestDTO dto) {
    dto.setStatus(TicketStatus.ACTIVE);

    TicketBO bo = TicketMapper.toBO(dto);
    TicketBO created = repository.save(bo);

    return TicketMapper.toResponseDTO(created);
  }

}
