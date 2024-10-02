package br.com.euperinotti.foodtickets.infra.pgsql.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import br.com.euperinotti.foodtickets.domain.entities.TicketBO;
import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;
import br.com.euperinotti.foodtickets.infra.pgsql.entities.PgSqlEmployeeEntity;
import br.com.euperinotti.foodtickets.infra.pgsql.entities.PgSqlTicketEntity;

class PgSqlTicketMapperTest {

  @Test
  void test_toEntity_shouldMapToPgSqlTicketEntity() {
    TicketBO bo = new TicketBO(1L, 1L, 5, TicketStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now());
    PgSqlEmployeeEntity employee = new PgSqlEmployeeEntity();
    employee.setId(1L);

    PgSqlTicketEntity entity = PgSqlTicketMapper.toEntity(bo, employee);

    assertNotNull(entity);
    assertEquals(employee, entity.getEmployee());
    assertEquals(bo.getQuantity(), entity.getQuantity());
    assertEquals(bo.getStatus(), entity.getStatus());
    assertEquals(bo.getCreatedAt(), entity.getCreatedAt());
    assertEquals(bo.getUpdatedAt(), entity.getUpdatedAt());
  }

  @Test
  void test_toBO_shouldMapToTicketBO() {
    PgSqlTicketEntity entity = new PgSqlTicketEntity();
    entity.setId(1L);
    PgSqlEmployeeEntity employee = new PgSqlEmployeeEntity();
    employee.setId(1L);
    entity.setEmployee(employee);
    entity.setQuantity(5);
    entity.setStatus(TicketStatus.ACTIVE);
    entity.setCreatedAt(LocalDateTime.now());
    entity.setUpdatedAt(LocalDateTime.now());

    TicketBO bo = PgSqlTicketMapper.toBO(entity);

    assertNotNull(bo);
    assertEquals(entity.getId(), bo.getId());
    assertEquals(entity.getEmployee().getId(), bo.getEmployeeId());
    assertEquals(entity.getQuantity(), bo.getQuantity());
    assertEquals(entity.getStatus(), bo.getStatus());
    assertEquals(entity.getCreatedAt(), bo.getCreatedAt());
    assertEquals(entity.getUpdatedAt(), bo.getUpdatedAt());
  }
}
