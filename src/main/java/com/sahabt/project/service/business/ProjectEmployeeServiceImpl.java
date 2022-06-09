package com.sahabt.project.service.business;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.sahabt.project.dto.response.EmpStatusResponse;
import com.sahabt.project.dto.response.EmployeeResponse;
import com.sahabt.project.dto.response.ProjectResponse;
import com.sahabt.project.repository.EmpStatusRepository;
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
	private EmpStatusRepository empStatusRepository;
	private ModelMapper modelMapper;
	private ProjectService projectService;
	private EmployeeService employeeService;

	public ProjectEmployeeServiceImpl(ProjectEmployeeRepository projectEmployeeRepository, ModelMapper modelMapper, ProjectService projectService, EmployeeService employeeService,EmpStatusRepository empStatusRepository) {
		this.projectEmployeeRepository = projectEmployeeRepository;
		this.modelMapper = modelMapper;
		this.projectService = projectService;
		this.employeeService = employeeService;
		this.empStatusRepository=empStatusRepository;
	}

	@Transactional
	@Override
	public ProjectEmployeeResponse add(ProjectEmployeeRequest request) {
		var projectEmployee = new ProjectEmployee();
		var employee = employeeService.findById(request.getEmployeeId());
		var project = projectService.findById(request.getProjectId());
		var empStatus=empStatusRepository.findById(request.getEmployeeStatus().getId()).get();

		projectEmployee.setEmployee(employee);
		projectEmployee.setProject(project);
		projectEmployee.setEmployeeStatus(empStatus);
		var savedEntity = projectEmployeeRepository.save(projectEmployee);
		var response = modelMapper.map(savedEntity, ProjectEmployeeResponse.class);
		response.setEmployeeResponse(modelMapper.map(employee, EmployeeResponse.class));
		response.setProjectResponse(modelMapper.map(project, ProjectResponse.class));
		response.setEmployeeStatus(modelMapper.map(empStatus, EmpStatusResponse.class));
		return response;
	}

	@Transactional
	@Override
	public ProjectEmployeeResponse update(Long id, ProjectEmployeeRequest request) {
		var request1 = new ProjectEmployee();
		var employee = employeeService.findById(request.getEmployeeId());
		var project = projectService.findById(request.getProjectId());
		var empStatus=empStatusRepository.findById(request.getEmployeeStatus().getId()).get();
		request1.setEmployee(employee);
		request1.setProject(project);
		request1.setEmployeeStatus(empStatus);

		if(request1.getProject().getId()==null)
			throw new EntityNotFoundException();
		else {
			var updateId = projectEmployeeRepository.findByEmployee_IdAndProject_Id
					(request1.getEmployee().getId(),request1.getProject().getId()).get().getId();
			projectEmployeeRepository.deleteById(updateId);
			var updatedEntity = projectEmployeeRepository.save(request1);
			var response = modelMapper.map(updatedEntity, ProjectEmployeeResponse.class);

			var prjResponse = modelMapper.map(updatedEntity.getProject(), ProjectResponse.class);
			var empResponse = modelMapper.map(updatedEntity.getEmployee(), EmployeeResponse.class);
			var empStatusResponse = modelMapper.map(updatedEntity.getEmployeeStatus(),EmpStatusResponse.class);
			response.setProjectResponse(prjResponse);
			response.setEmployeeResponse(empResponse);
			response.setEmployeeStatus(empStatusResponse);
			return response;
		}
	}

	@Transactional
	@Override
	public ProjectEmployeeResponse delete(Long employeeId,Long projectId) {
		var deletedEmp = projectEmployeeRepository.findByEmployee_IdAndProject_Id(employeeId,projectId)
				.orElseThrow(() -> new EntityNotFoundException());
		projectEmployeeRepository.delete(deletedEmp);
		return modelMapper.map(deletedEmp,ProjectEmployeeResponse.class);
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
	public List<ProjectEmployeeResponse> getProjectByEmployeeId(Long id) {
		var result = projectEmployeeRepository.findAll();
		var response = result.stream().filter(e->e.getEmployee().getId()==id).toList();
		var response2 = response.stream()
				.map(p -> {
					var empResponse = modelMapper.map(p.getEmployee(), EmployeeResponse.class);
					var prjResponse = modelMapper.map(p.getProject(), ProjectResponse.class);
					var empStatusResponse = modelMapper.map(p.getEmployeeStatus(),EmpStatusResponse.class);
					var response1 = modelMapper.map(p, ProjectEmployeeResponse.class);
					response1.setEmployeeResponse(empResponse);
					response1.setProjectResponse(prjResponse);
					response1.setEmployeeStatus(empStatusResponse);
					return response1;
				})
				.toList();
		return response2;
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
