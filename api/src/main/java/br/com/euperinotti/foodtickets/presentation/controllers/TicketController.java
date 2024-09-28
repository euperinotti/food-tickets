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

import br.com.euperinotti.foodtickets.domain.dtos.request.TicketRequestDTO;
import br.com.euperinotti.foodtickets.domain.dtos.response.TicketResponseDTO;
import br.com.euperinotti.foodtickets.services.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {

  private final TicketService employeeService;

  @Autowired
  public TicketController(TicketService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping
  public ResponseEntity<List<TicketResponseDTO>> getAllTickets() {
    List<TicketResponseDTO> employees = employeeService.getAll();
    return ResponseEntity.ok(employees);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TicketResponseDTO> getTicketById(@PathVariable Long id) {
    TicketResponseDTO employee = employeeService.getById(id);

    return ResponseEntity.ok(employee);
  }

  @GetMapping("/status/{status}")
  public ResponseEntity<List<TicketResponseDTO>> getTicketsByStatus(@PathVariable String status) {
    List<TicketResponseDTO> employees = employeeService.getByStatus(status);
    return ResponseEntity.ok(employees);
  }

  @PostMapping
  public ResponseEntity<TicketResponseDTO> createTicket(@RequestBody TicketRequestDTO dto) {
    TicketResponseDTO createdTicket = employeeService.create(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdTicket);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TicketResponseDTO> updateTicket(@PathVariable Long id,
      @RequestBody TicketRequestDTO dto) {
    TicketResponseDTO updatedTicket = employeeService.update(id, dto);
    return ResponseEntity.ok(updatedTicket);
  }
}
