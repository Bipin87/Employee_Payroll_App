package com.capgemini.employeepayrollapplication.controller;

import com.capgemini.employeepayrollapplication.entities.EmployeeEntity;
import com.capgemini.employeepayrollapplication.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String welcome() {
        log.info("App Started - / route.");
        return "Welcome to Employee Payroll App";
    }

    @GetMapping("/all")
    public List<EmployeeEntity> getAllEmployees() {
        log.info("Fetching all employees...");
        List<EmployeeEntity> employees = employeeService.getAllEmployee();
        log.info("Total employees fetched: {}", employees.size());
        return employees;
    }

    @GetMapping("/{id}")
    public Optional<EmployeeEntity> getEmployeeById(@PathVariable Long id) {
        log.info("Fetching employee with ID: {}", id);
        Optional<EmployeeEntity> employee = employeeService.getEmployeeById(id);
        employee.ifPresentOrElse(
                emp -> log.info("Employee found: {}", emp),
                () -> log.warn("Employee with ID {} not found", id)
        );
        return employee;
    }

    @PostMapping("/add")
    public EmployeeEntity addNewEmployee(@Valid @RequestBody EmployeeEntity employee) {
        log.info("Adding new employee: {}", employee);
        EmployeeEntity savedEmployee = employeeService.saveEmployee(employee);
        log.info("Employee added successfully with ID: {}", savedEmployee.getId());
        return savedEmployee;
    }

    @PutMapping("/update/details/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployeeDetails(
            @Valid @PathVariable Long id,@RequestBody EmployeeEntity updatedEmployee) {
        log.info("Updating details of employee with ID: {}", id);
        EmployeeEntity employee = employeeService.updateEmployee(id, updatedEmployee);
        log.info("Details of employee updated: {}", employee);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        log.info("Deleting employee with ID: {}", id);
        if (employeeService.deleteEmployee(id)) {
            log.info("Employee with ID {} deleted successfully.", id);
            return "Employee deleted successfully";
        }
        log.warn("Employee with ID {} not found.", id);
        return "Employee Not Found";
    }
}
