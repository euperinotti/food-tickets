package br.com.euperinotti.foodtickets.domain.usecases.ticket;

import java.util.List;
import java.util.stream.Collectors;

import br.com.euperinotti.foodtickets.application.dtos.response.TicketResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.TicketBO;
import br.com.euperinotti.foodtickets.domain.mappers.TicketMapper;
import br.com.euperinotti.foodtickets.domain.repository.ITicketRepository;

public class FindAll {
  private ITicketRepository repository;

  public FindAll(ITicketRepository repository) {
    this.repository = repository;
  }

  public List<TicketResponseDTO> execute() {
    List<TicketBO> Tickets = repository.findAll();
    return Tickets.stream().map(TicketMapper::toResponseDTO).collect(Collectors.toList());
  }
}
