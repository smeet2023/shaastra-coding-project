package com.shaastra.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class SolvedProblems 
{
	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sp_id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contest_participant_id", nullable = false)
	private ContestParticipants contestParticipant;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contest_id", nullable = false)
	private Contests contest; // Direct reference to Contest
	
	@ManyToOne
    @JoinColumn(name = "contest_problem_id", nullable = false)
    private ContestProblem contestProblem;
	
	@Column(columnDefinition = "INT DEFAULT 0")
    private Integer score;

	
}
