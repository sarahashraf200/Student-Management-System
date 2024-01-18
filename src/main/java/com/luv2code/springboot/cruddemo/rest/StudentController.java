package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.models.Course;
import com.luv2code.springboot.cruddemo.models.Student;
import com.luv2code.springboot.cruddemo.models.dto.CourseDto;
import com.luv2code.springboot.cruddemo.services.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/createNewStudent")
	public ResponseEntity<Student> createNewStudent(@RequestBody Student student) {
		Student createdStudent = studentService.saveStudent(student);
		return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
	}

	@PostMapping("/{studentId}/enroll/{courseId}")
	public ResponseEntity<String> enrollStudentInCourse(
			@PathVariable Long studentId,
			@PathVariable Long courseId) {
		studentService.enrollStudentInCourse(studentId, courseId);
		return ResponseEntity.ok("Student enrolled in the course successfully");
	}
	
	   @GetMapping("/{studentId}/courses")
	    public ResponseEntity<List<CourseDto>> getCoursesEnrolledByStudent(@PathVariable Long studentId) {
	        List<CourseDto> enrolledCourses = studentService.getCoursesEnrolledByStudent(studentId);
	        return ResponseEntity.ok(enrolledCourses);
	    }
	   
	   @GetMapping("/all")
		public ResponseEntity<List<Student>> getAllStudents() {
			List<Student> students = studentService.getAllStudents();
			return ResponseEntity.ok(students);
		}

		@GetMapping("/{studentId}")
		public ResponseEntity<Student> getStudent(@PathVariable Long studentId) {
			Student student = studentService.getStudentById(studentId);
			return ResponseEntity.ok(student);
		}
	// other CRUD operation endpoints
}