package br.com.euperinotti.foodtickets.infra.pgsql.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import br.com.euperinotti.foodtickets.infra.pgsql.entities.PgSqlEmployeeEntity;
import br.com.euperinotti.foodtickets.infra.pgsql.mappers.PgSqlEmployeeMapper;
import br.com.euperinotti.foodtickets.infra.pgsql.repository.contracts.PgSqlEmployeeRepository;

public class PgSqlEmployeeRepositoryImplTest {

    @Mock
    private PgSqlEmployeeRepository jpa;

    @InjectMocks
    private PgSqlEmployeeRepositoryImplementation sut;

    private EmployeeBO bo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.bo = new EmployeeBO(1L, "John Doe", "123.456.789-10", EmployeeStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now());
    }

    private PgSqlEmployeeEntity makeInactiveEmployeeEntity() {
        PgSqlEmployeeEntity inactiveEmployee = new PgSqlEmployeeEntity();
        inactiveEmployee.setName("Kyle Schmidt");
        inactiveEmployee.setCpf("66418505745");
        inactiveEmployee.setStatus(EmployeeStatus.INACTIVE);
        inactiveEmployee.setCreatedAt(LocalDateTime.now());
        inactiveEmployee.setUpdatedAt(LocalDateTime.now());

        return inactiveEmployee;
    }

    private PgSqlEmployeeEntity makeActiveEmployeeEntity() {
        PgSqlEmployeeEntity activeEmployee = new PgSqlEmployeeEntity();
        activeEmployee.setName("Ophelia Beck");
        activeEmployee.setCpf("72197234469");
        activeEmployee.setStatus(EmployeeStatus.ACTIVE);
        activeEmployee.setCreatedAt(LocalDateTime.now());
        activeEmployee.setUpdatedAt(LocalDateTime.now());

        return activeEmployee;
    }

    @Test
    public void test_save_successful() {
        when(jpa.save(any(PgSqlEmployeeEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        EmployeeBO result = sut.save(bo);

        assertNotNull(result);
        assertEquals("JOHN DOE", result.getName());
    }

    @Test
    public void test_findById_found() {
        PgSqlEmployeeEntity entity = PgSqlEmployeeMapper.toEntity(bo);
        entity.setId(1L);
        when(jpa.findById(1L)).thenReturn(Optional.of(entity));

        Optional<EmployeeBO> result = sut.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    public void test_findById_notFound() {
        when(jpa.findById(999L)).thenReturn(Optional.empty());

        Optional<EmployeeBO> result = sut.findById(999L);

        assertFalse(result.isPresent());
    }

    @Test
    public void test_deleteById() {
        PgSqlEmployeeEntity entity = PgSqlEmployeeMapper.toEntity(bo);
        when(jpa.findById(1L)).thenReturn(Optional.of(entity));

        sut.deleteById(1L);

        verify(jpa, times(1)).deleteById(1L);
    }

    @Test
    public void test_findAll_successful() {
        PgSqlEmployeeEntity entity1 = PgSqlEmployeeMapper.toEntity(bo);
        entity1.setId(1L);

        PgSqlEmployeeEntity entity2 = new PgSqlEmployeeEntity();
        entity2.setName("Jane Doe");
        entity2.setStatus(EmployeeStatus.INACTIVE);
        entity2.setCreatedAt(LocalDateTime.now());
        entity2.setUpdatedAt(LocalDateTime.now());
        entity2.setCpf("987.654.321-09");
        entity2.setId(2L);

        when(jpa.findAll()).thenReturn(List.of(entity1, entity2));

        List<EmployeeBO> result = sut.findAll();

        assertEquals(2, result.size());
        assertEquals("JOHN DOE", result.get(0).getName());
        assertEquals("JANE DOE", result.get(1).getName());
    }

    @Test
    public void test_findByStatus_successful() {
        PgSqlEmployeeEntity entity = PgSqlEmployeeMapper.toEntity(bo);
        when(jpa.findByStatus(EmployeeStatus.ACTIVE)).thenReturn(List.of(entity));

        List<EmployeeBO> result = sut.findByStatus(EmployeeStatus.ACTIVE);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("JOHN DOE", result.get(0).getName());
    }

    @Test
    public void test_findByStatus_activeEmployees() {
        PgSqlEmployeeEntity activeEmployee = makeActiveEmployeeEntity();
        PgSqlEmployeeEntity inactiveEmployee = makeInactiveEmployeeEntity();

        when(jpa.findByStatus(EmployeeStatus.ACTIVE)).thenReturn(List.of(activeEmployee));

        List<EmployeeBO> activeEmployees = sut.findByStatus(EmployeeStatus.ACTIVE);

        assertEquals(1, activeEmployees.size());
        assertEquals("OPHELIA BECK", activeEmployees.get(0).getName());
    }

    @Test
    public void test_findByStatus_noActiveEmployees() {
        when(jpa.findByStatus(EmployeeStatus.ACTIVE)).thenReturn(List.of());

        List<EmployeeBO> activeEmployees = sut.findByStatus(EmployeeStatus.ACTIVE);

        assertTrue(activeEmployees.isEmpty());
    }

    @Test
    public void test_findByCpf_successful() {
        PgSqlEmployeeEntity entity = PgSqlEmployeeMapper.toEntity(bo);
        when(jpa.findByCpf("12345678910")).thenReturn(Optional.of(entity));

        Optional<EmployeeBO> result = sut.findByCpf("12345678910");

        assertTrue(result.isPresent());
        assertEquals("12345678910", result.get().getCpf());
    }

    @Test
    public void test_findByCpf_notFound() {
        when(jpa.findByCpf("98765432100")).thenReturn(Optional.empty());

        Optional<EmployeeBO> result = sut.findByCpf("98765432100");

        assertFalse(result.isPresent());
    }

    @Test
    public void test_updateById_successful() {
        PgSqlEmployeeEntity entity = PgSqlEmployeeMapper.toEntity(bo);
        entity.setId(1L);
        when(jpa.findById(1L)).thenReturn(Optional.of(entity));

        EmployeeBO updatedBO = new EmployeeBO(1L, "Updated Name", "12345678910", EmployeeStatus.ACTIVE, LocalDateTime.now(),
                LocalDateTime.now());

        when(jpa.save(any(PgSqlEmployeeEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        EmployeeBO result = sut.updateById(1L, updatedBO);

        assertNotNull(result);
        assertEquals("UPDATED NAME", result.getName());
    }

    @Test
    void test_countByStatus_shouldReturnCountForExistingStatus() {
        when(jpa.countByStatus(EmployeeStatus.ACTIVE)).thenReturn(5);

        Integer result = sut.countByStatus(EmployeeStatus.ACTIVE);

        assertEquals(5, result);
    }

    @Test
    void test_countByStatus_shouldReturnZeroForNonExistingStatus() {
        when(jpa.countByStatus(EmployeeStatus.INACTIVE)).thenReturn(0);

        Integer result = sut.countByStatus(EmployeeStatus.INACTIVE);

        assertEquals(0, result);
        verify(jpa, times(1)).countByStatus(EmployeeStatus.INACTIVE);
    }

    @Test
    void test_findEmployeeWithMostTickets_shouldReturnEmployeeBO() {
      PgSqlEmployeeEntity entity = PgSqlEmployeeMapper.toEntity(bo);
        when(jpa.findEmployeeWithMostTickets()).thenReturn(entity);

        EmployeeBO result = sut.findEmployeeWithMostTickets();

        assertNotNull(result);
        verify(jpa, times(1)).findEmployeeWithMostTickets();
    }

    @Test
    @Disabled
    void test_findEmployeeWithMostTickets_shouldReturnNullWhenNoEmployeeExists() {
        when(jpa.findEmployeeWithMostTickets()).thenReturn(null);

        EmployeeBO result = sut.findEmployeeWithMostTickets();

        assertNull(result);
        verify(jpa, times(1)).findEmployeeWithMostTickets();
    }
}
