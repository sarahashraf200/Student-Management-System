package com.luv2code.springboot.cruddemo.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.cruddemo.dao.CourseRepository;
import com.luv2code.springboot.cruddemo.dao.StudentRepository;
import com.luv2code.springboot.cruddemo.dao.TeacherRepository;
import com.luv2code.springboot.cruddemo.models.Course;
import com.luv2code.springboot.cruddemo.models.Student;
import com.luv2code.springboot.cruddemo.models.Teacher;
import com.luv2code.springboot.cruddemo.models.dto.CourseDto;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public Course saveCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public Course getCourseById(Long id) {
		return courseRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Course with id " + id + " not found"));
	}

	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	@Override
	public CourseDto updateCourse(Long id, CourseDto updatedCourseDto) {
		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Course with id " + id + " not found"));

		// Update course information from the DTO
		course.setCourseName(updatedCourseDto.getCourseName());

		if (updatedCourseDto.getTeacherId() != null) {
			// Fetch the new teacher from the database by its ID
			Teacher newTeacher = teacherRepository.findById(updatedCourseDto.getTeacherId())
					.orElseThrow(() -> new EntityNotFoundException("Teacher with id " + updatedCourseDto.getTeacherId() + " not found"));

			course.setTeacher(newTeacher);

			newTeacher.setCourse(course);
		}
		// Save the updated course
		courseRepository.save(course);
		return updatedCourseDto.convertCourseToDto(course);
	}

	@Override
	public void deleteCourse(Long id) {
		   // Find the course by ID
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course with id " + id + " not found"));

        // Disassociate students from the course
        disassociateStudentsFromCourse(course);
        
        // Remove the association with the teacher (optional, depending on your needs)
        course.setTeacher(null);


        // Delete the course
        courseRepository.delete(course);
    }

    private void disassociateStudentsFromCourse(Course course) {
        // Clear the association from students to the course
        for (Student student : course.getStudents()) {
            student.getCourses().remove(course);
        }
        // Clear the association from the course to students
        course.getStudents().clear();
    }
}
