package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId) throws Exception{
	Employee employee =	employeeRepository.findById(employeeId).orElseThrow(()-> new Exception("Employee not found for this id:"+employeeId));
	
	return ResponseEntity.ok().body(employee);
	}
	
	public Employee creatEmployee(@Validated @RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId, @Validated @RequestBody Employee employeeDetails) throws Exception{
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(()-> new Exception("Employee not found for this id :"+employeeId));
		
		 employee.setFirstName(employeeDetails.getFirstName());
		 employee.setLastName(employeeDetails.getLastName());
		 employee.setEmail(employeeDetails.getEmail());
		 
		 Employee updatedEmployee = employeeRepository.save(employee);
		 
		 return ResponseEntity.ok(updatedEmployee);
	}
	
	public Map<String, Boolean> deleteEmployee(@PathVariable("id") long employeeId) throws Exception{
	Employee employee =	employeeRepository.findById(employeeId).orElseThrow(()-> new Exception("Employee not found for this id: "+employeeId));
	
	employeeRepository.deleteById(employeeId);
	Map<String, Boolean> response = new HashMap<>();
	response.put("Deleted", Boolean.TRUE);
	
	return response;
		
	}
	

}
