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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.models.Quiz;
import com.luv2code.springboot.cruddemo.models.dto.QuizDto;
import com.luv2code.springboot.cruddemo.services.QuizService;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

	@Autowired
	private QuizService quizService;

	@PostMapping("/assign")
	public ResponseEntity<Void> assignQuizToStudentsInCourse(
			@RequestBody QuizDto quizDto) {
		quizService.assignQuizToStudentsInCourse(quizDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/updateScore")
	public ResponseEntity<Void> updateQuizScore(
			@RequestParam Long quizId,
			@RequestParam int newScore) {
		quizService.updateQuizScore(quizId, newScore);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/deleteByCourse")
	public ResponseEntity<Void> deleteQuizzesByCourseId(@RequestParam Long courseId) {
		quizService.deleteQuizzesByCourseId(courseId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Quiz>> getAllQuizzes() {
		List<Quiz> courses = quizService.getAllQuizzes();
		return ResponseEntity.ok(courses);
	}

	@GetMapping("/{quizId}")
	public ResponseEntity<Quiz> getQuiz(@PathVariable Long quizId) {
		Quiz quiz = quizService.getQuizById(quizId);
		return ResponseEntity.ok(quiz);
	}
}
