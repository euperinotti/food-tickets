package br.com.euperinotti.foodtickets.infra.pgsql.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;

class TicketStatusConverterTest {

    private TicketStatusConverter sut;

    @BeforeEach
    void setup() {
        sut = new TicketStatusConverter();
    }

    @Test
    void test_convertToDatabaseColumn_shouldReturnNullForNullStatus() {
        String result = sut.convertToDatabaseColumn(null);
        assertNull(result);
    }

    @Test
    void test_convertToDatabaseColumn_shouldReturnStatusNameForValidStatus() {
        TicketStatus status = TicketStatus.ACTIVE;
        String result = sut.convertToDatabaseColumn(status);
        assertEquals("A", result);
    }

    @Test
    void test_convertToEntityAttribute_shouldReturnNullForNullDbData() {
        TicketStatus result = sut.convertToEntityAttribute(null);
        assertNull(result);
    }

    @Test
    void test_convertToEntityAttribute_shouldReturnStatusForValidDbData() {
        String dbData = "A";
        TicketStatus result = sut.convertToEntityAttribute(dbData);
        assertEquals(TicketStatus.ACTIVE, result);
    }

    @Test
    void test_convertToEntityAttribute_shouldThrowForInvalidDbData() {
        String invalidDbData = "INVALID_STATUS";
        assertThrows(IllegalArgumentException.class, () -> {
            sut.convertToEntityAttribute(invalidDbData);
        });
    }
}
