package br.com.euperinotti.foodtickets.domain.usecases.ticket;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import br.com.euperinotti.foodtickets.application.dtos.request.TicketRequestDTO;
import br.com.euperinotti.foodtickets.application.dtos.response.TicketResponseDTO;
import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.entities.TicketBO;
import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;
import br.com.euperinotti.foodtickets.domain.exceptions.AppExceptions;
import br.com.euperinotti.foodtickets.domain.exceptions.enums.EmployeeExceptions;
import br.com.euperinotti.foodtickets.domain.mappers.TicketMapper;
import br.com.euperinotti.foodtickets.domain.repository.IEmployeeRepository;
import br.com.euperinotti.foodtickets.domain.repository.ITicketRepository;

class CreateTicketTest {

    @Mock
    private ITicketRepository repository;

    @Mock
    private IEmployeeRepository employeeRepository;

    @InjectMocks
    private CreateTicket sut;

    private EmployeeBO employeeBO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeBO = new EmployeeBO(1L, "John Doe", "12345678900");
    }

    @Test
    void test_execute_shouldCreateTicket() {
        TicketRequestDTO dto = new TicketRequestDTO(null,  1L, 5, TicketStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now());

        when(employeeRepository.findById(dto.getEmployeeId())).thenReturn(Optional.of(employeeBO));
        when(repository.save(any(TicketBO.class))).thenReturn(TicketMapper.toBO(dto));

        TicketResponseDTO result = sut.execute(dto);

        assertNotNull(result);
        assertEquals(1L, result.getEmployeeId());
        assertEquals(TicketStatus.ACTIVE, result.getStatus());
        verify(employeeRepository, times(1)).findById(dto.getEmployeeId());
        verify(repository, times(1)).save(any(TicketBO.class));
    }

    @Test
    void test_execute_shouldThrowExceptionWhenEmployeeNotFound() {
        TicketRequestDTO dto = new TicketRequestDTO();
        dto.setEmployeeId(1L);

        when(employeeRepository.findById(dto.getEmployeeId())).thenReturn(Optional.empty());

        AppExceptions exception = assertThrows(AppExceptions.class, () -> sut.execute(dto));
        assertEquals(EmployeeExceptions.EMPLOYEE_NOT_FOUND.getMessage(), exception.getMessage());
        verify(employeeRepository, times(1)).findById(dto.getEmployeeId());
        verify(repository, never()).save(any(TicketBO.class));
    }

    @Test
    void test_validate_shouldSetEmployeeIdAndStatus() {
        TicketRequestDTO dto = new TicketRequestDTO();
        dto.setQuantity(10);
        dto.setEmployeeId(1L);

        EmployeeBO employee = new EmployeeBO(1L, "John Doe", "12345678900");

        sut.validate(dto, employee);

        assertEquals(employee.getId(), dto.getEmployeeId());
        assertEquals(TicketStatus.ACTIVE, dto.getStatus());
        assertNotNull(dto.getCreatedAt());
        assertNotNull(dto.getUpdatedAt());
    }

    @Test
    void test_validate_invalidQuantity() {
        TicketRequestDTO dto = new TicketRequestDTO();
        dto.setQuantity(0);
        dto.setEmployeeId(1L);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employeeBO));

        assertThrows(AppExceptions.class, () -> sut.execute(dto));
        verify(employeeRepository).findById(1L);
    }

    @Test
    void test_fillAdditionalFields() {
        TicketResponseDTO dto = new TicketResponseDTO();

        sut.fillAdditionalFields(dto, employeeBO);

        assertNotNull(dto.getEmployee());
        assertEquals(employeeBO.getId(), dto.getEmployee().getId());
    }
}
