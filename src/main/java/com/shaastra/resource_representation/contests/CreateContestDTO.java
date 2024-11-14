package com.shaastra.resource_representation.contests;

import java.time.OffsetDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class CreateContestDTO 
{ 
	@NotEmpty(message = " ❗⛔⚠ contest-problems cannot be empty ")
	private Set<Integer> contestProblems;

	@NotBlank(message = " ❗⛔⚠ contest-link cannot be empty ")
	private String contest_link;
	
	@NotBlank(message = " ❗⛔⚠ status cannot be empty ")
	private String status;

	@NotBlank(message = " ❗⛔⚠ contest-description cannot be empty ")
	private String contest_description; // this could a part of an html page

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
	private OffsetDateTime contest_date;
	
	@Positive(message = " ❗⛔⚠ total_participants cannot be negative ")
	private Integer total_participants;
	
}