package com.sahabt.project.repository;

import com.sahabt.project.entity.EmpStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpStatusRepository extends JpaRepository<EmpStatus,Long> {
}
