package com.springboot.learning.restfulcruddemo.services;

import java.util.List;

import com.springboot.learning.restfulcruddemo.entities.Employee;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);
}
