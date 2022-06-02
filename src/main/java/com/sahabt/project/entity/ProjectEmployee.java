package com.sahabt.project.entity;

import javax.persistence.*;

import lombok.*;

import java.util.Objects;

@Entity
@Table(name="project_employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEmployee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private EmployeeStatus employeeStatus;
	@ManyToOne
	@JoinColumn(name="project_id")
	private Project project;
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ProjectEmployee that = (ProjectEmployee) o;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "ProjectEmployee{" +
				"id=" + id +
				", employeeStatus=" + employeeStatus +
				", project=" + project +
				", employee=" + employee +
				'}';
	}
}
