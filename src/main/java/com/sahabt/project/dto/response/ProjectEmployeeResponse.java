package com.sahabt.project.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEmployeeResponse {

	private EmpStatusResponse employeeStatus;
	private ProjectResponse projectResponse;
	private EmployeeResponse employeeResponse;
}
