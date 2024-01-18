package com.luv2code.springboot.cruddemo.services;

import java.util.List;

import com.luv2code.springboot.cruddemo.models.Quiz;
import com.luv2code.springboot.cruddemo.models.dto.QuizDto;

public interface QuizService {

	Quiz createQuiz(Long courseId, String quizName);

	Quiz getQuizById(Long id);

	List<Quiz> getAllQuizzes();

	void deleteQuizzesByCourseId(Long courseId);

	void updateQuizScore(Long quizId, int newScore);

	void assignQuizToStudentsInCourse(QuizDto quizDto);
}
