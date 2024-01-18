package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

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

import com.luv2code.springboot.cruddemo.models.Teacher;
import com.luv2code.springboot.cruddemo.models.dto.TeacherDto;
import com.luv2code.springboot.cruddemo.services.TeacherService;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

	@Autowired
	TeacherService teacherService;

	@GetMapping("/{teacherId}")
	public ResponseEntity<Teacher> getTeacherById(@PathVariable Long teacherId) {
		Teacher teacher = teacherService.getTeacherById(teacherId);
		return ResponseEntity.ok(teacher);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Teacher>> getAllTeachers() {
		List<Teacher> teachers = teacherService.getAllTeachers();
		return ResponseEntity.ok(teachers);
	}

	@PostMapping("/createNewTeacher")
	public ResponseEntity<TeacherDto> createNewTeacher(@RequestBody TeacherDto teacherDto) {
		TeacherDto createdTeacher = teacherService.saveTeacher(teacherDto);
		return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
	}

	@DeleteMapping("/{teacherId}")
	public ResponseEntity<String> deleteTeacher(@PathVariable Long teacherId) {
		teacherService.deleteTeacher(teacherId);
		return ResponseEntity.ok("Teacher deleted successfully");
	}

	@PutMapping("/{teacherId}")
	public ResponseEntity<String> updateTeacherInfoWithDto(
			@PathVariable Long teacherId,
			@RequestBody TeacherDto updatedTeacherDto) {
		teacherService.updateTeacher(teacherId, updatedTeacherDto);
		return ResponseEntity.ok("Teacher information updated successfully");
	}

}
