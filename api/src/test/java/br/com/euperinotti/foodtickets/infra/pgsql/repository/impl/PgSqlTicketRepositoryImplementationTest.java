package br.com.euperinotti.foodtickets.infra.pgsql.repository.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import br.com.euperinotti.foodtickets.domain.entities.TicketBO;
import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;
import br.com.euperinotti.foodtickets.infra.pgsql.entities.PgSqlEmployeeEntity;
import br.com.euperinotti.foodtickets.infra.pgsql.repository.contracts.PgSqlEmployeeRepository;
import br.com.euperinotti.foodtickets.infra.pgsql.repository.contracts.PgSqlTicketRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Rollback
class PgSqlTicketRepositoryImplementationTest {

  private PgSqlTicketRepositoryImplementation sut;

  @Autowired
  private PgSqlTicketRepository repository;

  @Autowired
  private PgSqlEmployeeRepository employeeRepository;

  @PersistenceContext
  private EntityManager entityManager;

  private PgSqlEmployeeEntity employeeEntity;

  private TicketBO bo;

  @BeforeEach
  void setUp() {
    employeeEntity = new PgSqlEmployeeEntity();
    employeeEntity.setName("Ophelia Beck");
    employeeEntity.setCpf("72197234469");
    employeeEntity.setStatus(EmployeeStatus.ACTIVE);
    employeeEntity.setCreatedAt(LocalDateTime.now());
    employeeEntity.setUpdatedAt(LocalDateTime.now());

    entityManager.persist(employeeEntity);
    entityManager.flush();

    sut = new PgSqlTicketRepositoryImplementation(repository, employeeRepository);
    bo = new TicketBO(null, employeeEntity.getId(), 5, TicketStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now());
  }

  @Test
  void test_save_shouldReturnSavedTicketBO() {
    TicketBO result = sut.save(bo);

    assertNotNull(result);
    assertNotNull(result.getId());
    assertEquals(5, result.getQuantity());
    assertEquals(employeeEntity.getId(), result.getEmployeeId());
  }

  @Test
  void test_findById_shouldReturnTicketBO() {
    TicketBO savedTicket = sut.save(bo);

    TicketBO result = sut.findById(savedTicket.getId()).orElse(null);

    assertNotNull(result);
    assertEquals(savedTicket.getId(), result.getId());
  }

  @Test
  void test_findById_shouldReturnEmptyWhenNotFound() {
    Optional<TicketBO> result = sut.findById(999L);

    assertFalse(result.isPresent());
  }

  @Test
  void test_deleteById_shouldDeleteTicket() {
    TicketBO savedTicket = sut.save(bo);
    repository.deleteById(savedTicket.getId());

    Optional<TicketBO> result = sut.findById(999L);

    assertFalse(result.isPresent());
  }

  @Test
  void test_findAll_shouldReturnListOfTicketBO() {
    sut.save(bo);
    sut.save(
        new TicketBO(null, employeeEntity.getId(), 20, TicketStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now()));

    List<TicketBO> result = sut.findAll();

    assertTrue(result.size() > 0);
    assertEquals(2, result.size());
  }

  @Test
  void test_findAll_shouldReturnEmptyListWhenNoTicketsFound() {
    List<TicketBO> result = sut.findAll();

    assertEquals(0, result.size());
  }

  @Test
  void test_updateById_shouldReturnUpdatedTicketBO() {
    TicketBO savedTicket = sut.save(bo);
    TicketBO newTicket = new TicketBO(null, employeeEntity.getId(), 10, TicketStatus.ACTIVE, LocalDateTime.now(),
        LocalDateTime.now());

    TicketBO updatedTicket = sut.updateById(savedTicket.getId(), newTicket);

    assertThat(updatedTicket.getQuantity()).isEqualTo(10);
  }
}
