package spring.course.service;


import java.util.List;

import spring.course.model.Employee;

public interface EmployeeService {
	
	Employee findById(Long id);

	Employee findByFirstName(String name);

	void saveEmployee(Employee employee);

	void updateEmployee(Employee employee);

	void deleteEmployeeById(Long id);

	void deleteAllEmployees();

	List<Employee> findAllEmployees();

	boolean isEmployeeExist(Employee employee);
}