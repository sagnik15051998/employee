package com.employee.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dto.UpdateDto;
import com.employee.dto.UpdateResponseDto;
import com.employee.entity.Employee;
import com.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeApi {
	
	@Autowired
	private EmployeeService employeeService; 
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable ("id") Long id) throws Exception {
		return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> createEmployee(@RequestBody Employee emp) throws Exception {
		return new ResponseEntity<>(employeeService.createEmployee(emp), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable ("id") Long id) throws Exception {
		return new ResponseEntity<>(employeeService.deleteEmployee(id), HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<UpdateResponseDto> updateEmployee(@RequestBody UpdateDto updateDto) throws Exception {
		return new ResponseEntity<>(employeeService.updateEmployee(updateDto), HttpStatus.OK);
	}

}
