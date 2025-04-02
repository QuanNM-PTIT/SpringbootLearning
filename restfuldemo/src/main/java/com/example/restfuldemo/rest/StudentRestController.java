package com.example.restfuldemo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.restfuldemo.entities.Student;
import com.example.restfuldemo.error.StudentErrorResponse;
import com.example.restfuldemo.error.StudentNotFoundException;

import jakarta.annotation.PostConstruct;

@RestController
public class StudentRestController {

    private List<Student> students;

    public StudentRestController() {

    }
    
    @PostConstruct
    public void loadData() {
        students = new ArrayList<>();
        students.add(new Student("Quan", "Nguyen"));
        students.add(new Student("Phuong", "Mai"));
        students.add(new Student("Huy", "Nguyen"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        if (studentId >= students.size() || studentId < 0) {
            throw new StudentNotFoundException("Student with id " + studentId + " not found!");
        }

        return students.get(studentId);
    }
}
