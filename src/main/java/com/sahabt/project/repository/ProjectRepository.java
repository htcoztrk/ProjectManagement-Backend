package com.sahabt.project.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahabt.project.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
	List<Project> findByEndDate(LocalDate date);
	boolean existsByProjectName(String name);
	Optional<Project> findById(Long id);
}
