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

  public static TicketStatus fromName(String name) {
    for (TicketStatus status : TicketStatus.values()) {
      if (status.getName().equals(name)) {
        return status;
      }
    }
    throw new IllegalArgumentException("No enum constant for name: " + name);
  }
}
