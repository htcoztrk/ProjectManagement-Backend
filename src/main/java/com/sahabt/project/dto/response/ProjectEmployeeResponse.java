package com.sahabt.project.dto.response;

import com.sahabt.project.entity.Employee;
import com.sahabt.project.entity.EmployeeStatus;
import com.sahabt.project.entity.Project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEmployeeResponse {

	private Long id;
	private EmployeeStatus employeeStatus;
	private Project project;
	private Employee employee;
}
