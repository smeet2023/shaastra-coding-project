//package com.shaastra.controllers;
//
//import java.util.Optional;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import com.shaastra.config.UpdateApiResponse;
//import com.shaastra.entities.ContestParticipants;
//import com.shaastra.entities.Contests;
//import com.shaastra.entities.SolvedProblems;
//
//public class sample {
//	@PostMapping("/{contestId}/participants/{participantId}/solved-problems")
//	public ResponseEntity<UpdateApiResponse<SolvedProblems>> addSolvedProblems(@PathVariable Integer contestId , @PathVariable Integer participantId , @RequestBody SolvedProblems solvedProblems)
//	{
//	    Optional<ContestParticipants> participant = contestParticipantRepository.findById(participantId);
//	    if(participant.isPresent())
//	    {
//	    	Optional<Contests> contest = contestRepository.findById(contestId);
//	    	if(contest.isPresent())
//	    	{
//	    		if(participant.get().getContests().stream().noneMatch(cont -> cont.getContest_id().equals(contestId)))
////	    				&& contestRepository.findById(solvedProblems.getContestProblem().getContest_problem_id()))
//	    		{
//	    			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UpdateApiResponse<>("Participant with id : " +participantId+ " is not registered in this contest", null));
////	    			throw new IllegalArgumentException("Participant is not registered in this contest");
//	    		}
//	    		if(contest.get().getContestProblems().stream().noneMatch(prob -> prob.getContest_problem_id().equals(solvedProblems.getContestProblem().getContest_problem_id())))
//	    		{
//	    			
//	    		}
//	    		
//	    	}
//	    	else 
//	    	{
//	    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UpdateApiResponse<>("Contest with id : " +contestId+ " doesn't exists", null));
//			}
//	    }
//	    else
//	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UpdateApiResponse<>("Participant with id : " +participantId+ " doesn't exists", null));
//	    
//	    
//	    solvedProblemsRepository.saveAndFlush(solvedProblems);
//	    return ResponseEntity.ok(new UpdateApiResponse<>("Problems solved by this participant is successfully saved", solvedProblems));
//	    
//	}
//}
