package com.springbootlearning.cruddemo.daos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.springbootlearning.cruddemo.entities.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@Transactional
public class StudentDAOImplTest {

    @Autowired
    private StudentDAO studentDAO;

    @PersistenceContext
    private EntityManager entityManager;

    private Student testStudent;

    @BeforeEach
    void setUp() {
        testStudent = new Student("Test", "Student", "test@example.com");
        studentDAO.save(testStudent);
    }

    @Test
    void testSave() {
        Student newStudent = new Student("New", "Student", "new@example.com");
        studentDAO.save(newStudent);
        
        Student savedStudent = studentDAO.findById(newStudent.getId());
        assertNotNull(savedStudent);
        assertEquals("New", savedStudent.getFirstName());
        assertEquals("Student", savedStudent.getLastName());
        assertEquals("new@example.com", savedStudent.getEmail());
    }

    @Test
    void testFindById() {
        Student foundStudent = studentDAO.findById(testStudent.getId());
        assertNotNull(foundStudent);
        assertEquals(testStudent.getFirstName(), foundStudent.getFirstName());
        assertEquals(testStudent.getLastName(), foundStudent.getLastName());
        assertEquals(testStudent.getEmail(), foundStudent.getEmail());
    }

    @Test
    void testFindAll() {
        List<Student> students = studentDAO.findAll();
        assertFalse(students.isEmpty());
        assertTrue(students.contains(testStudent));
    }

    @Test
    void testFindByLastName() {
        List<Student> students = studentDAO.findByLastName("Student");
        assertFalse(students.isEmpty());
        assertTrue(students.stream().allMatch(s -> s.getLastName().equals("Student")));
    }

    @Test
    void testUpdate() {
        testStudent.setFirstName("Updated");
        studentDAO.update(testStudent);
        
        Student updatedStudent = studentDAO.findById(testStudent.getId());
        assertEquals("Updated", updatedStudent.getFirstName());
    }

    @Test
    void testDelete() {
        studentDAO.delete(testStudent.getId());
        Student deletedStudent = studentDAO.findById(testStudent.getId());
        assertNull(deletedStudent);
    }

    @Test
    void testDeleteAll() {
        int deletedCount = studentDAO.deleteAll();
        List<Student> remainingStudents = studentDAO.findAll();
        assertEquals(0, remainingStudents.size());
        assertTrue(deletedCount > 0);
    }
} 