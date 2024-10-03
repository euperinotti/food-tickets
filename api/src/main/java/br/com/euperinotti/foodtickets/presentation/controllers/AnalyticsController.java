package br.com.euperinotti.foodtickets.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.euperinotti.foodtickets.application.dtos.response.AnalyticsResponseDTO;
import br.com.euperinotti.foodtickets.application.services.AnalyticsService;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

  private final AnalyticsService service;

  @Autowired
  public AnalyticsController(AnalyticsService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<AnalyticsResponseDTO> getAnalytics() {
    AnalyticsResponseDTO dto = service.getData();
    return ResponseEntity.ok(dto);
  }

}
