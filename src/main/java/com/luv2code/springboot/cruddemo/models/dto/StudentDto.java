package com.luv2code.springboot.cruddemo.models.dto;

import com.luv2code.springboot.cruddemo.models.Student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

	String name;

	String email;

	String birthdate;

	public StudentDto convertToDto(Student student) {
		StudentDto studentDto = new StudentDto();
		studentDto.setName(student.getName());
		studentDto.setEmail(student.getEmail());
		studentDto.setBirthdate(student.getBirthday());
		return studentDto;
	}
}
