package net.codeclickfilm.springboot.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import net.codeclickfilm.springboot.exception.ResourceNotFoundException;
import net.codeclickfilm.springboot.model.Employee;
import net.codeclickfilm.springboot.repository.EmployeeRepository;
import net.codeclickfilm.springboot.service.EmployeeService;

//@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	// Autowired
	// Dependency Injection using Constructor
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
//		Optional<Employee> employee=employeeRepository.findById(id);
//		if(employee.isPresent())
//		{
//			return employee.get();
//		}
//		else
//		{
//			throw new ResourceNotFoundException("Employee", "Id", id);
//		}
		
		return employeeRepository.findById(id).
				orElseThrow(()->new ResourceNotFoundException("Employee", "Id", id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		//first check if employee with given id exists in DB
		Employee existingEmployee=employeeRepository.findById(id).
				orElseThrow(()->new ResourceNotFoundException("Employee", "Id", id));
		existingEmployee.setFirstname(employee.getFirstname());
		existingEmployee.setLastname(employee.getLastname());
		existingEmployee.setEmail(employee.getEmail());
		
		//save existing employee to DB
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		employeeRepository.findById(id).
		orElseThrow(()->new ResourceNotFoundException("Employee", "Id", id));
		employeeRepository.deleteById(id);
	}

}
