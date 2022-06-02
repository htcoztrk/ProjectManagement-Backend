package com.sahabt.project.service.business;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.sahabt.project.exception.ProjectAlreadyExistException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.sahabt.project.dto.request.ProjectRequest;
import com.sahabt.project.dto.response.ProjectResponse;
import com.sahabt.project.entity.Project;
import com.sahabt.project.exception.ProjectNotFoundException;
import com.sahabt.project.repository.ProjectRepository;
import com.sahabt.project.service.ProjectService;


@Service
public class ProjectServiceImpl implements ProjectService {

	private ProjectRepository projectRepository;
	private ModelMapper modelMapper;

	public ProjectServiceImpl(ProjectRepository projectRepository,ModelMapper modelMapper) {
		this.modelMapper=modelMapper;
		this.projectRepository = projectRepository;
	}

	@Transactional
	@Override
	public ProjectResponse add(ProjectRequest project) {
		var projectName = project.getProjectName();
		if (projectRepository.existsByProjectName(projectName))
			throw new ProjectAlreadyExistException();
		var result=modelMapper.map(project, Project.class);
		return modelMapper.map(projectRepository.save(result), ProjectResponse.class);
	}

	@Transactional
	@Override
	public ProjectResponse update(Long id,ProjectRequest project) {
		var result= projectRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
		modelMapper.map(project, result);
		return modelMapper.map(projectRepository.save(result),ProjectResponse.class);
	}

	@Transactional
	@Override
	public ProjectResponse delete(Long id) {
		var result= projectRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
		projectRepository.deleteById(id);
		return modelMapper.map(result, ProjectResponse.class);
	}

	@Override
	public ProjectResponse getById(Long id) {
		var result= projectRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
		 return modelMapper.map(result, ProjectResponse.class);

	}

	@Override
	public Project findById(Long id) {
		var project = projectRepository.findById(id);
		if (project.isPresent())
			return project.get();
		else
			throw new EntityNotFoundException();
	}

	@Override
	public List<ProjectResponse> getAll() {
		return projectRepository.findAll().stream()
				.map(p->modelMapper.map(p, ProjectResponse.class))
				.toList();
	}

	public List<ProjectResponse> getProjectsByDate(LocalDate date) throws ProjectNotFoundException {
		var date1 = LocalDate.of(date.getYear(),date.getMonthValue(),date.getDayOfMonth());
		var list = projectRepository.findByEndDate(date1);
		if(list.isEmpty())
			throw new ProjectNotFoundException("there is no project record on:" + date);
		return list.stream().map(pro -> modelMapper.map(pro, ProjectResponse.class)).toList();
	}

}
