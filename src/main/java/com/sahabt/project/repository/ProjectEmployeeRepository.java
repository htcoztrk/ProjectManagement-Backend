package com.sahabt.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahabt.project.entity.ProjectEmployee;

import java.util.Optional;

@Repository
public interface ProjectEmployeeRepository extends JpaRepository<ProjectEmployee, Long>{
    Optional<ProjectEmployee> findByEmployee_IdAndProject_Id(Long employee_id, Long project_id);
}
