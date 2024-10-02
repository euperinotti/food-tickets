package br.com.euperinotti.foodtickets.domain.usecases.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.euperinotti.foodtickets.application.dtos.response.EmployeeResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;

class FindByStatusTest {

  @Mock
  private IEmployeeRepository repository;

  @InjectMocks
  private FindByStatus findByStatus;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void test_execute_shouldReturnEmployeesByStatus() {
    EmployeeStatus status = EmployeeStatus.ACTIVE;
    EmployeeBO employee1 = new EmployeeBO(1L, "John", "12345678900");
    EmployeeBO employee2 = new EmployeeBO(2L, "Jane", "98765432100");
    List<EmployeeBO> mockEmployees = List.of(employee1, employee2);

    when(repository.findByStatus(status)).thenReturn(mockEmployees);

    List<EmployeeResponseDTO> result = findByStatus.execute(status);

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals("JOHN", result.get(0).getName());
    assertEquals("JANE", result.get(1).getName());
    verify(repository, times(1)).findByStatus(status);
  }

  @Test
  void test_execute_shouldReturnEmptyListWhenNoEmployeesWithStatus() {
    EmployeeStatus status = EmployeeStatus.ACTIVE;

    when(repository.findByStatus(status)).thenReturn(Arrays.asList());

    List<EmployeeResponseDTO> result = findByStatus.execute(status);

    assertNotNull(result);
    assertTrue(result.isEmpty());
    verify(repository, times(1)).findByStatus(status);
  }
}
