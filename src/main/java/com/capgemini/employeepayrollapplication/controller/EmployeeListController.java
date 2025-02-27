package com.capgemini.employeepayrollapplication.controller;

import com.capgemini.employeepayrollapplication.entities.EmployeeEntity;
import com.capgemini.employeepayrollapplication.service.EmployeeServiceAsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/list")
public class EmployeeListController {

    @Autowired
    private final EmployeeServiceAsList employeeServiceAsList;

    public EmployeeListController(EmployeeServiceAsList employeeServiceAsList) {
        this.employeeServiceAsList = employeeServiceAsList;
    }

    @PostMapping("/add")
    public EmployeeEntity addEmployee(@RequestBody EmployeeEntity employee){
        return employeeServiceAsList.addEmployee(employee);
    }

    @GetMapping("/all")
    public List<EmployeeEntity> findAllEmployee(){
        return employeeServiceAsList.getAllEmployee();
    }

    @PutMapping("/update/{id}")
    public EmployeeEntity updateEmployee(@PathVariable Long id,@RequestBody EmployeeEntity employee){
        return employeeServiceAsList.updateEmployee(id, employee);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id){
        if(employeeServiceAsList.deleteEmployee(id)){
            return "Employee deleted successfully";
        }
        return "Employee not found";
    }
}