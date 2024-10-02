package br.com.euperinotti.foodtickets.infra.pgsql.repository.contracts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import br.com.euperinotti.foodtickets.infra.pgsql.entities.PgSqlEmployeeEntity;

@DataJpaTest
public class PgSqlEmployeeRepositoryTest {

  @Autowired
  private PgSqlEmployeeRepository repository;

  private PgSqlEmployeeEntity makeInactiveEmployeeEntity() {
    PgSqlEmployeeEntity inactiveEmployee = new PgSqlEmployeeEntity();
    inactiveEmployee.setId(1L);
    inactiveEmployee.setName("Kyle Schmidt");
    inactiveEmployee.setCpf("66418505745");
    inactiveEmployee.setStatus(EmployeeStatus.INACTIVE);
    inactiveEmployee.setCreatedAt(LocalDateTime.now());
    inactiveEmployee.setUpdatedAt(LocalDateTime.now());

    return inactiveEmployee;
  }

  private PgSqlEmployeeEntity makeActiveEmployeeEntity() {
    PgSqlEmployeeEntity inactiveEmployee = new PgSqlEmployeeEntity();
    inactiveEmployee.setName("Ophelia Beck");
    inactiveEmployee.setCpf("72197234469");
    inactiveEmployee.setStatus(EmployeeStatus.ACTIVE);
    inactiveEmployee.setCreatedAt(LocalDateTime.now());
    inactiveEmployee.setUpdatedAt(LocalDateTime.now());

    return inactiveEmployee;
  }

  @Test
  public void test_findByCpf_existingCpf_shouldReturnEmployee() {
    PgSqlEmployeeEntity employee = repository.save(makeActiveEmployeeEntity());

    Optional<PgSqlEmployeeEntity> result = repository.findByCpf("72197234469");

    assertThat(result).isPresent();
    assertEquals(employee, result.get());
  }

  @Test
  public void test_findByCpf_nonExistingCpf_shouldReturnEmpty() {
    Optional<PgSqlEmployeeEntity> result = repository.findByCpf("99999999999");

    assertThat(result).isNotPresent();
  }

  @Test
  public void test_findByStatus_activeStatus_shouldReturnEmployees() {
    PgSqlEmployeeEntity employee = makeActiveEmployeeEntity();
    PgSqlEmployeeEntity employee2 = new PgSqlEmployeeEntity();
    employee2.setId(2L);
    employee2.setName("Jane Doe");
    employee2.setCpf("09876543210");
    employee2.setStatus(EmployeeStatus.ACTIVE);
    employee2.setCreatedAt(LocalDateTime.now());
    employee2.setUpdatedAt(LocalDateTime.now());

    repository.save(employee);
    repository.save(employee2);

    List<PgSqlEmployeeEntity> result = repository.findByStatus(EmployeeStatus.ACTIVE);

    assertEquals(2, result.size());
  }

  @Test
  public void test_findByStatus_activeStatus_shouldReturnEmptyList() {
    PgSqlEmployeeEntity employee = makeActiveEmployeeEntity();
    repository.save(employee);

    List<PgSqlEmployeeEntity> result = repository.findByStatus(EmployeeStatus.INACTIVE);

    assertThat(result).isEmpty();
  }

  @Test
  public void test_findByStatus_inactiveStatus_shouldReturnEmployees() {
    PgSqlEmployeeEntity employee = makeInactiveEmployeeEntity();
    PgSqlEmployeeEntity employee2 = new PgSqlEmployeeEntity();
    employee2.setId(2L);
    employee2.setName("Jane Doe");
    employee2.setCpf("09876543210");
    employee2.setStatus(EmployeeStatus.INACTIVE);
    employee2.setCreatedAt(LocalDateTime.now());
    employee2.setUpdatedAt(LocalDateTime.now());

    repository.save(employee);
    repository.save(employee2);

    List<PgSqlEmployeeEntity> result = repository.findByStatus(EmployeeStatus.INACTIVE);

    assertEquals(2, result.size());
  }

  @Test
  public void test_findByStatus_inactiveStatus_shouldReturnEmptyList() {
    PgSqlEmployeeEntity employee = makeInactiveEmployeeEntity();
    repository.save(employee);

    List<PgSqlEmployeeEntity> result = repository.findByStatus(EmployeeStatus.ACTIVE);

    assertThat(result).isEmpty();
  }
}
