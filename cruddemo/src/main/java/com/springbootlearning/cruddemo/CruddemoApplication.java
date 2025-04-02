package com.springbootlearning.cruddemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springbootlearning.cruddemo.daos.StudentDAO;
import com.springbootlearning.cruddemo.entities.Student;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			createStudent(studentDAO);
			readStudent(studentDAO);
			readAllStudents(studentDAO);
			readStudentByLastName(studentDAO);
			updateStudent(studentDAO);
			// deleteStudent(studentDAO);
			// deleteAllStudents(studentDAO);
		};
	}

	private void createStudent(StudentDAO studentDAO) {
		Student tempStudent = new Student("Jimmy", "Nguyen", "quannm.ptit@gmail.com");
		studentDAO.save(tempStudent);
		System.out.println("Saved student. Generated ID: " + tempStudent.getId());
	}

	private void readStudent(StudentDAO studentDAO) {
		Student tempStudent = studentDAO.findById(1);
		System.out.println("Found student: " + tempStudent);
	}

	private void readAllStudents(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findAll();
		System.out.println("=========== List of students ===========");
		for (Student student : students) {
			System.out.println(student);
		}
		System.out.println("=========== End of list ===========");
	}

	private void readStudentByLastName(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findByLastName("Nguyen");
		System.out.println("=========== List of students with last name Nguyen ===========");
		for (Student student : students) {
			System.out.println(student);
		}
	}

	private void updateStudent(StudentDAO studentDAO) {
		Student tempStudent = studentDAO.findById(1);
		tempStudent.setFirstName("Quan");
		studentDAO.update(tempStudent);
		System.out.println("Updated student: " + tempStudent);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		studentDAO.delete(1);
		System.out.println("Deleted student with ID 1");
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		int numRowsDeleted = studentDAO.deleteAll();
	}
}
