package br.com.euperinotti.foodtickets.domain.repository;

import java.util.List;

import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;

public interface IEmployeeRepository {
  EmployeeBO save(EmployeeBO employee);
  EmployeeBO findById(Long id);
  void deleteById(Long id);
  List<EmployeeBO> findAll();
  List<EmployeeBO> findByStatus(EmployeeStatus status);
  EmployeeBO findByCpf(String cpf);
  EmployeeBO updateById(Long id, EmployeeBO employee);
}
