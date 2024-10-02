package br.com.euperinotti.foodtickets.domain.exceptions.enums;

public enum EmployeeExceptions {
  EMPLOYEE_NOT_FOUND("Employee not found"),
  EMPLOYEE_INVALID_STATUS("Invalid employee status"),
  EMPLOYEE_INVALID_NAME("Invalid employee name"),
  EMPLOYEE_INVALID_EMAIL("Invalid employee email"),
  EMPLOYEE_INVALID_PHONE("Invalid employee phone"),
  CPF_ALREADY_EXISTS("Cpf already exists");

  private String message;

  EmployeeExceptions(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
