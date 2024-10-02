package br.com.euperinotti.foodtickets.domain.usecases.ticket;

import java.time.LocalDateTime;

import br.com.euperinotti.foodtickets.application.dtos.request.TicketRequestDTO;
import br.com.euperinotti.foodtickets.application.dtos.response.TicketResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.TicketBO;
import br.com.euperinotti.foodtickets.domain.exceptions.AppExceptions;
import br.com.euperinotti.foodtickets.domain.exceptions.enums.TicketExceptions;
import br.com.euperinotti.foodtickets.domain.mappers.TicketMapper;
import br.com.euperinotti.foodtickets.domain.repository.ITicketRepository;

public class UpdateById {
  private ITicketRepository repository;

  public UpdateById(ITicketRepository repository) {
    this.repository = repository;
  }

  public TicketResponseDTO execute(Long id, TicketRequestDTO dto) {
    validate(id, dto);

    TicketBO bo = TicketMapper.toBO(dto);
    TicketBO updated = repository.updateById(id, bo);

    return TicketMapper.toResponseDTO(updated);
  }

  private void validate(Long id, TicketRequestDTO dto) {
    TicketBO bo = repository.findById(id).orElseThrow(() -> new AppExceptions(TicketExceptions.TICKET_NOT_FOUND.getMessage()));

    dto.setId(bo.getId());
    dto.setUpdatedAt(LocalDateTime.now());
  }
}
