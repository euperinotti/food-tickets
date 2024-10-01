package br.com.euperinotti.foodtickets.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.euperinotti.foodtickets.domain.dtos.request.EmployeeRequestDTO;
import br.com.euperinotti.foodtickets.domain.dtos.response.EmployeeResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import br.com.euperinotti.foodtickets.domain.exceptions.AppExceptions;
import br.com.euperinotti.foodtickets.domain.mappers.EmployeeMapper;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;

class EmployeeServiceTest {

  @Mock
  private IEmployeeRepository repository;

  @InjectMocks
  private EmployeeService service;

  private List<EmployeeResponseDTO> mockEmployeesResponseDTO;
  private List<EmployeeBO> mockEmployeesBO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockEmployeesResponseDTO = List.of(
        new EmployeeResponseDTO(1L, "John", "33046243922", EmployeeStatus.ACTIVE, LocalDate.now(), LocalDate.now()),
        new EmployeeResponseDTO(2L, "Jane", "51135388281", EmployeeStatus.ACTIVE, LocalDate.now(), LocalDate.now()));

    mockEmployeesBO = List.of(
        new EmployeeBO(1L, "John", "33046243922", EmployeeStatus.ACTIVE, LocalDate.now(), LocalDate.now()),
        new EmployeeBO(2L, "Jane", "51135388281", EmployeeStatus.INACTIVE, LocalDate.now(), LocalDate.now()));
  }

  @Test
  void test_getAll_shouldReturnListWithAllEmployees() {
    when(repository.findAll()).thenReturn(mockEmployeesBO);

    List<EmployeeResponseDTO> result = service.getAll();

    assertNotNull(result);
    assertEquals(2, result.size());
    verify(repository, times(1)).findAll();
  }

  @Test
  void test_getAll_shouldReturnEmptyList() {
    when(repository.findAll()).thenReturn(List.of());

    List<EmployeeResponseDTO> result = service.getAll();

    assertEquals(0, result.size());
    verify(repository, times(1)).findAll();
  }

  @Test
  void test_getById_shouldReturnEmployeeById() {
    when(repository.findById(1L)).thenReturn(Optional.of(mockEmployeesBO.get(0)));

    EmployeeResponseDTO result = service.getById(1L);

    assertNotNull(result);
    assertEquals(1L, result.getId());
    assertEquals("JOHN", result.getName());
    verify(repository, times(1)).findById(1L);
  }

  @Test
  void test_getById_shouldThrowIfEmployeeNotFound() {
    when(repository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(AppExceptions.class, () -> {
      service.getById(1L);
    });
  }

  @Test
  void test_getByStatus_shouldReturnAllActiveEmployeesIfExisted() {
    when(repository.findByStatus(EmployeeStatus.ACTIVE)).thenReturn(List.of(mockEmployeesBO.get(0)));

    List<EmployeeResponseDTO> result = service.getByStatus(EmployeeStatus.ACTIVE.getName());

    assertNotNull(result);
    assertEquals(1, result.size());
    verify(repository, times(1)).findByStatus(EmployeeStatus.ACTIVE);
  }

  @Test
  void test_getByStatus_shouldReturnAllInactiveEmployeesIfExisted() {
    when(repository.findByStatus(EmployeeStatus.INACTIVE)).thenReturn(List.of(mockEmployeesBO.get(1)));

    List<EmployeeResponseDTO> result = service.getByStatus(EmployeeStatus.INACTIVE.getName());

    assertNotNull(result);
    assertEquals(1, result.size());
    verify(repository, times(1)).findByStatus(EmployeeStatus.INACTIVE);
  }

  @Test
  void test_getByStatus_shouldReturnEmptyListIfNoEmployeesMatch() {
    when(repository.findByStatus(EmployeeStatus.INACTIVE)).thenReturn(List.of());

    List<EmployeeResponseDTO> result = service.getByStatus(EmployeeStatus.INACTIVE.getName());

    assertEquals(0, result.size());
    verify(repository, times(1)).findByStatus(EmployeeStatus.INACTIVE);
  }

  @Test
  void test_create_shouldCreateNewEmployee() {
    EmployeeRequestDTO request = new EmployeeRequestDTO(1L, "John", "33046243922");
    when(repository.save(any(EmployeeBO.class))).thenReturn(EmployeeMapper.toBO(request));

    EmployeeResponseDTO result = service.create(request);

    assertNotNull(result);
    assertEquals("JOHN", result.getName());
    verify(repository, times(1)).save(any(EmployeeBO.class));
  }

  @Test
  void test_create_shouldCreateNewEmployeeWithActiveStatus() {
    EmployeeRequestDTO request = new EmployeeRequestDTO(1L, "John", "33046243922");
    request.setStatus(EmployeeStatus.INACTIVE);
    when(repository.save(any(EmployeeBO.class))).thenReturn(EmployeeMapper.toBO(request));

    EmployeeResponseDTO result = service.create(request);

    assertNotNull(result);
    assertEquals(EmployeeStatus.INACTIVE, result.getStatus());
    verify(repository, times(1)).save(any(EmployeeBO.class));
  }

  @Test
  void test_updateById_shouldUpdateEmployeeAttributes() {
    EmployeeRequestDTO request = new EmployeeRequestDTO(1L, "John", "33046243922");
    request.setStatus(EmployeeStatus.INACTIVE);

    EmployeeBO bo = EmployeeMapper.toBO(request);

    when(repository.findById(anyLong())).thenReturn(Optional.of(bo));
    when(repository.updateById(anyLong(), any(EmployeeBO.class))).thenReturn(bo);

    EmployeeResponseDTO result = service.update(1L, request);

    assertNotNull(result);
    assertEquals("JOHN", result.getName());
    assertEquals("JOHN", result.getName());
    verify(repository, times(1)).updateById(anyLong(), any(EmployeeBO.class));
  }
}
