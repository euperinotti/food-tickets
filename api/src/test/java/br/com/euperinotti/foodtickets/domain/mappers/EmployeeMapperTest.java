package br.com.euperinotti.foodtickets.domain.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.euperinotti.foodtickets.domain.dtos.request.EmployeeRequestDTO;
import br.com.euperinotti.foodtickets.domain.dtos.response.EmployeeResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;

public class EmployeeMapperTest {

  @Test
  public void test_toBO_successfulMapping() {
    // Arrange
    EmployeeRequestDTO dto = new EmployeeRequestDTO();
    dto.setId(1L);
    dto.setName("John Doe");
    dto.setCpf("123.456.789-10");
    dto.setStatus(EmployeeStatus.ACTIVE);
    dto.setCreatedAt(LocalDate.now());
    dto.setUpdatedAt(LocalDate.now());

    // Act
    EmployeeBO bo = EmployeeMapper.toBO(dto);

    // Assert
    assertEquals(dto.getId(), bo.getId());
    assertEquals(dto.getName(), bo.getName());
    assertEquals(dto.getCpf(), bo.getCpf());
    assertEquals(dto.getStatus(), bo.getStatus());
    assertEquals(dto.getCreatedAt(), bo.getCreatedAt());
    assertEquals(dto.getUpdatedAt(), bo.getUpdatedAt());
  }

  @Test
  public void test_toResponseDTO_successfulMapping() {
    // Arrange
    EmployeeBO bo = new EmployeeBO(1L, "John Doe", "123.456.789-10", EmployeeStatus.ACTIVE, LocalDate.now(),
        LocalDate.now());

    // Act
    EmployeeResponseDTO dto = EmployeeMapper.toResponseDTO(bo);

    // Assert
    assertEquals(bo.getId(), dto.getId());
    assertEquals(bo.getName(), dto.getName());
    assertEquals(bo.getCpf(), dto.getCpf());
    assertEquals(bo.getStatus(), dto.getStatus());
    assertEquals(bo.getCreatedAt(), dto.getCreatedAt());
    assertEquals(bo.getUpdatedAt(), dto.getUpdatedAt());
  }
}
