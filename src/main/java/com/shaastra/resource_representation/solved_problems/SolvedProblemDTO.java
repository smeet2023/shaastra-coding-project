package com.shaastra.resource_representation.solved_problems;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SolvedProblemDTO 
{
	private Integer sp_id;
//	private Integer contest_id;
	private Integer score;
	private Integer contest_problem_id;
}
