package com.sahabt.project.dto.request;

import com.sahabt.project.entity.EmployeeStatus;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEmployeeRequest {

	private Long projectId;
	private Long employeeId;
	private EmployeeStatus employeeStatus;
}
