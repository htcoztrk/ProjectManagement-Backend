package com.sahabt.project.service.business;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.sahabt.project.dto.request.EmployeeRequest;
import com.sahabt.project.dto.response.EmployeeResponse;
import com.sahabt.project.entity.Employee;
import com.sahabt.project.repository.EmployeeRepository;
import com.sahabt.project.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	private ModelMapper modelMapper;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
		this.employeeRepository = employeeRepository;
	}

	@Transactional
	@Override
	public EmployeeResponse add(EmployeeRequest request) {
		var employee = modelMapper.map(request, Employee.class);
		return modelMapper.map(employeeRepository.save(employee), EmployeeResponse.class);
	}
    @Transactional
	@Override
	public EmployeeResponse update(Long id, EmployeeRequest request) {

		var employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
		modelMapper.map(request, employee);
		return modelMapper.map(employee,EmployeeResponse.class);
	}

    @Transactional
	@Override
	public EmployeeResponse delete(Long id) {
		var employee = employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		employeeRepository.deleteById(id);
		return modelMapper.map(employee, EmployeeResponse.class);
	}

	@Override
	public EmployeeResponse getById(Long id) {
		var employee= employeeRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
		return modelMapper.map(employee, EmployeeResponse.class);
	}

	@Override
	public List<EmployeeResponse> getAll() {
		return employeeRepository.findAll()
				.stream()
				.map(employee->modelMapper.map(employee, EmployeeResponse.class))
				.toList();
	}

}
