package br.com.euperinotti.foodtickets.domain.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringUtils {
  public static String sanitizeCpf(String str) {
    if (isEmpty(str)) {
      return null;
    }

    return str.replaceAll("\\D", "");
  }

  public static String formatCpf(String str) {
    if (str.length() != 11) {
      throw new StringIndexOutOfBoundsException("CPF deve ter 11 dígitos.");
    }

    return String.format("%s.%s.%s-%s", str.substring(0, 3), str.substring(3, 6), str.substring(6, 9),
        str.substring(9));
  }

  public static String parseDateToBRFormat(LocalDateTime date) {
    return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
  }

  public static String sanitizeName(String str) {
    return str.trim().toUpperCase();
  }

  public static boolean isEmpty(String str) {
    return str == null || str.isEmpty();
  }
}
