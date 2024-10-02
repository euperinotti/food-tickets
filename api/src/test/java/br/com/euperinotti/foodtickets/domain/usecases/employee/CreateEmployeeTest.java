package br.com.euperinotti.foodtickets.domain.usecases.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.euperinotti.foodtickets.application.dtos.request.EmployeeRequestDTO;
import br.com.euperinotti.foodtickets.application.dtos.response.EmployeeResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import br.com.euperinotti.foodtickets.domain.exceptions.AppExceptions;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;

class CreateEmployeeTest {

  @Mock
  private IEmployeeRepository repository;

  @InjectMocks
  private CreateEmployee createEmployee;

  private EmployeeBO bo;

  private EmployeeRequestDTO requestDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    bo = new EmployeeBO(1L, "John Doe", "12345678900", EmployeeStatus.ACTIVE, LocalDateTime.now(),
        LocalDateTime.now());
    requestDTO = new EmployeeRequestDTO();
    requestDTO.setId(bo.getId());
    requestDTO.setName(bo.getName());
    requestDTO.setCpf(bo.getCpf());
    requestDTO.setStatus(bo.getStatus());
  }

  @Test
  void test_execute_shouldCreateEmployee() {
    when(repository.save(any(EmployeeBO.class))).thenReturn(bo);
    when(repository.findByCpf(anyString())).thenReturn(Optional.empty());

    EmployeeResponseDTO result = createEmployee.execute(requestDTO);

    assertNotNull(result);
    verify(repository, times(1)).save(any(EmployeeBO.class));
    assertEquals(EmployeeStatus.ACTIVE, requestDTO.getStatus());
  }

  @Test
  void test_execute_shouldThrowExceptionWhenCpfAlreadyExists() {
    when(repository.findByCpf(anyString())).thenReturn(Optional.of(bo));

    assertThrows(AppExceptions.class, () -> createEmployee.execute(requestDTO));
    verify(repository, never()).save(any(EmployeeBO.class));
  }

  @Test
  void test_validate_shouldSetActiveStatusForNewEmployee() {
    when(repository.findByCpf(anyString())).thenReturn(Optional.empty());

    createEmployee.validate(requestDTO);

    assertEquals(EmployeeStatus.ACTIVE, requestDTO.getStatus());
  }

  @Test
  void test_validate_shouldThrowExceptionIfCpfExists() {
    when(repository.findByCpf(anyString())).thenReturn(Optional.of(bo));

    assertThrows(AppExceptions.class, () -> createEmployee.validate(requestDTO));
  }
}
