package com.shaastra.entities;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@NoArgsConstructor
@Getter
@Setter

public class Contests 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer contest_id;

	private Integer total_participants;
	
	private String status;
	
	private String contest_description; // this could a part of an html page
	
	@Column(unique = true)
	private String contest_link;
	
	private OffsetDateTime contest_date;
	
	@ManyToMany
    @JoinTable(
        name = "contest_contest_participant_join_table",
        joinColumns = @JoinColumn(name = "contest_id"),
        inverseJoinColumns = @JoinColumn(name = "sh_id")
    )
//	@JsonIgnoreProperties("contests")
//	@JsonManagedReference
    private Set<ContestParticipants> participants = new HashSet<>();
	
	@ManyToMany
    @JoinTable(
        name = "contest_contest_problem_join_table",
        joinColumns = @JoinColumn(name = "contest_id"),
        inverseJoinColumns = @JoinColumn(name = "contest_problem_id")
    )
	@JsonManagedReference
    private Set<ContestProblem> contestProblems;
}
