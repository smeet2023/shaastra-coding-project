package com.shaastra.controllers;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaastra.entities.ContestParticipants;
import com.shaastra.entities.ContestProblem;
import com.shaastra.entities.ContestResults;
import com.shaastra.entities.Contests;
import com.shaastra.exceptions.ApplicationException;
import com.shaastra.exceptions.ResourceNotFoundException;
import com.shaastra.repositories.ContestParticipantRepository;
import com.shaastra.repositories.ContestProblemRepository;
import com.shaastra.repositories.ContestRepository;
import com.shaastra.repositories.ContestResultRepository;
import com.shaastra.resource_representation.contest_result.GetContestWiseScoreForParticularParticipant;
import com.shaastra.resource_representation.contests.AddParticipantToContest;
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
    private final ContestParticipantRepository contestParticipantRepository;
    private final ContestResultRepository contestResultRepository;
    private final ModelMapper modelMapper; // Inject ModelMapper for DTO conversion
    
    public ContestController(ContestResultRepository contestResultRepository , ContestParticipantRepository contestParticipantRepository , ContestRepository contestRepository, ContestProblemRepository contestProblemRepository, ModelMapper modelMapper) 
    {
    	this.contestParticipantRepository = contestParticipantRepository;
    	this.contestRepository = contestRepository;
    	this.contestResultRepository = contestResultRepository;
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

    
    
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateContestDTO> updateContestByFields(@PathVariable Integer id,
                                                                  @RequestBody Map<String, Object> updates)
    {
        // Find the contest by id
        Contests contest = contestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contest with the id : " + id + " could not be found !"));
        
        // Apply updates to the entity (Contests), not the DTO
        updates.forEach((key, value) -> {
        	if(key.equals("contestProblems")) 
        	{
        		// Safely convert value to a Set<Integer>
                Set<Integer> inputProblemIds;
                if (value instanceof List) {
                    inputProblemIds = new HashSet<>((List<Integer>) value);
                } else {
                    inputProblemIds = (Set<Integer>) value;
                }
                
                // Retrieve the corresponding ContestProblem entities
                Set<ContestProblem> matchingProblems = contestProblemRepository.findByContestProblemIds(inputProblemIds);
                
                // Update the contest's contestProblems field with the retrieved entities
                contest.setContestProblems(matchingProblems);
                System.out.println("Contest problems updated successfully");
                
                
        	}
        	else {
        		Field field = ReflectionUtils.findField(Contests.class, key);  // Look for the field in the entity class
        		
        		if (field != null) {
        			field.setAccessible(true);
        			ReflectionUtils.setField(field, contest, value);  // Set value to the entity class, not DTO
        			System.out.println("Field updated successfully: " + key);
        		}
			}
        });
        
        // Save the updated contest entity
        contestRepository.saveAndFlush(contest);
        
        // Map the updated entity to DTO to return in the response
        CreateContestDTO contestDTO = new CreateContestDTO();
        contestDTO.setContest_date(contest.getContest_date());
        contestDTO.setContest_description(contest.getContest_description());
        contestDTO.setContest_link(contest.getContest_link());
        Set<Integer> problemIds = contest.getContestProblems().stream()
        	    .map(cp -> cp.getContest_problem_id())  // Extract contest_problem_id
        	    .collect(Collectors.toSet());  // Collect into a Set

        contestDTO.setContestProblems(problemIds);
        		
        contestDTO.setStatus(contest.getStatus());
        contestDTO.setTotal_participants(contest.getTotal_participants());
        // Return the updated DTO in the response
        return ResponseEntity.ok(contestDTO);
    }

    
    
    @PutMapping("/{id}/add-participants")
    public ResponseEntity<?> addParticipantToContest(@PathVariable Integer id , 
    													@RequestBody AddParticipantToContest participants)
    {
    	Set<ContestParticipants> contestParticipants = contestParticipantRepository.findByContestParticipantsIdsAndNotInContest(participants.getStudentList(), id);
    	
    	Optional<Contests> contest = contestRepository.findById(id);
    	
    	contest.get().setParticipants(contestParticipants);
    	contestRepository.save(contest.get());
    	return ResponseEntity.ok(contest.get());
    }
    
    
    
    @GetMapping("/{contestId}/participants/{participantId}/result")
    public ResponseEntity<?> getContestWiseParticipantScore(@PathVariable Integer contestId , @PathVariable Integer participantId)
    {
    	Contests contests = contestRepository.findById(contestId).orElseThrow(() -> new ResourceNotFoundException("Contest with the id : " + contestId + " Not Found !"));
    	ContestParticipants contestParticipant = contestParticipantRepository.findById(participantId).orElseThrow(() -> new ResourceNotFoundException("Contest Participant with the id : " + participantId + " Not Found !"));
    	
    	Optional<Integer> b = contestParticipantRepository.findByContestParticipantsIdsAndInContest(contests.getContest_id() , contestParticipant.getParticipant_id());
    	
    	if(b.isPresent())
    	{
    		Optional<ContestResults> contestResult = contestResultRepository.findByContestIdAndParticipantId(contestId , participantId);
    		GetContestWiseScoreForParticularParticipant score = this.modelMapper.map(contestResult, GetContestWiseScoreForParticularParticipant.class);
    		return ResponseEntity.ok(score);
    	}
    	else
    		return ResponseEntity.badRequest().body("❗⛔⚠ Participant with id : " + participantId + " Is Not Registered In this Contest !");
    }
    
    
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
    public ResponseEntity<Void> deleteContest(@PathVariable Integer id) 
    {
    	if (!contestRepository.existsById(id)) 
    	{
    		throw new ResourceNotFoundException("Contest with ID ▶" + id + "◀ does not exist.");
    	}
    	    
        contestRepository.deleteById(id);
        return ResponseEntity.noContent().build();
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