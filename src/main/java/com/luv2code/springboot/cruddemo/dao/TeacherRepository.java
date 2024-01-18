package com.luv2code.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.cruddemo.models.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{

}
