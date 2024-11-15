package com.shaastra.resource_representation.contest_result;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class GetContestWiseScoreForParticularParticipant 
{
	private Integer contest_id;
	private Integer rank_in_this_contest;
	private Integer score;
	private String status;
}
