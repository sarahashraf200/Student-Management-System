package com.luv2code.springboot.cruddemo.models.dto;

import com.luv2code.springboot.cruddemo.models.Teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto {

	private String name;

	private String email;

	private Long courseId;

	private String courseName;

	public TeacherDto convertTeacherToDto(Teacher teacher) {
		TeacherDto teacherDto = new TeacherDto();
		teacherDto.setName(teacher.getName());
		teacherDto.setEmail(teacher.getEmail());
		teacherDto.setCourseId(teacher.getCourse().getCourseId());
		teacherDto.setCourseName(teacher.getCourse().getCourseName());
		return teacherDto;
	}
}
