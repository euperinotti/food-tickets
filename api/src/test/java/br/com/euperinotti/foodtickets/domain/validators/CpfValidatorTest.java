package br.com.euperinotti.foodtickets.domain.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class CpfValidatorTest {

    @Test
    void test_isValidCPF_shouldReturnTrueForValidCPF() {
        String validCpf = "12345678909";

        boolean result = CpfValidator.isValidCPF(validCpf);

        assertTrue(result);
    }

    @Test
    void test_isValidCPF_shouldReturnFalseForInvalidCPF() {
        String invalidCpf = "12345678900";

        boolean result = CpfValidator.isValidCPF(invalidCpf);

        assertFalse(result);
    }

    @Test
    void test_isValidCPF_shouldReturnFalseForCPFWithRepeatedNumbers() {
        String repeatedCpf = "11111111111";

        boolean result = CpfValidator.isValidCPF(repeatedCpf);

        assertFalse(result);
    }

    @Test
    void test_isValidCPF_shouldReturnFalseForEmptyCPF() {
        String emptyCpf = "";

        boolean result = CpfValidator.isValidCPF(emptyCpf);

        assertFalse(result);
    }

    @Test
    void test_isValidCPF_shouldReturnFalseForNullCPF() {
        String nullCpf = null;

        boolean result = CpfValidator.isValidCPF(nullCpf);

        assertFalse(result);
    }

    @Test
    void test_isValidCPF_shouldReturnFalseForCpfWithLessThan11Digits() {
        String shortCpf = "12345678";

        boolean result = CpfValidator.isValidCPF(shortCpf);

        assertFalse(result);
    }

    @Test
    void test_isValidCPF_shouldReturnFalseForCpfWithMoreThan11Digits() {
        String longCpf = "123456789012";

        boolean result = CpfValidator.isValidCPF(longCpf);

        assertFalse(result);
    }

    @Test
    void test_isValidCPF_shouldReturnFalseForRepeatedNumbers() {
        String[] repeatedCpfs = {
            "00000000000",
            "11111111111",
            "22222222222",
            "33333333333",
            "44444444444",
            "55555555555",
            "66666666666",
            "77777777777",
            "88888888888",
            "99999999999"
        };

        for (String cpf : repeatedCpfs) {
            boolean result = CpfValidator.isValidCPF(cpf);
            assertFalse(result);
        }
    }
}
