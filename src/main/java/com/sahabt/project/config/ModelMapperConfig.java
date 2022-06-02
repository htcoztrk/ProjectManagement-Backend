package com.sahabt.project.config;

import com.sahabt.project.dto.response.ProjectEmployeeResponse;
import com.sahabt.project.entity.ProjectEmployee;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	private static final Converter<ProjectEmployee, ProjectEmployeeResponse>  PROJECT_EMPLOYEE_TO_PROJECT_EMPLOYEE_RESPONSE_CONVERTER =
			context -> {
				var response = new ProjectEmployeeResponse();
				response.setEmployeeStatus(context.getSource().getEmployeeStatus());
				return response;
			};
	@Bean
	public ModelMapper mapper() {
		var mapper = new ModelMapper();
		mapper.addConverter(PROJECT_EMPLOYEE_TO_PROJECT_EMPLOYEE_RESPONSE_CONVERTER, ProjectEmployee.class, ProjectEmployeeResponse.class);
		return mapper;
	}
}
