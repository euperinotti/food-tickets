package br.com.euperinotti.foodtickets.infra.pgsql.mappers;

import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.infra.pgsql.entities.PgSqlEmployeeEntity;

public class PgSqlEmployeeMapper {
  public static PgSqlEmployeeEntity toEntity(EmployeeBO bo) {
    PgSqlEmployeeEntity entity = new PgSqlEmployeeEntity();
    entity.setId(bo.getId());
    entity.setName(bo.getName());
    entity.setCpf(bo.getCpf());
    entity.setStatus(bo.getStatus());
    entity.setCreatedAt(bo.getCreatedAt());
    entity.setUpdatedAt(bo.getUpdatedAt());

    return entity;
  }

  public static EmployeeBO toBO(PgSqlEmployeeEntity entity) {
    EmployeeBO bo = new EmployeeBO(entity.getId(), entity.getName(), entity.getCpf(), entity.getStatus(), entity.getCreatedAt(), entity.getUpdatedAt());
    return bo;
  }
}
