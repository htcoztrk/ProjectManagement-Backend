package com.sahabt.project.service.business;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

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
	public ProjectEmployeeServiceImpl(ProjectEmployeeRepository projectEmployeeRepository,ModelMapper modelMapper) {
		this.modelMapper=modelMapper;
		this.projectEmployeeRepository = projectEmployeeRepository;
	}

	@Transactional
	@Override
	public ProjectEmployeeResponse add(ProjectEmployeeRequest request) {
		var result=modelMapper.map(request, ProjectEmployee.class);
		return modelMapper.map(projectEmployeeRepository.save(result), ProjectEmployeeResponse.class);
				
	
	}

	@Transactional
	@Override
	public ProjectEmployeeResponse update(Long id, ProjectEmployeeRequest projectEmployee) {
		var result=projectEmployeeRepository.findById(id)
				.orElseThrow(()->new EntityNotFoundException());
		modelMapper.map(projectEmployee, result);
		return  modelMapper.map(projectEmployeeRepository.save(result), ProjectEmployeeResponse.class);
				
		
	}

	@Transactional
	@Override
	public ProjectEmployeeResponse delete(Long id) {
		var result=projectEmployeeRepository.findById(id)
				.orElseThrow(()->new EntityNotFoundException());
		 projectEmployeeRepository.deleteById(id);
		 return modelMapper.map(result,ProjectEmployeeResponse.class);
	}

	@Override
	public ProjectEmployeeResponse getById(Long id) {
		var result= projectEmployeeRepository.findById(id)
				.orElseThrow(()->new EntityNotFoundException());
		return modelMapper.map(result, ProjectEmployeeResponse.class);
	}

	@Override
	public List<ProjectEmployeeResponse> getAll() {
		return projectEmployeeRepository.findAll()
				.stream()
				.map(p->modelMapper.map(p, ProjectEmployeeResponse.class))
				.toList();
	}

}
