package com.sahabt.project.service;

import java.util.List;

import com.sahabt.project.dto.request.ProjectEmployeeRequest;
import com.sahabt.project.dto.response.ProjectEmployeeResponse;

public interface ProjectEmployeeService {

	ProjectEmployeeResponse add(ProjectEmployeeRequest projectEmployee);
	ProjectEmployeeResponse update(Long id,ProjectEmployeeRequest projectEmployee);
	ProjectEmployeeResponse delete(Long id);
	ProjectEmployeeResponse getById(Long id);

	List<ProjectEmployeeResponse> getProjectByEmployeeId(Long id);

	List<ProjectEmployeeResponse> getAll();
}
