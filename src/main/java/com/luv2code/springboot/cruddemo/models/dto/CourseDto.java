package com.luv2code.springboot.cruddemo.models.dto;

import com.luv2code.springboot.cruddemo.models.Course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

	private String courseName;

	private Long teacherId;

	private String teacherName;

	public CourseDto convertCourseToDto(Course course) {
		CourseDto courseDto = new CourseDto();
		courseDto.setCourseName(course.getCourseName());
		courseDto.setTeacherId(course.getTeacher().getId());
		courseDto.setTeacherName(course.getTeacher().getName());
		return courseDto;
	}
}
