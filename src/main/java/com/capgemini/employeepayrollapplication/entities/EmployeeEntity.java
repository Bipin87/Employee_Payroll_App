package com.capgemini.employeepayrollapplication.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "employees_payroll")
@Data
public class EmployeeEntity {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "This field can't be null")
    private String name;

    @NotNull(message = "This field can't be null")
    private String department;

    private Double salary; // Changed from `double` to `Double`

    // Default constructor required by JPA
    public EmployeeEntity() {
    }

    // Parameterized constructor
    public EmployeeEntity(String name, String department, Double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

}