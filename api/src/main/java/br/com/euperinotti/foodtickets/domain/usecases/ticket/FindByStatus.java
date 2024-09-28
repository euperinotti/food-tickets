package br.com.euperinotti.foodtickets.domain.usecases.ticket;

import java.util.List;
import java.util.stream.Collectors;

import br.com.euperinotti.foodtickets.domain.dtos.response.TicketResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.TicketBO;
import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;
import br.com.euperinotti.foodtickets.domain.mappers.TicketMapper;
import br.com.euperinotti.foodtickets.domain.repository.ITicketRepository;

public class FindByStatus {
  private ITicketRepository repository;

  public FindByStatus(ITicketRepository repository) {
    this.repository = repository;
  }

  public List<TicketResponseDTO> execute(TicketStatus status) {
    List<TicketBO> Tickets = repository.findByStatus(status);

    return Tickets.stream().map(TicketMapper::toResponseDTO).collect(Collectors.toList());
  }
}
