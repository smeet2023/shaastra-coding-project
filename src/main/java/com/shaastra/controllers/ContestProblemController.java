/*package com.shaastra.controllers;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaastra.dto.ContestProblemDTO;
import com.shaastra.entities.ContestProblem;
import com.shaastra.exceptions.ResourceNotFoundException;
import com.shaastra.repositories.ContestProblemRepository;

@RestController
@RequestMapping("/api/shaastra/contest-problems")
public class ContestProblemController {

    private final ContestProblemRepository contestProblemRepository;
    private final ModelMapper modelMapper;

    public ContestProblemController(ContestProblemRepository contestProblemRepository, ModelMapper modelMapper) {
        this.contestProblemRepository = contestProblemRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add-problem")
    public ResponseEntity<ContestProblemDTO> createContestProblem(@RequestBody ContestProblemDTO contestProblemDTO) {
        ContestProblem contestProblem = modelMapper.map(contestProblemDTO, ContestProblem.class);
        ContestProblem savedProblem = contestProblemRepository.save(contestProblem);
        ContestProblemDTO createdContestProblemDTO = modelMapper.map(savedProblem, ContestProblemDTO.class);
        return ResponseEntity.ok(createdContestProblemDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContestProblemDTO> updateContestProblem(@PathVariable Integer id, @RequestBody ContestProblemDTO contestProblemDTO) {
        ContestProblem existingProblem = contestProblemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contest problem not found with ID: " + id));

        // Update fields
        modelMapper.map(contestProblemDTO, existingProblem);
        ContestProblem updatedProblem = contestProblemRepository.save(existingProblem);
        ContestProblemDTO updatedContestProblemDTO = modelMapper.map(updatedProblem, ContestProblemDTO.class);
        return ResponseEntity.ok(updatedContestProblemDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContestProblemDTO> getContestProblemById(@PathVariable Integer id) {
        ContestProblem contestProblem = contestProblemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contest problem not found with ID: " + id));
        ContestProblemDTO contestProblemDTO = modelMapper.map(contestProblem, ContestProblemDTO.class);
        return ResponseEntity.ok(contestProblemDTO);
    }

    @GetMapping
    public ResponseEntity<List<ContestProblemDTO>> getAllContestProblems() {
        List<ContestProblem> contestProblems = contestProblemRepository.findAll();
        List<ContestProblemDTO> contestProblemsDTO = contestProblems.stream()
                .map(problem -> modelMapper.map(problem, ContestProblemDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(contestProblemsDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContestProblem(@PathVariable Integer id) {
        ContestProblem contestProblem = contestProblemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contest problem not found with ID: " + id));
        contestProblemRepository.delete(contestProblem);
        return ResponseEntity.noContent().build();
    }
}
*/
