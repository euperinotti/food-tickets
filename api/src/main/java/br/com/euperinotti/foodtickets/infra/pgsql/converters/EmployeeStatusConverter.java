package br.com.euperinotti.foodtickets.infra.pgsql.converters;

import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EmployeeStatusConverter implements AttributeConverter<EmployeeStatus, String> {

  @Override
  public String convertToDatabaseColumn(EmployeeStatus status) {
    if (status == null) {
      return null;
    }
    return status.getName();
  }

  @Override
  public EmployeeStatus convertToEntityAttribute(String dbData) {
    if (dbData == null) {
      return null;
    }
    return EmployeeStatus.fromName(dbData);
  }
}
