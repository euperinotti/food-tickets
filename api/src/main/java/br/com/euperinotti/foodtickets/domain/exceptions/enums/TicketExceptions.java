package br.com.euperinotti.foodtickets.domain.exceptions.enums;

public enum TicketExceptions {
  TICKET_NOT_FOUND("Ticket not found"),
  TICKET_INVALID_STATUS("Invalid ticket status"),
  TICKET_INVALID_CUSTOMER("Invalid ticket customer"),
  TICKET_INVALID_EMPLOYEE("Invalid ticket employee"),
  TICKET_INVALID_PRODUCT("Invalid ticket product"),
  TICKET_INVALID_DATE("Invalid ticket date"),
  TICKET_INVALID_TIME("Invalid ticket time"),
  TICKET_INVALID_QUANTITY("Invalid ticket quantity"),
  TICKET_INVALID_SUBTOTAL("Invalid ticket subtotal"),
  TICKET_INVALID_TOTAL("Invalid ticket total");

  private String message;

  TicketExceptions(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
