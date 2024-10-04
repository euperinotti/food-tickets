package br.com.euperinotti.foodtickets.infra.pgsql.repository.contracts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;
import br.com.euperinotti.foodtickets.infra.pgsql.entities.PgSqlEmployeeEntity;
import br.com.euperinotti.foodtickets.infra.pgsql.entities.PgSqlTicketEntity;

@DataJpaTest
public class PgSqlTicketRepositoryTest {

  @Autowired
  private PgSqlTicketRepository sut;

  @Autowired
  private PgSqlEmployeeRepository employeeRepository;

  private PgSqlTicketEntity ticket1;
  private PgSqlTicketEntity ticket2;
  private List<PgSqlEmployeeEntity> employees;

  @BeforeEach
  void setUp() {
    List<PgSqlEmployeeEntity> employees = saveEmployees();
    ticket1 = new PgSqlTicketEntity();
    ticket1.setStatus(TicketStatus.ACTIVE);
    ticket1.setQuantity(5);
    ticket1.setCreatedAt(LocalDateTime.now());
    ticket1.setUpdatedAt(LocalDateTime.now());
    ticket1.setEmployee(employees.get(0));

    ticket2 = new PgSqlTicketEntity();
    ticket2.setStatus(TicketStatus.ACTIVE);
    ticket2.setQuantity(3);
    ticket2.setCreatedAt(LocalDateTime.now().minusDays(1));
    ticket2.setUpdatedAt(LocalDateTime.now().minusDays(1));
    ticket2.setEmployee(employees.get(1));

    sut.save(ticket1);
    sut.save(ticket2);
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

  private List<PgSqlEmployeeEntity> saveEmployees() {
    PgSqlEmployeeEntity employeeEntity = makeActiveEmployeeEntity();
    employeeRepository.save(employeeEntity);

    PgSqlEmployeeEntity employeeEntity2 = makeActiveEmployeeEntity();
    employeeEntity2.setName("Sally Mack");
    employeeEntity2.setCpf("72864267383");

    employeeRepository.save(employeeEntity2);


    employees = List.of(employeeEntity, employeeEntity2);
    return employees;
  }

  @Test
  void test_findByStatus_shouldReturnEmptyListWhenNoTickets() {
    List<PgSqlTicketEntity> inactiveTickets = sut.findByStatus(TicketStatus.INACTIVE);

    assertTrue(inactiveTickets.isEmpty());
  }

  @Test
  void test_countTickets_shouldReturnZeroWhenNoTickets() {
    sut.deleteAll();

    Integer totalCount = sut.countTickets();

    assertEquals(0, totalCount);
  }

  @Test
  void test_findDayWithMaxTickets_shouldReturnNullWhenNoTickets() {
    sut.deleteAll();

    LocalDateTime maxTicketsDay = sut.findDayWithMaxTickets();

    assertEquals(null, maxTicketsDay);
  }

  @Test
  void test_findAllTicketsFromPeriod_shouldReturnEmptyListWhenNoTicketsInPeriod() {
    LocalDateTime period = LocalDateTime.now().plusDays(1);

    List<PgSqlTicketEntity> ticketsFromPeriod = sut.findAllTicketsFromPeriod(period);

    assertTrue(ticketsFromPeriod.isEmpty());
  }

  @Test
  void test_findAllTicketsFromPeriod_shouldReturnAllTicketsInPeriod() {
    LocalDateTime period = LocalDateTime.now().minusDays(2);

    List<PgSqlTicketEntity> ticketsFromPeriod = sut.findAllTicketsFromPeriod(period);

    assertEquals(2, ticketsFromPeriod.size());
  }

  @Test
  void test_findByStatus_shouldReturnTicketsWithStatusActive() {
    List<PgSqlTicketEntity> activeTickets = sut.findByStatus(TicketStatus.ACTIVE);
    assertEquals(2, activeTickets.size());
  }

  @Test
  void test_findByStatus_shouldReturnTicketsWithMultipleStatuses() {
    PgSqlTicketEntity ticket3 = new PgSqlTicketEntity();
    ticket3.setStatus(TicketStatus.INACTIVE);
    ticket3.setQuantity(4);
    ticket3.setCreatedAt(LocalDateTime.now().minusDays(2));
    ticket3.setUpdatedAt(LocalDateTime.now().minusDays(2));
    ticket3.setEmployee(employees.get(0));
    sut.save(ticket3);

    List<PgSqlTicketEntity> inactiveTickets = sut.findByStatus(TicketStatus.INACTIVE);
    assertEquals(1, inactiveTickets.size());
  }

}
