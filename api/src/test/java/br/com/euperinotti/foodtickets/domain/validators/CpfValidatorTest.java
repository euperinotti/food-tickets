package br.com.euperinotti.foodtickets.domain.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CpfValidatorTest {

  @Test
  void test_isValidCPF_shouldReturnTrueForValidCPF() {
    String validCpf = "12345678909";

    boolean result = CpfValidator.isValidCPF(validCpf);

    assertTrue(result, "Deveria retornar true para um CPF válido");
  }

  @Test
  void test_isValidCPF_shouldReturnFalseForInvalidCPF() {
    String invalidCpf = "12345678900";

    boolean result = CpfValidator.isValidCPF(invalidCpf);

    assertFalse(result, "Deveria retornar false para um CPF inválido");
  }

  @Test
  void test_isValidCPF_shouldReturnFalseForCPFWithRepeatedNumbers() {
    String repeatedCpf = "11111111111";

    boolean result = CpfValidator.isValidCPF(repeatedCpf);

    assertFalse(result, "Deveria retornar false para CPFs com números repetidos");
  }

  @Test
  void test_isValidCPF_shouldReturnFalseForEmptyCPF() {
    String emptyCpf = "";

    boolean result = CpfValidator.isValidCPF(emptyCpf);

    assertFalse(result, "Deveria retornar false para CPF vazio");
  }

  @Test
  void test_isValidCPF_shouldReturnFalseForNullCPF() {
    String nullCpf = null;

    boolean result = CpfValidator.isValidCPF(nullCpf);

    assertFalse(result, "Deveria retornar false para CPF nulo");
  }

  @Test
  void test_isValidCPF_shouldReturnFalseForCpfWithLessThan11Digits() {
    String shortCpf = "12345678";

    boolean result = CpfValidator.isValidCPF(shortCpf);

    assertFalse(result, "Deveria retornar false para CPF com menos de 11 dígitos");
  }

  @Test
  void test_isValidCPF_shouldReturnFalseForCpfWithMoreThan11Digits() {
    String longCpf = "123456789012";

    boolean result = CpfValidator.isValidCPF(longCpf);

    assertFalse(result, "Deveria retornar false para CPF com mais de 11 dígitos");
  }
}
