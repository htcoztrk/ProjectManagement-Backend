package com.sahabt.project.dto.response;

import com.sahabt.project.entity.EmployeeStatus;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEmployeeResponse {

	private EmployeeStatus employeeStatus;
	private ProjectResponse projectResponse;
	private EmployeeResponse employeeResponse;
}
