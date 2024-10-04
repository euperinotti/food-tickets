package br.com.euperinotti.foodtickets.infra.pgsql.repository.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.euperinotti.foodtickets.domain.entities.TicketBO;
import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;
import br.com.euperinotti.foodtickets.domain.exceptions.AppExceptions;
import br.com.euperinotti.foodtickets.domain.repository.ITicketRepository;
import br.com.euperinotti.foodtickets.infra.pgsql.entities.PgSqlEmployeeEntity;
import br.com.euperinotti.foodtickets.infra.pgsql.entities.PgSqlTicketEntity;
import br.com.euperinotti.foodtickets.infra.pgsql.mappers.PgSqlTicketMapper;
import br.com.euperinotti.foodtickets.infra.pgsql.repository.contracts.PgSqlEmployeeRepository;
import br.com.euperinotti.foodtickets.infra.pgsql.repository.contracts.PgSqlTicketRepository;

@Repository
public class PgSqlTicketRepositoryImplementation implements ITicketRepository {

  private PgSqlTicketRepository jpa;
  private PgSqlEmployeeRepository employeeJpa;

  @Autowired
  public PgSqlTicketRepositoryImplementation(PgSqlTicketRepository jpa, PgSqlEmployeeRepository employeeJpa) {
    this.jpa = jpa;
    this.employeeJpa = employeeJpa;
  }

  @Override
  public TicketBO save(TicketBO bo) {
    PgSqlEmployeeEntity employee = employeeJpa.findById(bo.getEmployeeId())
        .orElseThrow(() -> new AppExceptions(""));

    PgSqlTicketEntity entity = PgSqlTicketMapper.toEntity(bo, employee);
    PgSqlTicketEntity created = jpa.save(entity);

    return PgSqlTicketMapper.toBO(created);
  }

  @Override
  public Optional<TicketBO> findById(Long id) {
    Optional<PgSqlTicketEntity> entity = jpa.findById(id);

    return entity.map(PgSqlTicketMapper::toBO);
  }

  @Override
  public List<TicketBO> findAll() {
    List<PgSqlTicketEntity> Tickets = jpa.findAll();

    return Tickets.stream().map(PgSqlTicketMapper::toBO).collect(Collectors.toList());
  }

  @Override
  public List<TicketBO> findByStatus(TicketStatus status) {
    List<PgSqlTicketEntity> Tickets = jpa.findByStatus(status);

    return Tickets.stream().map(PgSqlTicketMapper::toBO).collect(Collectors.toList());
  }

  @Override
  public TicketBO updateById(Long id, TicketBO bo) {
    PgSqlEmployeeEntity employee = employeeJpa.findById(bo.getEmployeeId())
        .orElseThrow(() -> new AppExceptions(""));

    PgSqlTicketEntity entity = PgSqlTicketMapper.toEntity(bo, employee);
    PgSqlTicketEntity updated = jpa.save(entity);

    return PgSqlTicketMapper.toBO(updated);
  }

  @Override
  public Integer countTickets() {
    return jpa.countTickets();
  }

  @Override
  public LocalDateTime findDayWithMaxTickets() {
    return jpa.findDayWithMaxTickets();
  }

  @Override
  public List<TicketBO> findAllTicketsFromPeriod(LocalDateTime period) {
    List<PgSqlTicketEntity> tickets = jpa.findAllTicketsFromPeriod(period);

    return tickets.stream().map(PgSqlTicketMapper::toBO).collect(Collectors.toList());
  }
}
