package com.capgemini.employeepayrollapplication.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "Name is required and cannot be empty") // Ensures non-null & not empty
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Employee name not valid")
    private String name;

    @NotNull(message = "This field can't be null")
    private String department;

    @DecimalMin(message = "Salary should be more than 500", value = "500")
    @NotNull
    private Double salary;

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