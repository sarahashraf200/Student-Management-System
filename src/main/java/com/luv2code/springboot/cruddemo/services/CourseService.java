package com.luv2code.springboot.cruddemo.services;

import java.util.List;

import com.luv2code.springboot.cruddemo.models.Course;
import com.luv2code.springboot.cruddemo.models.dto.CourseDto;

public interface CourseService {

	Course saveCourse(Course course);

	Course getCourseById(Long id);

	List<Course> getAllCourses();

	CourseDto updateCourse(Long id, CourseDto updatedCourseDto);

	void deleteCourse(Long id);

}
