package com.niecey.SpringBootDemo.mySpringBootApp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niecey.SpringBootDemo.mySpringBootApp.exception.ResourceNotFoundException;
import com.niecey.SpringBootDemo.mySpringBootApp.model.Employee;
import com.niecey.SpringBootDemo.mySpringBootApp.repository.EmployeeRepository;

@RestController
@RequestMapping("/niecey_api/v1")

public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;

    
//  get all employees
  

  @GetMapping("/employees")
  public List<Employee> getAllEmployees(Model model) {
  	
  return this.employeeRepository.findAll();
  
}


    
//  get all employees by id

  @GetMapping("/employees/{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
      throws ResourceNotFoundException {
      Employee employee = employeeRepository.findById(employeeId)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
      return ResponseEntity.ok().body(employee);
  }
  
//  save employee
  
  @PostMapping("/employees")
  public Employee createEmployee(@Valid @RequestBody Employee employee) {
	  return employeeRepository.save(employee);
  }
  
//  Update Employee
  
  @PutMapping("/employees/{id}")
  public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
		  @Valid @RequestBody Employee employeeDetails)
	      throws ResourceNotFoundException {
	      Employee employee = employeeRepository.findById(employeeId)
	    		  .orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
	      
	      
	     employee.setEmail(employeeDetails.getEmail()); 
	     employee.setFirstName(employeeDetails.getFirstName());
	     employee.setLastName(employeeDetails.getLastName());
	     
	     
	     final Employee updatedEmployee = employeeRepository.save(employee);
	     
	     
	     return ResponseEntity.ok(updatedEmployee);
	      
	      }
  
//  Delete Employee
  
  @DeleteMapping("/employees/{id}")
  public Map<String, Boolean> deletedEmployee(@PathVariable(value = "id") Long employeeId)
			      throws ResourceNotFoundException {
			      Employee employee = employeeRepository.findById(employeeId)
			    		  .orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
  
			      employeeRepository.delete(employee);
			      Map<String, Boolean> response = new HashMap<>();
			      
			      response.put("deleted employee", Boolean.TRUE);
			      
			      return response;
  
  }
  
  
}
