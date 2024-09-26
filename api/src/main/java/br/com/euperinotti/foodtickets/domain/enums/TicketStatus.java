package br.com.euperinotti.foodtickets.domain.enums;

public enum TicketStatus {
  ACTIVE("A"),
  INACTIVE("I");

  private String name;

  TicketStatus(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
