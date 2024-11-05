package com.shaastra.resource_representation.solved_problems;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContestSolvedProblemsDTO 
{
	private Integer contest_id;
	private List<SolvedProblemDTO> listOfSolvedProblems;
}
