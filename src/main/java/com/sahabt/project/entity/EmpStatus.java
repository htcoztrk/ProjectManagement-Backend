package com.sahabt.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="emp_status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpStatus implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpStatus empStatus = (EmpStatus) o;
        return Objects.equals(id, empStatus.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "EmpStatus{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
