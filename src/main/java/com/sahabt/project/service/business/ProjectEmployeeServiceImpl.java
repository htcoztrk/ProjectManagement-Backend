package com.sahabt.project.service.business;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.sahabt.project.dto.response.EmployeeResponse;
import com.sahabt.project.dto.response.ProjectResponse;
import com.sahabt.project.service.EmployeeService;
import com.sahabt.project.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.sahabt.project.dto.request.ProjectEmployeeRequest;
import com.sahabt.project.dto.response.ProjectEmployeeResponse;
import com.sahabt.project.entity.ProjectEmployee;
import com.sahabt.project.repository.ProjectEmployeeRepository;
import com.sahabt.project.service.ProjectEmployeeService;

@Service
public class ProjectEmployeeServiceImpl implements ProjectEmployeeService{

	private ProjectEmployeeRepository projectEmployeeRepository;
	private ModelMapper modelMapper;
	private ProjectService projectService;
	private EmployeeService employeeService;

	public ProjectEmployeeServiceImpl(ProjectEmployeeRepository projectEmployeeRepository, ModelMapper modelMapper, ProjectService projectService, EmployeeService employeeService) {
		this.projectEmployeeRepository = projectEmployeeRepository;
		this.modelMapper = modelMapper;
		this.projectService = projectService;
		this.employeeService = employeeService;
	}

	@Transactional
	@Override
	public ProjectEmployeeResponse add(ProjectEmployeeRequest request) {
		var projectEmployee = new ProjectEmployee();
		var employee = employeeService.findById(request.getEmployeeId());
		var project = projectService.findById(request.getProjectId());
		projectEmployee.setEmployee(employee);
		projectEmployee.setProject(project);
		projectEmployee.setEmployeeStatus(request.getEmployeeStatus());
		var savedEntity = projectEmployeeRepository.save(projectEmployee);
		var response = modelMapper.map(savedEntity, ProjectEmployeeResponse.class);
		response.setEmployeeResponse(modelMapper.map(employee, EmployeeResponse.class));
		response.setProjectResponse(modelMapper.map(project, ProjectResponse.class));
		return response;

	}

	@Transactional
	@Override
	public ProjectEmployeeResponse update(Long id, ProjectEmployeeRequest request) {
		var result=projectEmployeeRepository.findById(id);
		if(!result.isPresent())
			throw new EntityNotFoundException();
		else {

			var project = projectService.findById(request.getProjectId());
			var employee = employeeService.findById(request.getEmployeeId());
			result.get().setProject(project);
			result.get().setEmployee(employee);
			result.get().setEmployeeStatus(request.getEmployeeStatus());

			var prjResponse = modelMapper.map(result.get().getProject(), ProjectResponse.class);
			var empResponse = modelMapper.map(result.get().getEmployee(), EmployeeResponse.class);
			var response = modelMapper.map(result, ProjectEmployeeResponse.class);
			response.setProjectResponse(prjResponse);
			response.setEmployeeResponse(empResponse);
			return response;

		}
	}

	@Transactional
	@Override
	public ProjectEmployeeResponse delete(Long id) {
		var result=projectEmployeeRepository.findById(id)
				.orElseThrow(()->new EntityNotFoundException());
		var empResponse = modelMapper.map(result.getEmployee(), EmployeeResponse.class);
		var prjResponse = modelMapper.map(result.getProject(), ProjectResponse.class);
		var response = modelMapper.map(result, ProjectEmployeeResponse.class);
		response.setEmployeeResponse(empResponse);
		response.setProjectResponse(prjResponse);
		return response;
	}

	@Override
	public ProjectEmployeeResponse getById(Long id) {
		var result= projectEmployeeRepository.findById(id)
				.orElseThrow(()->new EntityNotFoundException());
		var empResponse = modelMapper.map(result.getEmployee(), EmployeeResponse.class);
		var prjResponse = modelMapper.map(result.getProject(), ProjectResponse.class);
		var response = modelMapper.map(result, ProjectEmployeeResponse.class);
		response.setEmployeeResponse(empResponse);
		response.setProjectResponse(prjResponse);
		return response;
	}

	@Override
	public List<ProjectEmployeeResponse> getAll() {
		return projectEmployeeRepository.findAll()
				.stream()
				.map(p -> {
					var empResponse = modelMapper.map(p.getEmployee(), EmployeeResponse.class);
					var prjResponse = modelMapper.map(p.getProject(), ProjectResponse.class);
					var response = modelMapper.map(p, ProjectEmployeeResponse.class);
					response.setEmployeeResponse(empResponse);
					response.setProjectResponse(prjResponse);

					return response;
				})
				.toList();
	}

}
