package br.com.euperinotti.foodtickets.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.euperinotti.foodtickets.application.dtos.request.EmployeeRequestDTO;
import br.com.euperinotti.foodtickets.application.dtos.response.EmployeeResponseDTO;
import br.com.euperinotti.foodtickets.application.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

  private final EmployeeService employeeService;

  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping
  public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
    List<EmployeeResponseDTO> employees = employeeService.getAll();
    return ResponseEntity.ok(employees);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
    EmployeeResponseDTO employee = employeeService.getById(id);

    return ResponseEntity.ok(employee);
  }

  @GetMapping("/status/{status}")
  public ResponseEntity<List<EmployeeResponseDTO>> getEmployeesByStatus(@PathVariable String status) {
    List<EmployeeResponseDTO> employees = employeeService.getByStatus(status);
    return ResponseEntity.ok(employees);
  }

  @PostMapping
  public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody EmployeeRequestDTO dto) {
    EmployeeResponseDTO createdEmployee = employeeService.create(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
  }

  @PutMapping("/{id}")
  public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable Long id,
      @RequestBody EmployeeRequestDTO dto) {
    EmployeeResponseDTO updatedEmployee = employeeService.update(id, dto);
    return ResponseEntity.ok(updatedEmployee);
  }
}
