package com.example.demo.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	private EmployeeService employeeService;
	
	
	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}


	@GetMapping("/employee")
	public List<Employee>findAll() {
		return employeeService.findAll();
	}
	
	@GetMapping("/employee/{id}")
	public Employee findById(@PathVariable int id) {
		Employee theEmployee= employeeService.findById(id);
		
		if (theEmployee==null) {
			throw new RuntimeException("Employee id Not found : "+id);
		}
		
		return theEmployee;
	}
	
	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		theEmployee.setId(0);
		Employee dbEmployee=employeeService.save(theEmployee);
		return dbEmployee;
		
	}
	
	@PutMapping("/employee")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		Employee dbEmployee=employeeService.save(theEmployee);
		return dbEmployee;
		
	}
	
	@DeleteMapping("/employee/{id}")
	public String deleteEmployee(@PathVariable int id) {
		Employee dbEmployee=employeeService.findById(id);
		
		if (dbEmployee==null) {
			throw new RuntimeException("Employee id not found : "+id);
		}
		
		
		employeeService.deleteById(id);
		
		return "Employee id : "+id+" deleted succsessfully!";
		
	}
}
