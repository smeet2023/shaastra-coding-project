package com.shaastra.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ContestProblem 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer contest_problem_id;
	
	@ManyToMany(mappedBy = "contestProblems")
	@JsonBackReference
	private Set<Contests> contests;

	@Column(nullable = false , unique = true)
	private String problem_title;
	@Column(nullable = false)
	private String problem_description;
	
	@Column(nullable = false , unique = true)
	private String problem_solution;//  possible a pddocument 
	
//	private String problem 
	@Column(nullable = false)
	private String problem_difficulty;
	
	private Set<String> keywords = new HashSet<>();
	
	
//	@ManyToMany
//	@JoinTable(name = "contest_problem_keyword" ,
//				joinColumns = @JoinColumn(name = "contest_problem_id") , 
//				inverseJoinColumns = @JoinColumn(name = "keyword_id"))
//	private Set<KeyWords> keywords = new HashSet<>();
	
}
