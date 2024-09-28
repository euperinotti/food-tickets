package br.com.euperinotti.foodtickets.infra.pgsql.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import br.com.euperinotti.foodtickets.infra.pgsql.entities.PgSqlEmployeeEntity;

public class PgSqlEmployeeMapperTest {

  @Test
  public void test_toEntity_successfulMapping() {
    EmployeeBO bo = new EmployeeBO(1L, "John Doe", "123.456.789-10", EmployeeStatus.ACTIVE, LocalDate.now(),
        LocalDate.now());

    PgSqlEmployeeEntity entity = PgSqlEmployeeMapper.toEntity(bo);

    assertEquals(bo.getId(), entity.getId());
    assertEquals("JOHN DOE", entity.getName());
    assertEquals("12345678910", entity.getCpf());
    assertEquals(bo.getStatus(), entity.getStatus());
    assertEquals(bo.getCreatedAt(), entity.getCreatedAt());
    assertEquals(bo.getUpdatedAt(), entity.getUpdatedAt());
  }

  @Test
  public void test_toBO_successfulMapping() {
    PgSqlEmployeeEntity entity = new PgSqlEmployeeEntity();
    entity.setId(1L);
    entity.setName("John Doe");
    entity.setCpf("123.456.789-10");
    entity.setStatus(EmployeeStatus.ACTIVE);
    entity.setCreatedAt(LocalDate.now());
    entity.setUpdatedAt(LocalDate.now());

    EmployeeBO bo = PgSqlEmployeeMapper.toBO(entity);

    assertEquals(entity.getId(), bo.getId());
    assertEquals("JOHN DOE", bo.getName());
    assertEquals("12345678910", bo.getCpf());
    assertEquals(entity.getStatus(), bo.getStatus());
    assertEquals(entity.getCreatedAt(), bo.getCreatedAt());
    assertEquals(entity.getUpdatedAt(), bo.getUpdatedAt());
  }
}
