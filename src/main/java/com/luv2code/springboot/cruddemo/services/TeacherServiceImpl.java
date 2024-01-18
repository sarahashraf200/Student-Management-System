package com.luv2code.springboot.cruddemo.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.cruddemo.dao.CourseRepository;
import com.luv2code.springboot.cruddemo.dao.TeacherRepository;
import com.luv2code.springboot.cruddemo.models.Course;
import com.luv2code.springboot.cruddemo.models.Teacher;
import com.luv2code.springboot.cruddemo.models.dto.TeacherDto;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public TeacherDto saveTeacher(TeacherDto teacherDto) {
		// Create a new teacher
		Teacher teacher = new Teacher();
		teacher.setName(teacherDto.getName());
		teacher.setEmail(teacherDto.getEmail());

		if (teacherDto.getCourseId() != null) {
			// If courseId is provided, fetch the course from the database
			Optional<Course> optionalCourse = courseRepository.findById(teacherDto.getCourseId());

			if (optionalCourse.isPresent()) {
				Course course = optionalCourse.get();
				// Associate the course with the new teacher
				teacher.setCourse(course);
				course.setTeacher(teacher);
			} else {
				// Handle the case where the specified course is not found
				throw new EntityNotFoundException("Course with id " + teacherDto.getCourseId() + " not found");
			}
		}

		// Save the teacher
		teacherRepository.save(teacher);
		return teacherDto.convertTeacherToDto(teacher);
	}

	@Override
	public Teacher getTeacherById(Long id) {
		return teacherRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Teacher with id " + id + " not found"));
	}

	@Override
	public List<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public TeacherDto updateTeacher(Long id, TeacherDto updatedTeacherDto) {
		Teacher teacher = teacherRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Teacher with id " + id + " not found"));

		// Update teacher information from the DTO
		teacher.setName(updatedTeacherDto.getName());
		teacher.setEmail(updatedTeacherDto.getEmail());

		// Fetch the new course from the database by its ID
		if (updatedTeacherDto.getCourseId() != null) {
			Course newCourse = courseRepository.findById(updatedTeacherDto.getCourseId())
					.orElseThrow(() -> new EntityNotFoundException("Course with id " + updatedTeacherDto.getCourseId() + " not found"));

			// Dissociate the teacher from the old course
			if (teacher.getCourse() != null) {
				teacher.getCourse().setTeacher(null);
			}

			// Set the teacher's course to the new course
			teacher.setCourse(newCourse);

			// Update the teacher's reference in the new course
			newCourse.setTeacher(teacher);
		}

		// Save the updated teacher
		teacherRepository.save(teacher);
		return updatedTeacherDto.convertTeacherToDto(teacher);

	}

	@Override
	public void deleteTeacher(Long id) {
		Teacher teacher = teacherRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Teacher with id " + id + " not found"));

		// Fetch the associated course
		Course associatedCourse = teacher.getCourse();

		// Set the teacher to null in the associated course
		if (associatedCourse != null) {
			associatedCourse.setTeacher(null);
			courseRepository.save(associatedCourse); // Update the associated course
		}

		// Delete the teacher
		teacherRepository.delete(teacher);
	}

}
