package com.sahabt.project.service;

import java.util.List;

import com.sahabt.project.dto.request.EmployeeRequest;
import com.sahabt.project.dto.response.EmployeeResponse;

public interface EmployeeService {

	EmployeeResponse add(EmployeeRequest request);
	EmployeeResponse update(Long id,EmployeeRequest request);
	EmployeeResponse delete(Long id);
	EmployeeResponse getById(Long id);
	List<EmployeeResponse> getAll();
}
