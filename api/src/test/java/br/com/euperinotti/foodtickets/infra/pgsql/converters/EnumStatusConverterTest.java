package br.com.euperinotti.foodtickets.infra.pgsql.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;

class EmployeeStatusConverterTest {

    private EmployeeStatusConverter sut;

    @BeforeEach
    void setup() {
      sut = new EmployeeStatusConverter();
    }

    @Test
    void test_convertToDatabaseColumn_shouldReturnNullForNullStatus() {
        String result = sut.convertToDatabaseColumn(null);
        assertNull(result);
    }

    @Test
    void test_convertToDatabaseColumn_shouldReturnStatusNameForValidStatus() {
        EmployeeStatus status = EmployeeStatus.ACTIVE;
        String result = sut.convertToDatabaseColumn(status);
        assertEquals("ACTIVE", result);
    }

    @Test
    void test_convertToEntityAttribute_shouldReturnNullForNullDbData() {
        EmployeeStatus result = sut.convertToEntityAttribute(null);
        assertNull(result);
    }

    @Test
    void test_convertToEntityAttribute_shouldReturnStatusForValidDbData() {
        String dbData = "ACTIVE";
        EmployeeStatus result = sut.convertToEntityAttribute(dbData);
        assertEquals(EmployeeStatus.ACTIVE, result);
    }

    @Test
    void test_convertToEntityAttribute_shouldReturnNullForInvalidDbData() {
        String invalidDbData = "INVALID_STATUS";
        assertThrows(IllegalArgumentException.class, () -> {
            sut.convertToEntityAttribute(invalidDbData);
        });
    }

}
