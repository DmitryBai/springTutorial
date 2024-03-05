package spring.tutorial.crud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.tutorial.crud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
