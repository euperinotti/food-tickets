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

  public static EmployeeStatus fromName(String name) {
    for (EmployeeStatus status : EmployeeStatus.values()) {
      if (status.getName().equals(name)) {
        return status;
      }
    }
    throw new IllegalArgumentException("No enum constant for name: " + name);
  }
}
