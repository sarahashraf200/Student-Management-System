package com.luv2code.springboot.cruddemo.services;

import java.util.List;

import com.luv2code.springboot.cruddemo.models.Student;
import com.luv2code.springboot.cruddemo.models.dto.CourseDto;

public interface StudentService {

	Student saveStudent(Student student);

	void enrollStudentInCourse(Long studentId, Long courseId);

	List<CourseDto> getCoursesEnrolledByStudent(Long studentId);

	Student getStudentById(Long id);

	List<Student> getAllStudents();

	Student updateStudent(Long id, Student updatedStudent);

	void deleteStudent(Long id);

	Student getStudentWithQuizzes(Long id);
}
