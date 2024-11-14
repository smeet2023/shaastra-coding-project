package com.shaastra.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaastra.entities.ContestProblem;
import com.shaastra.entities.Contests;
import com.shaastra.exceptions.ApplicationException;
import com.shaastra.exceptions.ResourceNotFoundException;
import com.shaastra.repositories.ContestProblemRepository;
import com.shaastra.repositories.ContestRepository;
import com.shaastra.resource_representation.contests.ContestDTO;
import com.shaastra.resource_representation.contests.CreateContestDTO;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/shaastra/contests")
@Tag(name = "ContestController", description = "This is ContestController")

public class ContestController 
{

    private final ContestRepository contestRepository; // Inject ContestRepository directly
    private final ContestProblemRepository contestProblemRepository; // Inject ContestProblemRepository directly
    private final ModelMapper modelMapper; // Inject ModelMapper for DTO conversion

    public ContestController(ContestRepository contestRepository, ContestProblemRepository contestProblemRepository, ModelMapper modelMapper) {
        this.contestRepository = contestRepository;
        this.contestProblemRepository = contestProblemRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create-contest")
    public ResponseEntity<ContestDTO> createContest(@Valid @RequestBody CreateContestDTO contestDTO) 
    {

    	/*******************************************************************************/
    	Contests contest = new Contests();
        contest.setContest_description(contestDTO.getContest_description());
        contest.setContest_date(contestDTO.getContest_date());
        contest.setContest_link(contestDTO.getContest_link());
        contest.setStatus(contestDTO.getStatus());
       
        Set<Integer> inputProblems = contestDTO.getContestProblems();
        /*******************************************************************************/
        
        Set<ContestProblem> matchingProblems = contestProblemRepository.findByContestProblemIds(inputProblems);
        
        if (matchingProblems.size() != inputProblems.size()) 
        {
            Set<Integer> foundProblemIds = matchingProblems.stream()
                .map(ContestProblem::getContest_problem_id)
                .collect(Collectors.toSet());
            Set<Integer> missingIds = new HashSet<>(inputProblems);
            missingIds.removeAll(foundProblemIds);

            throw new ResourceNotFoundException("Contest problems with IDs " + missingIds + " could not be found!");
        }
        else
        {
        	contest.setContestProblems(matchingProblems);
        	contestRepository.save(contest);
        	return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(contest, ContestDTO.class));
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<UpdateContestDTO> updateContest(@PathVariable Integer id, @RequestBody UpdateContestDTO updateContestDTO) {
//        Optional<Contests> optionalContest = contestRepository.findById(id);
//
//        if (optionalContest.isPresent()) {
//            Contests contest = optionalContest.get();
//
//            // Check and update fields conditionally
//            if (updateContestDTO.getStatus() != null) {
//                contest.setStatus(updateContestDTO.getStatus());
//            }
//
//            if (updateContestDTO.getContest_description() != null) {
//                contest.setContest_description(updateContestDTO.getContest_description());
//            }
//
//            if (updateContestDTO.getContest_date() != null) {
//                contest.setContest_date(updateContestDTO.getContest_date());
//            }
//
//            if (updateContestDTO.getContest_link() != null) {
//                contest.setContest_link(updateContestDTO.getContest_link());
//            }
//
//            // Save the updated contest entity
//            Contests updatedContest = contestRepository.save(contest);
//
//            // Map back the updated entity to DTO
//            return ResponseEntity.ok(modelMapper.map(updatedContest, UpdateContestDTO.class));
//        } else {
//            // Throw a custom exception if the contest is not found
//            throw new ContestNotFoundException("Contest with ID " + id + " not found");
//        }
//    }

    @GetMapping("/{id}")
    public ResponseEntity<ContestDTO> getContestById(@PathVariable Integer id) 
    {
    	Contests contests = contestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contest with id : " + id + " could not be found!"));
    	return ResponseEntity.ok(this.modelMapper.map(contests, ContestDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<ContestDTO>> getAllContests() 
    {
    	try {
    		List<Contests> contests = contestRepository.findAll();
    		List<ContestDTO> contestDTOs = contests.stream()
    				.map(contest -> modelMapper.map(contest, ContestDTO.class))
    				.collect(Collectors.toList());
    		return ResponseEntity.ok(contestDTOs);
		} 
    	catch (Exception e) {
    		throw new ApplicationException("Failed to retrieve Students!", e);
		}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContest(@PathVariable Integer id) {
        contestRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}