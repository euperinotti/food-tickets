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
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import br.com.euperinotti.foodtickets.infra.pgsql.entities.PgSqlEmployeeEntity;
import br.com.euperinotti.foodtickets.infra.pgsql.mappers.PgSqlEmployeeMapper;
import br.com.euperinotti.foodtickets.infra.pgsql.repository.contracts.PgSqlEmployeeRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PgSqlEmployeeRepositoryImplTest {

  @Autowired
  private PgSqlEmployeeRepository jpa;

  private PgSqlEmployeeRepositoryImpl sut;

  private EmployeeBO bo;

  @BeforeEach
  void setUp() {
    this.sut = new PgSqlEmployeeRepositoryImpl(jpa);
    this.bo = new EmployeeBO(1L, "John Doe", "123.456.789-10", EmployeeStatus.ACTIVE, LocalDate.now(), LocalDate.now());
  }

  private PgSqlEmployeeEntity makeInactiveEmployeeEntity() {
    PgSqlEmployeeEntity inactiveEmployee = new PgSqlEmployeeEntity();
    inactiveEmployee.setName("Kyle Schmidt");
    inactiveEmployee.setCpf("66418505745");
    inactiveEmployee.setStatus(EmployeeStatus.INACTIVE);
    inactiveEmployee.setCreatedAt(LocalDate.now());
    inactiveEmployee.setUpdatedAt(LocalDate.now());

    return inactiveEmployee;
  }

  private PgSqlEmployeeEntity makeActiveEmployeeEntity() {
    PgSqlEmployeeEntity inactiveEmployee = new PgSqlEmployeeEntity();
    inactiveEmployee.setName("Ophelia Beck");
    inactiveEmployee.setCpf("72197234469");
    inactiveEmployee.setStatus(EmployeeStatus.ACTIVE);
    inactiveEmployee.setCreatedAt(LocalDate.now());
    inactiveEmployee.setUpdatedAt(LocalDate.now());

    return inactiveEmployee;
  }

  @Test
  public void test_save_successful() {
    EmployeeBO result = sut.save(bo);

    assertNotNull(result);
    assertEquals("JOHN DOE", result.getName());
  }

  @Test
  public void test_findById_found() {
    PgSqlEmployeeEntity entity = PgSqlEmployeeMapper.toEntity(bo);
    entity.setId(null);

    jpa.save(entity);

    Optional<EmployeeBO> result = sut.findById(1L);

    assertTrue(result.isPresent());
    assertEquals(1L, result.get().getId());
  }

  @Test
  public void test_findById_notFound() {
    Optional<EmployeeBO> result = sut.findById(999L);

    assertFalse(result.isPresent());
  }

  @Test
  public void test_deleteById() {
    PgSqlEmployeeEntity entity = PgSqlEmployeeMapper.toEntity(bo);
    jpa.save(entity);

    sut.deleteById(1L);

    Optional<PgSqlEmployeeEntity> deletedEntity = jpa.findById(1L);
    assertFalse(deletedEntity.isPresent());
  }

  @Test
  public void test_findAll_successful() {
    PgSqlEmployeeEntity entity1 = PgSqlEmployeeMapper.toEntity(bo);
    entity1.setId(null);

    PgSqlEmployeeEntity entity2 = new PgSqlEmployeeEntity();
    entity2.setName("Jane Doe");
    entity2.setStatus(EmployeeStatus.INACTIVE);
    entity2.setCreatedAt(LocalDate.now());
    entity2.setUpdatedAt(LocalDate.now());
    entity2.setCpf("987.654.321-09");

    jpa.save(entity1);
    jpa.save(entity2);

    List<EmployeeBO> result = sut.findAll();

    assertEquals(2, result.size());
    assertEquals("JOHN DOE", result.get(0).getName());
    assertEquals("JANE DOE", result.get(1).getName());
  }

  @Test
  public void test_findByStatus_successful() {
    PgSqlEmployeeEntity entity = PgSqlEmployeeMapper.toEntity(bo);
    jpa.save(entity);

    List<EmployeeBO> result = sut.findByStatus(EmployeeStatus.ACTIVE);

    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals("JOHN DOE", result.get(0).getName());
  }

  @Test
  public void test_findByStatus_activeEmployees() {
    PgSqlEmployeeEntity activeEmployee = makeActiveEmployeeEntity();
    PgSqlEmployeeEntity inactiveEmployee = makeInactiveEmployeeEntity();

    jpa.save(activeEmployee);
    jpa.save(inactiveEmployee);

    List<EmployeeBO> activeEmployees = sut.findByStatus(EmployeeStatus.ACTIVE);

    assertEquals(1, activeEmployees.size());
    assertEquals("OPHELIA BECK", activeEmployees.get(0).getName());
  }

  @Test
  public void test_findByStatus_noActiveEmployees() {
    PgSqlEmployeeEntity inactiveEmployee = makeInactiveEmployeeEntity();
    jpa.save(inactiveEmployee);

    List<EmployeeBO> activeEmployees = sut.findByStatus(EmployeeStatus.ACTIVE);

    assertTrue(activeEmployees.isEmpty());
  }

  @Test
  public void test_findByCpf_successful() {
    PgSqlEmployeeEntity entity = PgSqlEmployeeMapper.toEntity(bo);
    jpa.save(entity);

    Optional<EmployeeBO> result = sut.findByCpf("12345678910");

    assertTrue(result.isPresent());
    assertEquals("12345678910", result.get().getCpf());
  }

  @Test
  public void test_findByCpf_notFound() {
    Optional<EmployeeBO> result = sut.findByCpf("98765432100");

    assertFalse(result.isPresent());
  }

  @Test
  public void test_updateById_successful() {
    PgSqlEmployeeEntity entity = PgSqlEmployeeMapper.toEntity(bo);
    jpa.save(entity);

    EmployeeBO updatedBO = new EmployeeBO(1L, "Updated Name", "12345678910", EmployeeStatus.ACTIVE, LocalDate.now(),
        LocalDate.now());

    EmployeeBO result = sut.updateById(1L, updatedBO);

    assertNotNull(result);
    assertEquals("UPDATED NAME", result.getName());
  }
}
