package br.com.euperinotti.foodtickets.domain.usecases.ticket;

import br.com.euperinotti.foodtickets.domain.dtos.request.TicketRequestDTO;
import br.com.euperinotti.foodtickets.domain.dtos.response.TicketResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.TicketBO;
import br.com.euperinotti.foodtickets.domain.mappers.TicketMapper;
import br.com.euperinotti.foodtickets.domain.repository.ITicketRepository;

public class UpdateById {
  private ITicketRepository repository;

  public UpdateById(ITicketRepository repository) {
    this.repository = repository;
  }

  public TicketResponseDTO execute(Long id, TicketRequestDTO dto) {
    validateExisting(id);
    dto.setId(id);

    TicketBO bo = TicketMapper.toBO(dto);
    TicketBO updated = repository.updateById(id, bo);

    return TicketMapper.toResponseDTO(updated);
  }

  private void validateExisting(Long id) {

  }
}
