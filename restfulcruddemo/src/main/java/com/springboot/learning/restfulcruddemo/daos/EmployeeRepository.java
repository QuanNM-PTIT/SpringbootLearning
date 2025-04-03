package com.springboot.learning.restfulcruddemo.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.learning.restfulcruddemo.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
}
