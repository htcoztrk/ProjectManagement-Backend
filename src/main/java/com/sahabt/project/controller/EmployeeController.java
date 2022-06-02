package com.sahabt.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahabt.project.dto.request.EmployeeRequest;
import com.sahabt.project.dto.response.EmployeeResponse;
import com.sahabt.project.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		
		this.employeeService = employeeService;
	}
	@GetMapping(value="/getAll")
	public List<EmployeeResponse> getAll(){
		return employeeService.getAll();
	}
	@GetMapping(value="/getById/{id}")
	public EmployeeResponse getById(@PathVariable Long id){
		return employeeService.getById(id);
	}
	@PostMapping("/addEmployee")
	public EmployeeResponse addEmployee(@RequestBody EmployeeRequest employee) {
		return employeeService.add(employee);
	}
	@PutMapping("/updateEmployee/{id}")
	public EmployeeResponse updatEmployee(@PathVariable Long id,@RequestBody EmployeeRequest employee) {
		return employeeService.update(id,employee);
	}
	@DeleteMapping("delete/{id}")
	public EmployeeResponse deleteEmployee(Long id) {
		return employeeService.delete(id);
	}
}
