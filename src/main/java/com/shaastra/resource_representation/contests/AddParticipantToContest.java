package com.shaastra.resource_representation.contests;

import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class AddParticipantToContest 
{
	private Integer contest_id;
	private Set<Integer> studentList;
}
