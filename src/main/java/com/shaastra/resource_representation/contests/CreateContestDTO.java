package com.shaastra.resource_representation.contests;

import java.time.OffsetDateTime;
import java.util.TreeSet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CreateContestDTO 
{
	private OffsetDateTime contest_date;
	private String contest_link;
	private String status;
	private String contest_description; // this could a part of an html page
	private TreeSet<Integer> contestProblems;
}
