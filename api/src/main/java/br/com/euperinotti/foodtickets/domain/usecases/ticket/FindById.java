package br.com.euperinotti.foodtickets.domain.usecases.ticket;

import br.com.euperinotti.foodtickets.domain.dtos.response.TicketResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.TicketBO;
import br.com.euperinotti.foodtickets.domain.exceptions.AppExceptions;
import br.com.euperinotti.foodtickets.domain.exceptions.enums.TicketExceptions;
import br.com.euperinotti.foodtickets.domain.mappers.TicketMapper;
import br.com.euperinotti.foodtickets.domain.repository.ITicketRepository;

public class FindById {
  private ITicketRepository repository;

  public FindById(ITicketRepository repository) {
    this.repository = repository;
  }

  public TicketResponseDTO execute(Long id) {
    TicketBO bo = repository.findById(id)
        .orElseThrow(() -> new AppExceptions(TicketExceptions.TICKET_NOT_FOUND.getMessage()));

    return TicketMapper.toResponseDTO(bo);
  }
}
