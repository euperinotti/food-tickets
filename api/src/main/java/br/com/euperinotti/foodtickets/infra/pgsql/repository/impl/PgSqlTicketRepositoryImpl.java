package br.com.euperinotti.foodtickets.infra.pgsql.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import br.com.euperinotti.foodtickets.domain.entities.TicketBO;
import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;
import br.com.euperinotti.foodtickets.domain.repository.ITicketRepository;
import br.com.euperinotti.foodtickets.infra.pgsql.entities.PgSqlTicketEntity;
import br.com.euperinotti.foodtickets.infra.pgsql.mappers.PgSqlTicketMapper;
import br.com.euperinotti.foodtickets.infra.pgsql.repository.contracts.PgSqlTicketRepository;

@Component("pgSqlTicketRepositoryImpl")
@Repository
public class PgSqlTicketRepositoryImpl implements ITicketRepository {

  private PgSqlTicketRepository jpa;

  public PgSqlTicketRepositoryImpl(PgSqlTicketRepository jpa) {
    this.jpa = jpa;
  }

  @Override
  public TicketBO save(TicketBO bo) {
    PgSqlTicketEntity entity = PgSqlTicketMapper.toEntity(bo);

    PgSqlTicketEntity created = jpa.save(entity);

    return PgSqlTicketMapper.toBO(created);
  }

  @Override
  public Optional<TicketBO> findById(Long id) {
    Optional<PgSqlTicketEntity> entity = jpa.findById(id);

    return entity.map(PgSqlTicketMapper::toBO);
  }

  @Override
  public void deleteById(Long id) {
    jpa.deleteById(id);

    return;
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
    PgSqlTicketEntity entity = PgSqlTicketMapper.toEntity(bo);

    PgSqlTicketEntity updated = jpa.save(entity);

    return PgSqlTicketMapper.toBO(updated);
  }

}
