package br.com.euperinotti.foodtickets.domain.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class StringUtilsTest {

  @Test
  void test_sanitizeCpf_shouldRemoveNonDigits() {
    String cpfWithSpecialChars = "123.456.789-00";
    String sanitizedCpf = StringUtils.sanitizeCpf(cpfWithSpecialChars);

    assertEquals("12345678900", sanitizedCpf);
  }

  @Test
  void test_formatCpf_shouldFormatCpfCorrectly() {
    String rawCpf = "12345678900";
    String formattedCpf = StringUtils.formatCpf(rawCpf);

    assertEquals("123.456.789-00", formattedCpf);
  }

  @Test
  void test_parseDateToBRFormat_shouldReturnCorrectBRDateFormat() {
    LocalDateTime date = LocalDateTime.of(2024, 10, 3, 12, 0);
    String formattedDate = StringUtils.parseDateToBRFormat(date);

    assertEquals("03/10/2024", formattedDate);
  }

  @Test
  void test_sanitizeName_shouldTrimAndConvertToUpperCase() {
    String name = "   João da Silva   ";
    String sanitizedName = StringUtils.sanitizeName(name);

    assertEquals("JOÃO DA SILVA", sanitizedName);
  }

  @Test
  void test_isEmpty_shouldReturnTrueForNullString() {
    String nullString = null;
    boolean result = StringUtils.isEmpty(nullString);

    assertTrue(result);
  }

  @Test
  void test_isEmpty_shouldReturnTrueForEmptyString() {
    String emptyString = "";
    boolean result = StringUtils.isEmpty(emptyString);

    assertTrue(result);
  }

  @Test
  void test_isEmpty_shouldReturnFalseForNonEmptyString() {
    String nonEmptyString = "Hello";
    boolean result = StringUtils.isEmpty(nonEmptyString);

    assertFalse(result);
  }

  @Test
  void test_formatCpf_shouldThrowExceptionForInvalidLength() {
    String invalidCpf = "123456789";

    assertThrows(StringIndexOutOfBoundsException.class, () -> {
      StringUtils.formatCpf(invalidCpf);
    });
  }

  @Test
  void test_sanitizeCpf_shouldHandleEmptyString() {
    String emptyCpf = "";
    String sanitizedCpf = StringUtils.sanitizeCpf(emptyCpf);

    assertEquals(null, sanitizedCpf);
  }

  @Test
  void test_sanitizeCpf_shouldHandleNullString() {
    String nullCpf = null;
    String sanitizedCpf = StringUtils.sanitizeCpf(nullCpf);

    assertNull(sanitizedCpf);
  }

  @Test
  void test_formatCpf_shouldHandleCpfWithMoreThan11Digits() {
    String longCpf = "12345678900123";

    assertThrows(StringIndexOutOfBoundsException.class, () -> {
      StringUtils.formatCpf(longCpf);
    });
  }

  @Test
  void test_parseDateToBRFormat_shouldHandleNullDate() {
    LocalDateTime nullDate = null;

    assertThrows(NullPointerException.class, () -> {
      StringUtils.parseDateToBRFormat(nullDate);
    });
  }

  @Test
  void test_sanitizeName_shouldHandleEmptyString() {
    String emptyName = "";
    String sanitizedName = StringUtils.sanitizeName(emptyName);

    assertEquals("", sanitizedName);
  }

  @Test
  void test_sanitizeName_shouldHandleNullString() {
    String nullName = null;

    assertThrows(NullPointerException.class, () -> {
      StringUtils.sanitizeName(nullName);
    });
  }

  @Test
  void test_isEmpty_shouldReturnFalseForWhitespaceOnlyString() {
    String whitespaceOnlyString = "   ";
    boolean result = StringUtils.isEmpty(whitespaceOnlyString);

    assertFalse(result);
  }

  @Test
  void test_sanitizeCpf_shouldHandleCpfWithLetters() {
    String cpfWithLetters = "abc123.def456.ghi789-jk00";
    String sanitizedCpf = StringUtils.sanitizeCpf(cpfWithLetters);

    assertEquals("12345678900", sanitizedCpf);
  }

  @Test
  void test_sanitizeCpf_shouldHandleCpfWithSpecialCharacters() {
    String cpfWithSpecialChars = "@#123.$%^456.&*789-00!";
    String sanitizedCpf = StringUtils.sanitizeCpf(cpfWithSpecialChars);

    assertEquals("12345678900", sanitizedCpf);
  }
}
