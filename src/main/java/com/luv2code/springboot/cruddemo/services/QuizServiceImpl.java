package com.luv2code.springboot.cruddemo.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.cruddemo.dao.CourseRepository;
import com.luv2code.springboot.cruddemo.dao.QuizRepository;
import com.luv2code.springboot.cruddemo.dao.StudentRepository;
import com.luv2code.springboot.cruddemo.models.Course;
import com.luv2code.springboot.cruddemo.models.Quiz;
import com.luv2code.springboot.cruddemo.models.Student;
import com.luv2code.springboot.cruddemo.models.dto.QuizDto;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Quiz createQuiz(Long courseId, String quizName) {
		// Find the course by ID
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new EntityNotFoundException("Course with id " + courseId + " not found"));

		// Create a new quiz
		Quiz quiz = new Quiz();
		quiz.setDescription(quizName);
		quiz.setCourse(course);

		// Save the quiz
		return quizRepository.save(quiz);
	}

	@Override
	public Quiz getQuizById(Long id) {
		return quizRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Quiz with id " + id + " not found"));
	}

	@Override
	public List<Quiz> getAllQuizzes() {
		return quizRepository.findAll();
	}

	@Override
	public void deleteQuizzesByCourseId(Long courseId) {
		// Retrieve quizzes associated with the specified course
		List<Quiz> quizzesToDelete = quizRepository.findByCourseId(courseId);

		// Iterate over each quiz
		for (Quiz quiz : quizzesToDelete) {
			// Remove the quiz from the students' quizzes collection
			for (Student student : quiz.getStudents()) {
				student.getQuizzes().remove(quiz);
			}

			// Clear the quiz's students collection
			quiz.getStudents().clear();

			// Set the course to null (if you want to disassociate the quiz from the course)
			quiz.setCourse(null);
		}

		// Delete the quizzes from the database
		quizRepository.deleteAll(quizzesToDelete);
	}

	@Override
	public void updateQuizScore(Long quizId, int newScore) {
		// Find the quiz by ID
		Quiz quiz = quizRepository.findById(quizId)
				.orElseThrow(() -> new EntityNotFoundException("Quiz with id " + quizId + " not found"));

		// Update the quiz score
		quiz.setScore(newScore);
		quizRepository.save(quiz);
	}

	@Override
	public void assignQuizToStudentsInCourse(QuizDto quizDto) {
		// Find the course by ID
		Course course = courseRepository.findById(quizDto.getCourseId())
				.orElseThrow(() -> new EntityNotFoundException("Course with id " + quizDto.getCourseId() + " not found"));

		// Retrieve students from the course
		List<Student> studentsInCourse = course.getStudents();
		List<Quiz> quizzes = new ArrayList<>();
		for (Student student : studentsInCourse) {
			Quiz quiz = new Quiz();
			quiz.setDescription(quizDto.getDescription());
			quiz.setCourse(course);
			quiz.setScore(0);
			quizzes.add(quiz);
		}
		for (int i = 0; i < studentsInCourse.size(); i++) {
			Student student = studentsInCourse.get(i);
			Quiz quiz = quizzes.get(i);
			student.getQuizzes().add(quiz);
			quiz.getStudents().add(student);
		}

		quizRepository.saveAll(quizzes);

	}
}
