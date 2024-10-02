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

import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.exceptions.AppExceptions;
import br.com.euperinotti.foodtickets.domain.exceptions.enums.EmployeeExceptions;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;

class FindByCpfTest {

  @Mock
  private IEmployeeRepository repository;

  @InjectMocks
  private FindByCpf findByCpf;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void test_execute_shouldReturnEmployeeWhenCpfExists() {
    String cpf = "12345678900";
    EmployeeBO employee = new EmployeeBO(1L, "John", cpf);

    when(repository.findByCpf(cpf)).thenReturn(Optional.of(employee));

    EmployeeBO result = findByCpf.execute(cpf);

    assertNotNull(result);
    assertEquals(cpf, result.getCpf());
    verify(repository, times(1)).findByCpf(cpf);
  }

  @Test
  void test_execute_shouldThrowExceptionWhenCpfNotFound() {
    String cpf = "12345678900";

    when(repository.findByCpf(cpf)).thenReturn(Optional.empty());

    AppExceptions exception = assertThrows(AppExceptions.class, () -> findByCpf.execute(cpf));
    assertEquals(EmployeeExceptions.EMPLOYEE_NOT_FOUND.getMessage(), exception.getMessage());
    verify(repository, times(1)).findByCpf(cpf);
  }
}
