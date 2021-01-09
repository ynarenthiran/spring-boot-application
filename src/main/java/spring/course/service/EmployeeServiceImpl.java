package spring.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.course.configuration.MerticLogger;
import spring.course.model.Employee;
import spring.course.repositories.EmployeeRepository;



@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee findById(Long id) {
		return employeeRepository.findOne(id);
	}

	
	public Employee findByFirstName(String name) {
		return employeeRepository.findByFirstName(name);
	}

	@MerticLogger
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public void updateEmployee(Employee employee){
		saveEmployee(employee);
	}

	public void deleteEmployeeById(Long id){
		employeeRepository.delete(id);
	}

	public void deleteAllEmployees(){
		employeeRepository.deleteAll();
	}

	@MerticLogger
	public List<Employee> findAllEmployees(){
		return employeeRepository.findAll();
	}

	public boolean isEmployeeExist(Employee employee) {
		return findByFirstName(employee.getFirstName()) != null;
	}

}
