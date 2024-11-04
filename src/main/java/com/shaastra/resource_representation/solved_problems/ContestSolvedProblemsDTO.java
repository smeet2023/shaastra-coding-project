package com.shaastra.resource_representation.solved_problems;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContestSolvedProblemsDTO 
{
	private Integer contest_id;
	private List<SolvedProblemDTO> listOfSolvedProblems;
}
