package com.luv2code.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.cruddemo.models.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
