package spring.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import spring.course.model.Employee;
import spring.course.service.EmployeeService;

@RestController
@RequestMapping("/endpoint")
public class EndPointController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/employee/", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> employees = employeeService.findAllEmployees();
		if (null == employees || employees.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public ResponseEntity<RestResponse> getEmployee(@PathVariable("id") long id) {
		Employee employee = employeeService.findById(id);
		if (employee == null) {
			return new ResponseEntity(new RestResponse("NOT_FOUND", "Employee with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RestResponse>(new RestResponse("SUCCESS", employee), HttpStatus.OK);
	}

	@RequestMapping(value = "/employee/", method = RequestMethod.POST)
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee, UriComponentsBuilder ucBuilder) {

		if (employeeService.isEmployeeExist(employee)) {
			return new ResponseEntity(
					new RestResponse(
							"Unable to create. A Employee with name " + employee.getFirstName() + " already exist."),
					HttpStatus.CONFLICT);
		}
		employeeService.saveEmployee(employee);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/endpoint/employee/{id}").buildAndExpand(employee.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
		Employee resultEmployee = employeeService.findById(id);

		if (resultEmployee == null) {
			return new ResponseEntity(new RestResponse("Unable to upate. Employee with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		resultEmployee.setFirstName(employee.getFirstName());
		resultEmployee.setLastName(employee.getLastName());

		resultEmployee.setEmail(employee.getEmail());
		resultEmployee.setDateOfBirth(employee.getDateOfBirth());

		resultEmployee.setYearOfExp(employee.getYearOfExp());

		employeeService.updateEmployee(resultEmployee);
		return new ResponseEntity<Employee>(resultEmployee, HttpStatus.OK);
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") long id) {

		Employee employee = employeeService.findById(id);
		if (employee == null) {
			return new ResponseEntity(new RestResponse("Unable to delete. Employee with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		employeeService.deleteEmployeeById(id);
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/employee/", method = RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteAllEmployees() {
		employeeService.deleteAllEmployees();
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}

}