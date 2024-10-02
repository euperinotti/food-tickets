package br.com.euperinotti.foodtickets.domain.usecases.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.euperinotti.foodtickets.application.dtos.response.EmployeeResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.exceptions.AppExceptions;
import br.com.euperinotti.foodtickets.domain.exceptions.enums.EmployeeExceptions;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;

class FindByIdTest {

  @Mock
  private IEmployeeRepository repository;

  @InjectMocks
  private FindById findById;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void test_execute_shouldReturnEmployeeWhenIdExists() {
    Long id = 1L;
    EmployeeBO employeeBO = new EmployeeBO(id, "John", "12345678900");

    when(repository.findById(id)).thenReturn(Optional.of(employeeBO));

    EmployeeResponseDTO result = findById.execute(id);

    assertNotNull(result);
    assertEquals(id, result.getId());
    verify(repository, times(1)).findById(id);
  }

  @Test
  void test_execute_shouldThrowExceptionWhenIdNotFound() {
    Long id = 1L;

    when(repository.findById(id)).thenReturn(Optional.empty());

    AppExceptions exception = assertThrows(AppExceptions.class, () -> findById.execute(id));
    assertEquals(EmployeeExceptions.EMPLOYEE_NOT_FOUND.getMessage(), exception.getMessage());
    verify(repository, times(1)).findById(id);
  }
}
