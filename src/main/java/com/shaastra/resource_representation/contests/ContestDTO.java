package com.shaastra.resource_representation.contests;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContestDTO 
{
	private Integer contest_id;
	private OffsetDateTime contest_date;
	private String contest_link;
	private String status;
	private String contest_description; // this could a part of an html page
	private Integer total_participants;

}
