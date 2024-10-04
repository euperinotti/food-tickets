package br.com.euperinotti.foodtickets.domain.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import br.com.euperinotti.foodtickets.domain.entities.TicketBO;
import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;

public interface ITicketRepository {

    TicketBO save(TicketBO Ticket);

    Optional<TicketBO> findById(Long id);

    void deleteById(Long id);

    List<TicketBO> findAll();

    List<TicketBO> findByStatus(TicketStatus status);

    TicketBO updateById(Long id, TicketBO Ticket);

    Integer countTickets();

    LocalDateTime findDayWithMaxTickets();

    List<TicketBO> findAllTicketsFromPeriod(LocalDateTime period);
}
