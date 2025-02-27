package com.capgemini.employeepayrollapplication.service;

import com.capgemini.employeepayrollapplication.entities.EmployeeEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceAsList {

    final List<EmployeeEntity> employeeList = new ArrayList<>();

    Long id = 1L;

    //method to get all employees
    public List<EmployeeEntity> getAllEmployee(){
        return employeeList;
    }

    //method to find employee by id
    public EmployeeEntity getEmployeeById(Long id){
        for(EmployeeEntity employee : employeeList){
            if(employee.getId().equals(id)){
                return employee;
            }
        }
        return null;
    }

    //method to add new employee
    public EmployeeEntity addEmployee(EmployeeEntity employee){
        employeeList.add(employee);
        employee.setId(id++);
        return employee;
    }

    //method to update employees details
    public EmployeeEntity updateEmployee(Long id, EmployeeEntity employee){
        for(EmployeeEntity employee1 : employeeList){
            if(employee1.getId().equals(id)){
                employee1.setName(employee.getName());
                employee1.setDepartment(employee.getDepartment());
                employee1.setSalary(employee.getSalary());
                return employee1;
            }
        }
        return null;
    }

    //method to delete employee
    public boolean deleteEmployee(Long id){
        for(EmployeeEntity employee1 : employeeList){
            if(employee1.getId().equals(id)){
                employeeList.remove(employee1);
                return true;
            }
        }
        return false;
    }
}