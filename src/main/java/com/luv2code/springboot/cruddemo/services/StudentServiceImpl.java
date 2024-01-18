package com.luv2code.springboot.cruddemo.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.cruddemo.dao.CourseRepository;
import com.luv2code.springboot.cruddemo.dao.QuizRepository;
import com.luv2code.springboot.cruddemo.dao.StudentRepository;
import com.luv2code.springboot.cruddemo.models.Course;
import com.luv2code.springboot.cruddemo.models.Student;
import com.luv2code.springboot.cruddemo.models.dto.CourseDto;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private CourseRepository courseRepository;

	private CourseDto courseDto = new CourseDto();

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	public void enrollStudentInCourse(Long studentId, Long courseId) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new EntityNotFoundException("Student with id " + studentId + " not found"));

		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new EntityNotFoundException("Course with id " + courseId + " not found"));

		// Enroll the student in the course
		student.getCourses().add(course);
		course.getStudents().add(student);

		// Save changes
		studentRepository.save(student);
		courseRepository.save(course);
	}

	@Override
	public List<CourseDto> getCoursesEnrolledByStudent(Long studentId) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new EntityNotFoundException("Student with id " + studentId + " not found"));

		List<CourseDto> courseDtos = student.getCourses()
				.stream()
				.map(courseDto::convertCourseToDto)
				.collect(Collectors.toList());

		return courseDtos;
	}

	@Override
	public Student getStudentById(Long id) {
		return studentRepository.findById(id).orElse(null);
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student updateStudent(Long id, Student updatedStudent) {
		// Perform validation and update logic
		return studentRepository.save(updatedStudent);
	}

	@Override
	public void deleteStudent(Long id) {
		studentRepository.deleteById(id);
	}

	@Override
	public Student getStudentWithQuizzes(Long id) {
		return studentRepository.findById(id)
				.map(student -> {
					// Fetch associated quizzes
					student.getQuizzes().size(); // This will trigger lazy loading
					return student;
				})
				.orElse(null);
	}

}
