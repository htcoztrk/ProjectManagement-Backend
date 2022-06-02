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

import com.sahabt.project.dto.request.ProjectEmployeeRequest;
import com.sahabt.project.dto.response.ProjectEmployeeResponse;
import com.sahabt.project.service.ProjectEmployeeService;

@RestController
@RequestMapping("/projectEmployee")
@CrossOrigin
public class ProjectEmployeeController {

	private ProjectEmployeeService projectEmployeeService;

	public ProjectEmployeeController(ProjectEmployeeService projectEmployeeService) {
		
		this.projectEmployeeService = projectEmployeeService;
	}
	@GetMapping(value="/getAll")
	public List<ProjectEmployeeResponse> getAll(){
		return projectEmployeeService.getAll();
	}

	@GetMapping(value="/getById/{id}")
	public ProjectEmployeeResponse getById(@PathVariable Long id){
		return projectEmployeeService.getById(id);
	}

	@PostMapping("/addEmployee")
	public ProjectEmployeeResponse addEmployee(@RequestBody ProjectEmployeeRequest request) {
		return projectEmployeeService.add(request);
	}

	@PutMapping("/updateEmployee/{id}")
	public ProjectEmployeeResponse updateProjectEmployee(@PathVariable Long id,@RequestBody ProjectEmployeeRequest request) {
		return projectEmployeeService.update(id,request);
	}

	@DeleteMapping("/{id}")
	public ProjectEmployeeResponse deleteEmployee(Long id) {
		return projectEmployeeService.delete(id);
	}
}
