package com.shaastra.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaastra.dto.ContestDTO;
import com.shaastra.dto.ContestProblemDTO;
import com.shaastra.dto.UpdateContestDTO;
import com.shaastra.entities.ContestProblem;
import com.shaastra.entities.Contests;
import com.shaastra.exceptions.ContestNotFoundException;
import com.shaastra.repositories.ContestProblemRepository;
import com.shaastra.repositories.ContestRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/shaastra/contests")
@Tag(name = "ContestController", description = "This is ContestController")
public class ContestController {

    private final ContestRepository contestRepository; // Inject ContestRepository directly
    private final ContestProblemRepository contestProblemRepository; // Inject ContestProblemRepository directly
//    private StudentRepository studentRepository;
    private final ModelMapper modelMapper; // Inject ModelMapper for DTO conversion

    public ContestController(ContestRepository contestRepository, ContestProblemRepository contestProblemRepository, ModelMapper modelMapper) {
        this.contestRepository = contestRepository;
        this.contestProblemRepository = contestProblemRepository;
//        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create-contest")
    public ResponseEntity<ContestDTO> createContest(@RequestBody ContestDTO contestDTO) {
        // Step 1: Create a new Contest entity
        Contests contest = new Contests();
        contest.setContest_description(contestDTO.getContest_description());
        contest.setContest_date(contestDTO.getContest_date());
        contest.setContest_link(contestDTO.getContest_link());

        // Step 2: Initialize a Set to hold contest problems
        Set<ContestProblem> contestProblems = new HashSet<>();

        // Step 3: Handle existing contest problems by their IDs
        if (contestDTO.getExistingContestProblemIds() != null) {
            for (Integer problemId : contestDTO.getExistingContestProblemIds()) {
                // Find existing problems from the repository and add them
                ContestProblem existingProblem = contestProblemRepository.findById(problemId)
                        .orElseThrow(() -> new RuntimeException("ContestProblem not found for ID: " + problemId));
                contestProblems.add(existingProblem);
            }
        }

        // Step 4: Handle new contest problems using ModelMapper
        if (contestDTO.getNewContestProblems() != null) {
            for (ContestProblemDTO problemDTO : contestDTO.getNewContestProblems()) {
                ContestProblem contestProblem = modelMapper.map(problemDTO, ContestProblem.class);
                ContestProblem savedProblem = contestProblemRepository.save(contestProblem);
                contestProblems.add(savedProblem);
            }
        }

        // Step 5: Set the contest problems to the contest
        contest.setContestProblems(contestProblems);
        
        // Step 6: Save the contest
        contestRepository.save(contest);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(contest, ContestDTO.class));
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
    public ResponseEntity<ContestDTO> getContestById(@PathVariable Integer id) {
        Optional<Contests> contest = contestRepository.findById(id);
        return contest.map(value -> ResponseEntity.ok(modelMapper.map(value, ContestDTO.class)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<ContestDTO>> getAllContests() {
        List<Contests> contests = contestRepository.findAll();
        List<ContestDTO> contestDTOs = contests.stream()
                .map(contest -> modelMapper.map(contest, ContestDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(contestDTOs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContest(@PathVariable Integer id) {
        contestRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}