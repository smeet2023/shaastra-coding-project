package com.shaastra.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaastra.config.UpdateApiResponse;
import com.shaastra.entities.ContestParticipants;
import com.shaastra.entities.Contests;
import com.shaastra.entities.SolvedProblems;
import com.shaastra.repositories.ContestParticipantRepository;
import com.shaastra.repositories.ContestRepository;
import com.shaastra.repositories.SolvedProblemsRepository;

@RestController
@RequestMapping("api/shaastra/contest")

public class SolvedProblemsController 
{
	private SolvedProblemsRepository solvedProblemsRepository;
	private ContestRepository contestRepository; 
//	private ContestProblemRepository contestProblemRepository; 
	private ContestParticipantRepository contestParticipantRepository; 
	
	public SolvedProblemsController(SolvedProblemsRepository solvedProblemsRepository,ContestRepository contestRepository, ContestParticipantRepository contestParticipantRepository)
	{
		this.contestParticipantRepository = contestParticipantRepository;
//		this.contestProblemRepository = contestProblemRepository;
		this.contestRepository = contestRepository;
		this.solvedProblemsRepository = solvedProblemsRepository;
	}
	
	
	
	
	@GetMapping("/participants/{participantId}/solved-problems")
	public ResponseEntity<UpdateApiResponse<List<SolvedProblems>>> getAllSolvedProblems(@PathVariable Integer participantId)
	{
		List<SolvedProblems> solvedProblems = solvedProblemsRepository.findByContestParticipantId(participantId);
		return ResponseEntity.ok(new UpdateApiResponse<>("" , solvedProblems));
	}
	
	
	
	@PostMapping("/{contestId}/participants/{participantId}/solved-problems")
	public ResponseEntity<UpdateApiResponse<List<SolvedProblems>>> addSolvedProblems(
	    @PathVariable Integer contestId,
	    @PathVariable Integer participantId,
	    @RequestBody List<SolvedProblems> solvedProblemsList) 
	{
	    Optional<ContestParticipants> participant = contestParticipantRepository.findById(participantId);
	    
	    if (!participant.isPresent()) 
	    {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
	            new UpdateApiResponse<>("Participant with id : " + participantId + " doesn't exist", null));
	    }

	    Optional<Contests> contest = contestRepository.findById(contestId);
	    if (!contest.isPresent()) 
	    {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
	            new UpdateApiResponse<>("Contest with id : " + contestId + " doesn't exist", null));
	    }

	    if (participant.get().getContests().stream().noneMatch(cont -> cont.getContest_id().equals(contestId))) 
	    {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
	            new UpdateApiResponse<>("Participant with id : " + participantId + " is not registered in this contest", null));
	    }

	    List<SolvedProblems> savedSolvedProblems = new ArrayList<>();

	    for (SolvedProblems solvedProblem : solvedProblemsList) 
	    {
	        // Check if the problem is part of the contest
	        if (contest.get().getContestProblems().stream()
	                .noneMatch(prob -> prob.getContest_problem_id().equals(solvedProblem.getContestProblem().getContest_problem_id()))) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
	                new UpdateApiResponse<>("Contest problem with id : " + solvedProblem.getContestProblem().getContest_problem_id() + " is not part of the contest", null));
	        }

	        // Set the relationships for SolvedProblem
	        solvedProblem.setContestParticipant(participant.get());
	        solvedProblem.setContest(contest.get());

	        // Save each solved problem
	        savedSolvedProblems.add(solvedProblemsRepository.save(solvedProblem));
	    }

	    return ResponseEntity.ok(
	        new UpdateApiResponse<>("Problems solved by this participant were successfully saved", savedSolvedProblems));
	}

}	