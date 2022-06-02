package com.sahabt.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahabt.project.entity.Employee;

import java.util.Optional;

@Repository
public interface  EmployeeRepository extends JpaRepository<Employee, Long>{

    Optional<Employee> findById(Long id);
}
