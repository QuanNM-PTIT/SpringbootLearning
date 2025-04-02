package com.springbootlearning.cruddemo.daos;

import java.util.List;

import com.springbootlearning.cruddemo.entities.Student;

public interface StudentDAO {
    void save(Student theStudent);

    Student findById(Integer id);

    List<Student> findAll();

    List<Student> findByLastName(String theLastName);

    void update(Student theStudent);

    void delete(Integer id);

    int deleteAll();
}