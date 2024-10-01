package br.com.euperinotti.foodtickets.infra.pgsql.mappers;

import br.com.euperinotti.foodtickets.domain.entities.TicketBO;
import br.com.euperinotti.foodtickets.infra.pgsql.entities.PgSqlTicketEntity;

public class PgSqlTicketMapper {
  public static PgSqlTicketEntity toEntity(TicketBO bo) {
    PgSqlTicketEntity entity = new PgSqlTicketEntity();

    entity.setId(bo.getId());
    // entity.setEmployee(bo.getEmployeeId());
    entity.setQuantity(bo.getQuantity());
    entity.setStatus(bo.getStatus());
    entity.setCreatedAt(bo.getCreatedAt());
    entity.setUpdatedAt(bo.getUpdatedAt());

    return entity;
  }

  public static TicketBO toBO(PgSqlTicketEntity entity) {
    TicketBO bo = new TicketBO(entity.getId(), entity.getEmployee().getId(), entity.getQuantity(), entity.getStatus(),
        entity.getCreatedAt(),
        entity.getUpdatedAt());
    return bo;
  }
}
