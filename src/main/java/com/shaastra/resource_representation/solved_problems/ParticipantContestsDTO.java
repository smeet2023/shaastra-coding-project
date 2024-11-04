package com.shaastra.resource_representation.solved_problems;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParticipantContestsDTO 
{
//	 private List<Integer> contest_id;
	 private Integer contest_participant_id;
	 private List<ContestSolvedProblemsDTO> contests;
//	 private List<SolvedProblemDTO> solved_problems;
}
