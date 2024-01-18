package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.models.Course;
import com.luv2code.springboot.cruddemo.models.dto.CourseDto;
import com.luv2code.springboot.cruddemo.services.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@PostMapping("/createNewCourse")
	public ResponseEntity<Course> createNewCourse(@RequestBody Course course) {
		Course createdCourse = courseService.saveCourse(course);
		return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);

	}

	@GetMapping("/all")
	public ResponseEntity<List<Course>> getAllCourses() {
		List<Course> courses = courseService.getAllCourses();
		return ResponseEntity.ok(courses);
	}

	@GetMapping("/{courseId}")
	public ResponseEntity<Course> getCourse(@PathVariable Long courseId) {
		Course course = courseService.getCourseById(courseId);
		return ResponseEntity.ok(course);
	}

	@DeleteMapping("/{courseId}")
	public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {
		try {
			courseService.deleteCourse(courseId);
			return ResponseEntity.ok("Course deleted successfully");
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting course");
		}
	}
	
	@PutMapping("/{courseId}")
	public ResponseEntity<String> updateTeacherInfoWithDto(
			@PathVariable Long courseId,
			@RequestBody CourseDto updatedCourseDto) {
		courseService.updateCourse(courseId, updatedCourseDto);
		return ResponseEntity.ok("Course information updated successfully");
	}

}
