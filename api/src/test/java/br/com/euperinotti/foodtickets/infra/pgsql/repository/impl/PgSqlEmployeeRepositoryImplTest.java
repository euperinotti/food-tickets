package br.com.euperinotti.foodtickets.infra.pgsql.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import br.com.euperinotti.foodtickets.infra.pgsql.entities.PgSqlEmployeeEntity;
import br.com.euperinotti.foodtickets.infra.pgsql.mappers.PgSqlEmployeeMapper;
import br.com.euperinotti.foodtickets.infra.pgsql.repository.contracts.PgSqlEmployeeRepository;

@DataJpaTest
@Import(PgSqlEmployeeRepositoryImpl.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PgSqlEmployeeRepositoryImplTest {

  @Autowired
  private PgSqlEmployeeRepositoryImpl repository;

  @Autowired
  private PgSqlEmployeeRepository jpa;

  private EmployeeBO bo;

  @BeforeEach
  void setUp() {
    bo = new EmployeeBO(1L, "John Doe", "123.456.789-10", EmployeeStatus.ACTIVE, LocalDate.now(), LocalDate.now());
  }

  private PgSqlEmployeeEntity makeInactiveEmployeeEntity() {
    PgSqlEmployeeEntity inactiveEmployee = new PgSqlEmployeeEntity();
    inactiveEmployee.setId(1L);
    inactiveEmployee.setName("Inactive Employee");
    inactiveEmployee.setCpf("987.654.321-00");
    inactiveEmployee.setStatus(EmployeeStatus.INACTIVE);
    inactiveEmployee.setCreatedAt(LocalDate.now());
    inactiveEmployee.setUpdatedAt(LocalDate.now());

    return inactiveEmployee;
  }

  @Test
  public void test_save_successful() {
    EmployeeBO result = repository.save(bo);

    assertNotNull(result);
    assertEquals(bo.getId(), result.getId());

    Optional<PgSqlEmployeeEntity> entityInDb = jpa.findById(result.getId());
    assertTrue(entityInDb.isPresent());
    assertEquals("John Doe", entityInDb.get().getName());
  }

  @Test
  public void test_findById_found() {
    PgSqlEmployeeEntity entity = PgSqlEmployeeMapper.toEntity(bo);
    entity.setId(null);

    jpa.save(entity);

    Optional<EmployeeBO> result = repository.findById(1L);

    assertTrue(result.isPresent());
    assertEquals("John Doe", result.get().getName());
  }

  @Test
  public void test_findById_notFound() {
    Optional<EmployeeBO> result = repository.findById(999L);

    assertFalse(result.isPresent());
  }

  @Test
  public void test_deleteById() {
    PgSqlEmployeeEntity entity = PgSqlEmployeeMapper.toEntity(bo);
    jpa.save(entity);

    repository.deleteById(1L);

    Optional<PgSqlEmployeeEntity> deletedEntity = jpa.findById(1L);
    assertFalse(deletedEntity.isPresent());
  }

  @Test
  public void test_findAll_successful() {
    PgSqlEmployeeEntity entity1 = PgSqlEmployeeMapper.toEntity(bo);
    entity1.setId(null);

    PgSqlEmployeeEntity entity2 = new PgSqlEmployeeEntity();
    entity2.setId(null);
    entity2.setName("Jane Doe");
    entity2.setStatus(EmployeeStatus.INACTIVE);
    entity2.setCreatedAt(LocalDate.now());
    entity2.setUpdatedAt(LocalDate.now());
    entity2.setCpf("987.654.321-09");

    jpa.save(entity1);
    jpa.save(entity2);

    List<EmployeeBO> result = repository.findAll();

    assertEquals(2, result.size());
    assertEquals("John Doe", result.get(0).getName());
    assertEquals("Jane Doe", result.get(1).getName());
  }

  @Test
  public void test_findByStatus_successful() {
    PgSqlEmployeeEntity entity = PgSqlEmployeeMapper.toEntity(bo);
    jpa.save(entity);

    List<EmployeeBO> result = repository.findByStatus(EmployeeStatus.ACTIVE);

    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals("John Doe", result.get(0).getName());
  }

  @Test
  public void test_findByStatus_activeEmployees() {
    PgSqlEmployeeEntity activeEmployee = new PgSqlEmployeeEntity();
    activeEmployee.setId(1L);
    activeEmployee.setName("Active Employee");
    activeEmployee.setCpf("123.456.789-10");
    activeEmployee.setStatus(EmployeeStatus.ACTIVE);
    activeEmployee.setCreatedAt(LocalDate.now());
    activeEmployee.setUpdatedAt(LocalDate.now());

    PgSqlEmployeeEntity inactiveEmployee = makeInactiveEmployeeEntity();

    jpa.save(activeEmployee);
    jpa.save(inactiveEmployee);

    List<EmployeeBO> activeEmployees = repository.findByStatus(EmployeeStatus.ACTIVE);

    assertNotNull(activeEmployees);
    assertEquals(1, activeEmployees.size());
    assertEquals("Active Employee", activeEmployees.get(0).getName());
  }

  @Test
  public void test_findByStatus_noActiveEmployees() {
    PgSqlEmployeeEntity inactiveEmployee = makeInactiveEmployeeEntity();
    jpa.save(inactiveEmployee);

    List<EmployeeBO> activeEmployees = repository.findByStatus(EmployeeStatus.ACTIVE);

    assertTrue(activeEmployees.isEmpty());
  }

  @Test
  public void test_findByCpf_successful() {
    PgSqlEmployeeEntity entity = PgSqlEmployeeMapper.toEntity(bo);
    jpa.save(entity);

    Optional<EmployeeBO> result = repository.findByCpf("123.456.789-10");

    assertTrue(result.isPresent());
    assertEquals("123.456.789-10", result.get().getCpf());
  }

  @Test
  public void test_findByCpf_notFound() {
    Optional<EmployeeBO> result = repository.findByCpf("987.654.321-00");

    assertFalse(result.isPresent());
  }

  @Test
  public void test_updateById_successful() {
    PgSqlEmployeeEntity entity = PgSqlEmployeeMapper.toEntity(bo);
    jpa.save(entity);

    EmployeeBO updatedBO = new EmployeeBO(1L, "Updated Name", "123.456.789-10", EmployeeStatus.ACTIVE, LocalDate.now(),
        LocalDate.now());

    EmployeeBO result = repository.updateById(1L, updatedBO);

    assertNotNull(result);
    assertEquals("Updated Name", result.getName());
  }
}
