package br.com.euperinotti.foodtickets.domain.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringUtils {
  public static String sanitizeCpf(String str) {
    return str.replaceAll("\\D", "");
  }

  public static String formatCpf(String str) {
    return String.format("%s.%s.%s-%s", str.substring(0, 3), str.substring(3, 6), str.substring(6, 9), str.substring(9));
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
