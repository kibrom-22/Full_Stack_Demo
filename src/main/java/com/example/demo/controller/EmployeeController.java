package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import lombok.AllArgsConstructor;

@RestController  @CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api")
@AllArgsConstructor
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId) throws Exception{
		return employeeService.getEmployeeById(employeeId);
	}
	
	@PostMapping("/employees")
	public Employee creatEmployee(@Validated @RequestBody Employee employee) {
		return employeeService.creatEmployee(employee);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId, @Validated @RequestBody Employee employeeDetails) throws Exception{
		return employeeService.updateEmployee(employeeId, employeeDetails);
	}
	
	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable("id") long employeeId)throws Exception {
		return employeeService.deleteEmployee(employeeId);
	}

}
