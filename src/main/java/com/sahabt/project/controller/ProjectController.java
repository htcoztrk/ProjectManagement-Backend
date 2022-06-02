package com.sahabt.project.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sahabt.project.dto.request.ProjectRequest;
import com.sahabt.project.dto.response.ProjectResponse;
import com.sahabt.project.exception.ProjectNotFoundException;
import com.sahabt.project.service.ProjectService;

@RestController
@RequestMapping("/project")
@CrossOrigin
public class ProjectController {

	private ProjectService projectService;

	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}
	@GetMapping(value="/getAll")
	public List<ProjectResponse> getAll(){
		return projectService.getAll();
	}
	@GetMapping(value="/getById/{id}")
	public ProjectResponse getById(@PathVariable Long id){
		return projectService.getById(id);
	}
	@PostMapping("/addProject")
	public ProjectResponse addProject(@RequestBody  ProjectRequest project) {
		return projectService.add(project);
	}
	@PutMapping("/updateProject/{id}")
	public ProjectResponse updateProject(@PathVariable Long id,@RequestBody ProjectRequest project) {
		return projectService.update(id,project);
	}
	@DeleteMapping("/{id}")
	public ProjectResponse deleteProject(@PathVariable Long id) {
		return projectService.delete(id);
	}

	@GetMapping("/date/{endDate}")
	// swaggerda 15.05.2022 şeklinde aranacak
	List<ProjectResponse> getProjectsByDate(@RequestParam LocalDate date) throws ProjectNotFoundException {
		return projectService.getProjectsByDate(date);
	}
}
