package br.com.euperinotti.foodtickets.infra.pgsql.mappers;

import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.utils.StringUtils;
import br.com.euperinotti.foodtickets.infra.pgsql.entities.PgSqlEmployeeEntity;

public class PgSqlEmployeeMapper {
  public static PgSqlEmployeeEntity toEntity(EmployeeBO bo) {
    PgSqlEmployeeEntity entity = new PgSqlEmployeeEntity();

    String name = StringUtils.sanitizeName(bo.getName());
    String cpf = StringUtils.sanitizeCpf(bo.getCpf());

    entity.setId(bo.getId());
    entity.setName(name);
    entity.setCpf(cpf);
    entity.setStatus(bo.getStatus());
    entity.setCreatedAt(bo.getCreatedAt());
    entity.setUpdatedAt(bo.getUpdatedAt());

    return entity;
  }

  public static EmployeeBO toBO(PgSqlEmployeeEntity entity) {
    String name = StringUtils.sanitizeName(entity.getName());
    String cpf = StringUtils.sanitizeCpf(entity.getCpf());

    EmployeeBO bo = new EmployeeBO(entity.getId(), name, cpf, entity.getStatus(), entity.getCreatedAt(), entity.getUpdatedAt());
    return bo;
  }
}
