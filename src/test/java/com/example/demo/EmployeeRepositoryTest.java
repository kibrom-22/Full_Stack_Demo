package com.example.demo;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;


import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)

public class EmployeeRepositoryTest {
	

	@Autowired
	private EmployeeRepository employeeRepository;
	
	 private Employee employee;

	    @BeforeEach
	    public void setup(){
	        employee = Employee.builder()
	                .firstName("Ramesh")
	                .lastName("Fadatare")
	                .email("ramesh@gmail,com")
	                .build();
	    }
	
	@Test
	@Order(1)
	@Rollback(value = false)
	public void saveEmployeeTest() {
		Employee employee = Employee.builder()
				.firstName("John")
				.lastName("Ebrahim")
				.email("john@gmail.com")
				.build();
		
		employeeRepository.save(employee);
		Assertions.assertThat(employee.getId()).isNotNull();
		Assertions.assertThat(employee.getId()).isGreaterThan(0);
	}
	
	@Test
	@Order(2)
	public void getEmployeeTest() {
		Employee employee = employeeRepository.findById(1L).get();
		Assertions.assertThat(employee.getId()).isEqualTo(1L);
	}
	
	@Test
	@Order(3)
	public void getListOfEmployeesTest() {
		List<Employee> employees = employeeRepository.findAll();
		Assertions.assertThat(employees.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateEmployeeList() {
		Employee employee = employeeRepository.findById(1L).get();
		employee.setEmail("edu@gmail.com");
		Employee employeeUpdated = employeeRepository.save(employee);
		Assertions.assertThat(employeeUpdated.getEmail()).isEqualTo("edu@gmail.com");
	}
	
	@Test
	@Order(5)
	@Rollback(value = false)
	public void deleteEmployeeTest() {
		Employee employee = employeeRepository.findById(1L).get();
		employeeRepository.delete(employee);
		Employee employee1 = null;
		Optional<Employee> optionalEmployee = Optional.ofNullable(employeeRepository.findById(1L).get());
		if(optionalEmployee.isPresent()) {
			employee1 = optionalEmployee.get();
		}
		Assertions.assertThat(employee1).isNull();
	}
	


}
