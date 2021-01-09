package spring.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.course.configuration.MerticLogger;
import spring.course.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


	Employee findByFirstName(String name);

}
