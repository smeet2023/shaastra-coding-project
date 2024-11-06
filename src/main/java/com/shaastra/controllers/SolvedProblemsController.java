package com.shaastra.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaastra.entities.SolvedProblems;
import com.shaastra.repositories.ContestParticipantRepository;
import com.shaastra.repositories.ContestRepository;
import com.shaastra.repositories.SolvedProblemsRepository;
import com.shaastra.resource_representation.solved_problems.ContestSolvedProblemsDTO;
import com.shaastra.resource_representation.solved_problems.ParticipantContestsDTO;
import com.shaastra.resource_representation.solved_problems.SolvedProblemDTO;

@RestController
@RequestMapping("api/shaastra/contest")

public class SolvedProblemsController 
{
//	private ContestProblemRepository contestProblemRepository; 
	private SolvedProblemsRepository solvedProblemsRepository;
	private ContestRepository contestRepository; 
	private ContestParticipantRepository contestParticipantRepository; 
	private ModelMapper modelMapper;
	
	public SolvedProblemsController(SolvedProblemsRepository solvedProblemsRepository,
									ContestRepository contestRepository, 
									ContestParticipantRepository contestParticipantRepository,
									ModelMapper modelMapper)
	{
		this.modelMapper = modelMapper;
		this.solvedProblemsRepository = solvedProblemsRepository;
		this.contestRepository = contestRepository;
		this.contestParticipantRepository = contestParticipantRepository;
//		this.contestProblemRepository = contestProblemRepository;
	}
	
	/////////////////////////////////////////////////////////////////////////
	/* To get all solved-problems  solved by a participant in all contests*/
	/////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/participants/{participantId}/solved-problems")
	public ResponseEntity<ParticipantContestsDTO> getAllSolvedProblems(@PathVariable Integer participantId) 
	{
	    // Fetch solved problems for the given participant
	    List<SolvedProblems> solvedProblems = solvedProblemsRepository.findByContestParticipantIdGroupedByContest(participantId);

	    // Group solved problems by contest ID
	    Map<Integer, List<SolvedProblemDTO>> groupedByContest = solvedProblems.stream()
	        .collect(Collectors.groupingBy(sp -> sp.getContest().getContest_id(), 
	            Collectors.mapping(sp -> 
	            {
	                SolvedProblemDTO spDTO = new SolvedProblemDTO();
	                spDTO.setSp_id(sp.getSp_id());
	                spDTO.setScore(sp.getScore());
	                spDTO.setContest_problem_id(sp.getContestProblem().getContest_problem_id());
	                return spDTO;
	            }, Collectors.toList())));

	    // Convert the map to a list of ContestSolvedProblemsDTO
	    List<ContestSolvedProblemsDTO> contestSolvedProblemsList = groupedByContest.entrySet().stream()
	        .map(entry -> 
	        {
	            ContestSolvedProblemsDTO contestDTO = new ContestSolvedProblemsDTO();
	            contestDTO.setContest_id(entry.getKey());
	            contestDTO.setListOfSolvedProblems(entry.getValue());
	            return contestDTO;
	        })
	        .collect(Collectors.toList());

	    // Create the main DTO and set the participant ID and contest list
	    ParticipantContestsDTO participantContestsDTO = new ParticipantContestsDTO();
	    participantContestsDTO.setContest_participant_id(participantId);
	    participantContestsDTO.setContests(contestSolvedProblemsList);

	    // Return the response wrapped in UpdateApiResponse
	    return ResponseEntity.ok(participantContestsDTO);
	}
	
	/////////////////////////////////////////////////////////////////////////
	/*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
	/////////////////////////////////////////////////////////////////////////


//	@GetMapping("/participants/{participantId}/older-V/solved-problems")
//	public ResponseEntity<UpdateApiResponse<List<SolvedProblems>>> olderversionofgetAall(@PathVariable Integer participantId)
//	{
//		List<SolvedProblems> solvedProblems = solvedProblemsRepository.findByContestParticipantId(participantId);
//		return ResponseEntity.ok(new UpdateApiResponse<>("" , solvedProblems));
//	}
//	
	
	/*@PostMapping("/{contestId}/participants/{participantId}/solved-problems")
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
	}*/
	@PostMapping("/{contestId}/participants/{participantId}/solved-problems")
	public SolvedProblems singleParticipantSolvedProblemPost(
			@PathVariable Integer contestId,
			@PathVariable Integer participantId,
			@RequestBody ParticipantContestsDTO singleParticipantPostData) 
	{
		SolvedProblems solvedProblems = this.modelMapper.map(singleParticipantPostData, SolvedProblems.class);
		
		return solvedProblems;
	}
	/*@PostMapping("/{contestId}/participants/{participantId}/solved-problems")
	public ResponseEntity<UpdateApiResponse<List<SolvedProblems>>> singleParticipantSolvedProblemPost(
	    @PathVariable Integer contestId,
	    @PathVariable Integer participantId,
	    @RequestBody ParticipantContestsDTO singleParticipantPostData) 
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

//	    List<SolvedProblems> savedSolvedProblems = new ArrayList<>();

//	    for (ParticipantContestsDTO solvedProblem : solvedProblemsList) 
//	    {
	    	SolvedProblems solvedProblems = new SolvedProblems();
	    	solvedProblems.setContestParticipant(new ContestParticipants().setParticipant_id(participantId));
	    	
	    	ContestSolvedProblemsDTO contestSolvedProblemsDTO = new ContestSolvedProblemsDTO();
	    	
	    	solvedProblem.setContests(new ContestSolvedProblemsDTO().setContest_id(contestId));
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
//	    }

	    return ResponseEntity.ok(
	        new UpdateApiResponse<>("Problems solved by this participant were successfully saved", null));
	}*/
}	