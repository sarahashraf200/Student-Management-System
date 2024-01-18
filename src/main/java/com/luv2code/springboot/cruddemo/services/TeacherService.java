package com.luv2code.springboot.cruddemo.services;

import java.util.List;

import com.luv2code.springboot.cruddemo.models.Teacher;
import com.luv2code.springboot.cruddemo.models.dto.TeacherDto;

public interface TeacherService {

	TeacherDto saveTeacher(TeacherDto teacherDto);

	Teacher getTeacherById(Long id);

	List<Teacher> getAllTeachers();

	TeacherDto updateTeacher(Long id, TeacherDto updatedTeacherDto);

	void deleteTeacher(Long id);
}
