package br.com.euperinotti.foodtickets.domain.enums;

public enum EmployeeStatus {
  ACTIVE("A"),
  INACTIVE("I");

  private String name;

  EmployeeStatus(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
