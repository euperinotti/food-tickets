package br.com.euperinotti.foodtickets.domain.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import br.com.euperinotti.foodtickets.application.dtos.request.EmployeeRequestDTO;
import br.com.euperinotti.foodtickets.application.dtos.response.EmployeeResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;

public class EmployeeMapperTest {

  @Test
  public void test_toBO_successfulMapping() {
    EmployeeRequestDTO dto = new EmployeeRequestDTO();
    dto.setId(1L);
    dto.setName("John Doe");
    dto.setCpf("123.456.789-10");
    dto.setStatus(EmployeeStatus.ACTIVE);
    dto.setCreatedAt(LocalDateTime.now());
    dto.setUpdatedAt(LocalDateTime.now());

    EmployeeBO bo = EmployeeMapper.toBO(dto);

    assertEquals(dto.getId(), bo.getId());
    assertEquals("JOHN DOE", bo.getName());
    assertEquals("12345678910", bo.getCpf());
    assertEquals(dto.getStatus(), bo.getStatus());
    assertEquals(dto.getCreatedAt(), bo.getCreatedAt());
    assertEquals(dto.getUpdatedAt(), bo.getUpdatedAt());
  }

  @Test
  public void test_toResponseDTO_successfulMapping() {
    EmployeeBO bo = new EmployeeBO(1L, "John Doe", "123.456.789-10", EmployeeStatus.ACTIVE, LocalDateTime.now(),
        LocalDateTime.now());

    EmployeeResponseDTO dto = EmployeeMapper.toResponseDTO(bo);

    assertEquals(bo.getId(), dto.getId());
    assertEquals("JOHN DOE", dto.getName());
    assertEquals("12345678910", dto.getCpf());
    assertEquals(bo.getStatus(), dto.getStatus());
    assertEquals(bo.getCreatedAt(), dto.getCreatedAt());
    assertEquals(bo.getUpdatedAt(), dto.getUpdatedAt());
  }
}
