package com.luv2code.springboot.cruddemo.models;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "QUIZ")
public class Quiz {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		@JsonIgnore
	    @Column(name = "ID")
	    private Long id;
  
	    @Column(name = "SCORE")
	    private int score;
	    
	    @Column(name = "QUIZ_DESCRIPTION")
	    private String description;
	    
	    
	    @ManyToOne ( cascade = CascadeType.ALL)
		@JsonIgnore
	    @JoinColumn(name = "course_id")
	    private Course course;

	    @ManyToMany(mappedBy = "quizzes" , cascade = CascadeType.ALL)
	    @JsonIgnore
	    private List<Student> students =  Collections.synchronizedList(new ArrayList<>());
}
