package com.capgemini.employeepayrollapplication.service;

import com.capgemini.employeepayrollapplication.entities.EmployeeEntity;
import com.capgemini.employeepayrollapplication.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //method to find all employees
    public List<EmployeeEntity> getAllEmployee() {
        return employeeRepository.findAll();
    }

    //method to find employee by ID
    public Optional<EmployeeEntity> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    //method to save new employee to database
    public EmployeeEntity saveEmployee(EmployeeEntity employee) {
        return employeeRepository.save(employee);
    }

    //method to update employee details
    public EmployeeEntity updateEmployee(Long id, EmployeeEntity updatedEmployee) {
        EmployeeEntity existingEmployee = employeeRepository.findById(id)
                .orElse(null);

        // Only update non-null values
        if (updatedEmployee.getName() != null) {
            existingEmployee.setName(updatedEmployee.getName());
        }
        if (updatedEmployee.getDepartment() != null) {
            existingEmployee.setDepartment(updatedEmployee.getDepartment());
        }
        if (updatedEmployee.getSalary() != null) {
            existingEmployee.setSalary(updatedEmployee.getSalary());
        }

        return employeeRepository.save(existingEmployee);
    }

    //method to delete employee
    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}