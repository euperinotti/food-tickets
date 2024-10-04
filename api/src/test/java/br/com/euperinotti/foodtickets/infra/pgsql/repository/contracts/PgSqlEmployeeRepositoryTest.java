package br.com.euperinotti.foodtickets.infra.pgsql.repository.contracts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;
import br.com.euperinotti.foodtickets.infra.pgsql.entities.PgSqlEmployeeEntity;
import br.com.euperinotti.foodtickets.infra.pgsql.entities.PgSqlTicketEntity;

@DataJpaTest
public class PgSqlEmployeeRepositoryTest {

  @Autowired
  private PgSqlEmployeeRepository sut;

  @Autowired
  private PgSqlTicketRepository ticketRepository;

  List<PgSqlEmployeeEntity> employees;

  @BeforeEach
  void setup() {
    employees = List.of(makeActiveEmployeeEntity(), makeInactiveEmployeeEntity());
  }

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
    PgSqlEmployeeEntity employee = sut.save(makeActiveEmployeeEntity());

    Optional<PgSqlEmployeeEntity> result = sut.findByCpf("72197234469");

    assertThat(result).isPresent();
    assertEquals(employee, result.get());
  }

  @Test
  public void test_findByCpf_nonExistingCpf_shouldReturnEmpty() {
    Optional<PgSqlEmployeeEntity> result = sut.findByCpf("99999999999");

    assertThat(result).isNotPresent();
  }

  @Test
  public void test_findByStatus_activeStatus_shouldReturnEmployees() {
    PgSqlEmployeeEntity employee = makeActiveEmployeeEntity();
    PgSqlEmployeeEntity employee2 = makeActiveEmployeeEntity();
    employee2.setId(2L);
    employee2.setName("Jane Doe");
    employee2.setCpf("09876543210");

    sut.save(employee);
    sut.save(employee2);

    List<PgSqlEmployeeEntity> result = sut.findByStatus(EmployeeStatus.ACTIVE);

    assertEquals(2, result.size());
  }

  @Test
  public void test_findByStatus_activeStatus_shouldReturnEmptyList() {
    PgSqlEmployeeEntity employee = makeActiveEmployeeEntity();
    sut.save(employee);

    List<PgSqlEmployeeEntity> result = sut.findByStatus(EmployeeStatus.INACTIVE);

    assertThat(result).isEmpty();
  }

  @Test
  public void test_findByStatus_inactiveStatus_shouldReturnEmployees() {
    PgSqlEmployeeEntity employee = makeInactiveEmployeeEntity();
    PgSqlEmployeeEntity employee2 = makeInactiveEmployeeEntity();
    employee2.setId(2L);
    employee2.setName("Jane Doe");
    employee2.setCpf("09876543210");

    sut.save(employee);
    sut.save(employee2);

    List<PgSqlEmployeeEntity> result = sut.findByStatus(EmployeeStatus.INACTIVE);

    assertEquals(2, result.size());
  }

  @Test
  public void test_findByStatus_inactiveStatus_shouldReturnEmptyList() {
    PgSqlEmployeeEntity employee = makeInactiveEmployeeEntity();
    sut.save(employee);

    List<PgSqlEmployeeEntity> result = sut.findByStatus(EmployeeStatus.ACTIVE);

    assertThat(result).isEmpty();
  }

  @Test
  void test_countByStatus_shouldReturnCountForExistingStatus() {
    PgSqlEmployeeEntity employee = makeActiveEmployeeEntity();
    sut.save(employee);

    Integer activeCount = sut.countByStatus(EmployeeStatus.ACTIVE);
    Integer inactiveCount = sut.countByStatus(EmployeeStatus.INACTIVE);

    assertEquals(1, activeCount);
    assertEquals(0, inactiveCount);
  }

  @Test
  void test_countByStatus_shouldReturnZeroForNonExistingStatus() {
    Integer count = sut.countByStatus(EmployeeStatus.ACTIVE);

    assertEquals(0, count);
  }

  @Test
  void test_findEmployeeWithMostTickets_shouldReturnEmployeeWithMostTickets() {
    PgSqlTicketEntity ticket1 = new PgSqlTicketEntity();
    ticket1.setEmployee(sut.save(employees.get(0)));
    ticket1.setQuantity(5);
    ticket1.setStatus(TicketStatus.ACTIVE);
    ticket1.setCreatedAt(LocalDateTime.now());
    ticket1.setUpdatedAt(LocalDateTime.now());

    PgSqlTicketEntity ticket2 = new PgSqlTicketEntity();
    ticket2.setEmployee(sut.save(employees.get(1)));
    ticket2.setQuantity(3);
    ticket2.setStatus(TicketStatus.ACTIVE);
    ticket2.setCreatedAt(LocalDateTime.now());
    ticket2.setUpdatedAt(LocalDateTime.now());

    ticketRepository.save(ticket1);
    ticketRepository.save(ticket2);

    PgSqlEmployeeEntity result = sut.findEmployeeWithMostTickets();

    assertNotNull(result);
    assertEquals(employees.get(0).getCpf(), result.getCpf());
  }

  @Test
  void test_findEmployeeWithMostTickets_shouldReturnNullWhenNoTicketsExist() {
    PgSqlEmployeeEntity result = sut.findEmployeeWithMostTickets();

    assertNull(result);
  }
}
