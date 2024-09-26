package br.com.euperinotti.foodtickets.domain.repository;

import java.util.List;

import br.com.euperinotti.foodtickets.domain.entities.EmployeeBO;
import br.com.euperinotti.foodtickets.domain.enums.EmployeeStatus;

public interface IEmployeeRepository {
  EmployeeBO save(EmployeeBO employee);
  EmployeeBO findById(Integer id);
  void deleteById(Integer id);
  List<EmployeeBO> findAll();
  List<EmployeeBO> findByStatus(EmployeeStatus status);
  List<EmployeeBO> findByNameContainingIgnoreCase(String name);
  List<EmployeeBO> findByCpf(String cpf);
  EmployeeBO updateById(Integer id, EmployeeBO employee);
}
