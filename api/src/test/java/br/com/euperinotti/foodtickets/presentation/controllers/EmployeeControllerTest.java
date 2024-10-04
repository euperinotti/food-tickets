package br.com.euperinotti.foodtickets.presentation.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.euperinotti.foodtickets.application.dtos.request.EmployeeRequestDTO;
import br.com.euperinotti.foodtickets.application.dtos.response.EmployeeResponseDTO;
import br.com.euperinotti.foodtickets.application.services.EmployeeService;
import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;

class EmployeeControllerTest {

  private MockMvc mockMvc;

  @Mock
  private EmployeeService employeeService;

  @InjectMocks
  private EmployeeController employeeController;

  private List<EmployeeResponseDTO> employees;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    employees = List.of(
        new EmployeeResponseDTO(1L, "John Doe", "11111111111", EmployeeStatus.ACTIVE),
        new EmployeeResponseDTO(2L, "Jane Smith", "22222222222", EmployeeStatus.ACTIVE));
  }

  @Test
  void test_getAllEmployees_shouldReturnListOfEmployees() throws Exception {
    when(employeeService.getAll()).thenReturn(employees);

    mockMvc.perform(get("/employees")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(1L))
        .andExpect(jsonPath("$[0].name").value("John Doe"))
        .andExpect(jsonPath("$[1].id").value(2L))
        .andExpect(jsonPath("$[1].name").value("Jane Smith"));

    verify(employeeService, times(1)).getAll();
  }

  @Test
  void test_getEmployeeById_shouldReturnEmployee() throws Exception {
    EmployeeResponseDTO employee = new EmployeeResponseDTO(1L, "John Doe", "11111111111", EmployeeStatus.ACTIVE);
    when(employeeService.getById(1L)).thenReturn(employee);

    mockMvc.perform(get("/employees/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.name").value("John Doe"));

    verify(employeeService, times(1)).getById(1L);
  }

  @Test
  void test_getEmployeesByStatus_shouldReturnEmployeesByStatus() throws Exception {
    List<EmployeeResponseDTO> employees = List.of(
        new EmployeeResponseDTO(1L, "John Doe", "11111111111", EmployeeStatus.ACTIVE));
    when(employeeService.getByStatus(EmployeeStatus.ACTIVE.getName())).thenReturn(employees);

    mockMvc.perform(get("/employees/status/ACTIVE")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    verify(employeeService, times(1)).getByStatus(EmployeeStatus.ACTIVE.toString());
  }

  @Test
  @Disabled
  void test_createEmployee_shouldReturnCreatedEmployee() throws Exception {
    EmployeeRequestDTO requestDTO = new EmployeeRequestDTO(null, "John Doe", "11111111111", EmployeeStatus.ACTIVE);
    EmployeeResponseDTO createdEmployee = new EmployeeResponseDTO(1L, "John Doe", "11111111111", EmployeeStatus.ACTIVE);
    when(employeeService.create(any(EmployeeRequestDTO.class))).thenReturn(createdEmployee);

    mockMvc.perform(post("/employees")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(requestDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.name").value("John Doe"))
        .andExpect(jsonPath("$.status").value(EmployeeStatus.ACTIVE));

    verify(employeeService, times(1)).create(any(EmployeeRequestDTO.class));
  }

  @Test
  @Disabled
  void test_updateEmployee_shouldReturnUpdatedEmployee() throws Exception {
    EmployeeRequestDTO requestDTO = new EmployeeRequestDTO(null, "John Doe Updated", "11111111111",
        EmployeeStatus.INACTIVE);
    EmployeeResponseDTO updatedEmployee = new EmployeeResponseDTO(1L, "John Doe Updated", "11111111111",
        EmployeeStatus.INACTIVE);
    when(employeeService.update(eq(1L), any(EmployeeRequestDTO.class))).thenReturn(updatedEmployee);

    mockMvc.perform(put("/employees/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(requestDTO)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.name").value("John Doe Updated"))
        .andExpect(jsonPath("$.status").value(EmployeeStatus.INACTIVE));

    verify(employeeService, times(1)).update(eq(1L), any(EmployeeRequestDTO.class));
  }
}
