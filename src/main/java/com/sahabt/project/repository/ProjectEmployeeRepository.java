package com.sahabt.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahabt.project.entity.ProjectEmployee;

import java.util.List;

@Repository
public interface ProjectEmployeeRepository extends JpaRepository<ProjectEmployee, Long>{
}
