package br.com.euperinotti.foodtickets.infra.pgsql.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;
import br.com.euperinotti.foodtickets.infra.pgsql.entities.PgSqlEmployeeEntity;
import br.com.euperinotti.foodtickets.infra.pgsql.mappers.PgSqlEmployeeMapper;
import br.com.euperinotti.foodtickets.infra.pgsql.repository.contracts.PgSqlEmployeeRepository;

@Repository
public class PgSqlEmployeeRepositoryImpl implements IEmployeeRepository {

  @Autowired
  private PgSqlEmployeeRepository jpa;

  @Override
  public EmployeeBO save(EmployeeBO bo) {
    PgSqlEmployeeEntity entity = PgSqlEmployeeMapper.toEntity(bo);

    PgSqlEmployeeEntity created = jpa.save(entity);

    return PgSqlEmployeeMapper.toBO(created);
  }

  @Override
  public Optional<EmployeeBO> findById(Long id) {
    Optional<PgSqlEmployeeEntity> entity = jpa.findById(id);

    return entity.map(PgSqlEmployeeMapper::toBO);
  }

  @Override
  public void deleteById(Long id) {
    jpa.deleteById(id);

    return;
  }

  @Override
  public List<EmployeeBO> findAll() {
    List<PgSqlEmployeeEntity> employees = jpa.findAll();

    return employees.stream().map(PgSqlEmployeeMapper::toBO).collect(Collectors.toList());
  }

  @Override
  public List<EmployeeBO> findByStatus(EmployeeStatus status) {
    List<PgSqlEmployeeEntity> employees = jpa.findByStatus(status.getName());

    return employees.stream().map(PgSqlEmployeeMapper::toBO).collect(Collectors.toList());
  }

  @Override
  public Optional<EmployeeBO> findByCpf(String cpf) {
    Optional<PgSqlEmployeeEntity> entity = jpa.findByCpf(cpf);

    return entity.map(PgSqlEmployeeMapper::toBO);
  }

  @Override
  public EmployeeBO updateById(Long id, EmployeeBO bo) {
    PgSqlEmployeeEntity entity = PgSqlEmployeeMapper.toEntity(bo);

    PgSqlEmployeeEntity updated = jpa.save(entity);

    return PgSqlEmployeeMapper.toBO(updated);
  }

}
