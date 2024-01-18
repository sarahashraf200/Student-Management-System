package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.luv2code.springboot.cruddemo.models.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

	@Query("SELECT q FROM Quiz q WHERE q.course.id = :courseId")
	List<Quiz> findByCourseId(@Param("courseId") Long courseId);

}
