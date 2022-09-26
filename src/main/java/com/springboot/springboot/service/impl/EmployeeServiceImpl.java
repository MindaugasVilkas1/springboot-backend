package com.springboot.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.springboot.exeption.ResourceNotFoundExeption;
import com.springboot.springboot.model.Employee;
import com.springboot.springboot.repository.EmployeeRepository;
import com.springboot.springboot.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService{
	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
	Optional<Employee> employee = employeeRepository.findById(id);
	if(employee.isPresent()) {
	return employee.get();
	}else {
		throw new ResourceNotFoundExeption("Employee", "id", id);
	}
//		//lambda expression
//		return employeeRepository.findById(id).orElseThrow(() ->
//		new ResourceNotFoundExeption("Employee", "id", id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// we need to check whether with given id is exist in DB or not
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundExeption("Employee", "id", id));
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		// save existing employee to database
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		//Check whether a employee exist or not
		employeeRepository.findById(id).orElseThrow(() ->new ResourceNotFoundExeption("Employee", "id", id));
		employeeRepository.deleteById(id);
	
	}

}
