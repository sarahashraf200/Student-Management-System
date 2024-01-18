package com.luv2code.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.cruddemo.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
