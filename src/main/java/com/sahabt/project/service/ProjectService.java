package com.sahabt.project.service;

import java.time.LocalDate;
import java.util.List;

import com.sahabt.project.dto.request.ProjectRequest;
import com.sahabt.project.dto.response.ProjectResponse;
import com.sahabt.project.entity.Project;
import com.sahabt.project.exception.ProjectNotFoundException;

public interface ProjectService {

	ProjectResponse add(ProjectRequest project);
	ProjectResponse update(Long id,ProjectRequest project);
	ProjectResponse delete(Long id);
	ProjectResponse getById(Long id);
	Project findById(Long id);
	List<ProjectResponse> getAll();

	List<ProjectResponse> getProjectsByDate(LocalDate date) throws ProjectNotFoundException;
}
