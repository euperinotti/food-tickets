package br.com.euperinotti.foodtickets.infra.pgsql.converters;

import br.com.euperinotti.foodtickets.domain.enums.TicketStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TicketStatusConverter implements AttributeConverter<TicketStatus, String> {

  @Override
  public String convertToDatabaseColumn(TicketStatus status) {
    if (status == null) {
      return null;
    }
    return status.getName();
  }

  @Override
  public TicketStatus convertToEntityAttribute(String dbData) {
    if (dbData == null) {
      return null;
    }
    return TicketStatus.fromName(dbData);
  }
}
