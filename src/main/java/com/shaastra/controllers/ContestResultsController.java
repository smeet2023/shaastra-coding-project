/*package com.shaastra.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaastra.entities.ContestResults;
import com.shaastra.exceptions.ResourceNotFoundException;
import com.shaastra.repositories.ContestResultRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/shaastra/contestresults")
@Tag(name = "ContestResultsController", description = "This is ContestResultsController")


public class ContestResultsController 
{

    private final ContestResultRepository contestResultsRepository;
    private LocalValidatorFactoryBean validator; // Inject Validator

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    public ContestResultsController(LocalValidatorFactoryBean validator,ContestResultRepository contestResultsRepository) 
    {
        this.contestResultsRepository = contestResultsRepository;
        this.validator = validator;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContestResults> getContestResultById(@PathVariable Integer id) 
    {
        Optional<ContestResults> contestResult = contestResultsRepository.findById(id);
        
        if(contestResult.isPresent())
        {
        	return ResponseEntity.ok(contestResult.get());
        }
        else
        {
        	
        }
//                .orElseThrow(() -> new ResourceNotFoundException("Contest result not found with ID: " + id));
    }

    @PostMapping
    public ResponseEntity<ContestResults> createContestResult(@RequestBody ContestResults contestResult) {
        ContestResults savedResult = contestResultsRepository.save(contestResult);
        return ResponseEntity.ok(savedResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContestResults> updateContestResult(@PathVariable Integer id, @RequestBody ContestResults contestResult) {
        ContestResults existingResult = contestResultsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contest result not found with ID: " + id));

        // Update fields of the existing result entity
//        existingResult.setSomeField(contestResult.getSomeField()); // Update as necessary for fields
        // Repeat for other fields

        ContestResults updatedResult = contestResultsRepository.save(existingResult);
        return ResponseEntity.ok(updatedResult);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContestResult(@PathVariable Integer id) {
        ContestResults contestResult = contestResultsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contest result not found with ID: " + id));
        contestResultsRepository.delete(contestResult);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ContestResults>> getAllContestResults() {
        List<ContestResults> contestResultsList = contestResultsRepository.findAll();
        return ResponseEntity.ok(contestResultsList);
    }
}*/
