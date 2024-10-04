package br.com.euperinotti.foodtickets.domain.usecases.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.euperinotti.foodtickets.application.dtos.request.EmployeeRequestDTO;
import br.com.euperinotti.foodtickets.application.dtos.response.EmployeeResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.exceptions.AppExceptions;
import br.com.euperinotti.foodtickets.domain.exceptions.enums.EmployeeExceptions;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;

class UpdateByIdTest {

  @Mock
  private IEmployeeRepository repository;

  @InjectMocks
  private UpdateById sut;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void test_execute_shouldUpdateEmployee() {
    Long id = 1L;
    EmployeeRequestDTO dto = new EmployeeRequestDTO();
    dto.setName("Updated Name");
    dto.setCpf("10033067996");

    EmployeeBO existingEmployee = new EmployeeBO(id, "John", "10033067996");
    EmployeeBO updatedEmployee = new EmployeeBO(id, "Updated Name", "10033067996");

    when(repository.findById(id)).thenReturn(Optional.of(existingEmployee));
    when(repository.updateById(eq(id), any(EmployeeBO.class))).thenReturn(updatedEmployee);

    EmployeeResponseDTO result = sut.execute(id, dto);

    assertNotNull(result);
    assertEquals("UPDATED NAME", result.getName());
    verify(repository, times(1)).findById(id);
    verify(repository, times(1)).updateById(eq(id), any(EmployeeBO.class));
  }

  @Test
  void test_execute_shouldThrowExceptionWhenEmployeeNotFound() {
    Long id = 1L;
    EmployeeRequestDTO dto = new EmployeeRequestDTO(null, "Ivan Moss", "10033067996");

    when(repository.findById(id)).thenReturn(Optional.empty());

    assertThrows(AppExceptions.class, () -> sut.execute(id, dto));

    verify(repository, times(1)).findById(id);
    verify(repository, never()).updateById(anyLong(), any(EmployeeBO.class));
  }

  @Test
  void test_validate_shouldSetEmployeeIdAndUpdatedAt() {
    Long id = 1L;
    EmployeeRequestDTO dto = new EmployeeRequestDTO();
    dto.setName("John");
    dto.setCpf("10033067996");

    EmployeeBO existingEmployee = new EmployeeBO(id, "John", "10033067996");

    when(repository.findById(id)).thenReturn(Optional.of(existingEmployee));

    sut.validate(id, dto);

    assertEquals(id, dto.getId());
    assertNotNull(dto.getUpdatedAt());
    verify(repository, times(1)).findById(id);
  }

  @Test
  void test_validate_shouldThrowExceptionWhenEmployeeNotFound() {
    Long id = 1L;
    EmployeeRequestDTO dto = new EmployeeRequestDTO(null, "Ivan Moss", "10033067996");

    when(repository.findById(id)).thenReturn(Optional.empty());

    AppExceptions exception = assertThrows(AppExceptions.class, () -> sut.execute(id, dto));
    assertEquals(EmployeeExceptions.EMPLOYEE_NOT_FOUND.getMessage(), exception.getMessage());
  }

  @Test
  void test_validate_shouldThrowExceptionIfCpfIsInvalid() {
    Long id = 1L;
    EmployeeRequestDTO dto = new EmployeeRequestDTO(null, "Ivan Moss", "35882043390");

    assertThrows(AppExceptions.class, () -> sut.validate(id, dto));
  }
}
