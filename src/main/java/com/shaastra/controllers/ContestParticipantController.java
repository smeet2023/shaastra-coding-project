/*package com.shaastra.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaastra.dto.ContestParticipantDTO;
import com.shaastra.dto.ContestParticipantUpdateDTO;
import com.shaastra.entities.ContestParticipants;
import com.shaastra.exceptions.ResourceNotFoundException;
import com.shaastra.repositories.ContestParticipantRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/shaastra/contest-participants")
@Tag(name = "ContestParticipantController", description = "This is ContestParticipantController")
public class ContestParticipantController {

    private final ContestParticipantRepository contestParticipantRepository;
    private final ModelMapper modelMapper;

    public ContestParticipantController(ContestParticipantRepository contestParticipantRepository, ModelMapper modelMapper) {
        this.contestParticipantRepository = contestParticipantRepository;
        this.modelMapper = modelMapper;
    }
    
    

    @PutMapping("/{shId}")
    public ResponseEntity<ContestParticipantUpdateDTO> updateParticipant(
        @PathVariable String shId,
        @RequestBody ContestParticipantUpdateDTO participantDTO) {

        ContestParticipants existingParticipant = contestParticipantRepository.findById(shId)
            .orElseThrow(() -> new ResourceNotFoundException("Contest participant not found with ID: " + shId));
        
        // Update fields of existingParticipant with values from participantDTO
        modelMapper.map(participantDTO, existingParticipant);

        ContestParticipants updatedParticipant = contestParticipantRepository.save(existingParticipant);
        ContestParticipantUpdateDTO updatedParticipantDTO = modelMapper.map(updatedParticipant, ContestParticipantUpdateDTO.class);
        return ResponseEntity.ok(updatedParticipantDTO);
    }
    
    
    
//    		@PostMapping("/basic")
//    		@Operation(summary = "Create new contest participant", description = "some description")
//    		@ApiResponses(value = {
//    				@ApiResponse(responseCode = "200", description = "success"),
//    				@ApiResponse(responseCode = "401", description = "not authorized"),
//    				@ApiResponse(responseCode = "201", description = "new participant created!")
//    		public ResponseEntity<ContestParticipantDTO> addBasicParticipant(@RequestBody ContestParticipantDTO basicDTO) {
//    		    // Retrieve the existing contest based on contest_id
//    		    Contests contest = contestRepository.findById(basicDTO.getContest_id())
//    		        .orElseThrow(() -> new ResourceNotFoundException("Contest not found with ID: " + basicDTO.getContest_id()));
//    		    
//    		    // Create and populate the ContestParticipants entity
//    		    ContestParticipants participant = new ContestParticipants();
//    		    participant.setSh_id(basicDTO.getSh_id());
//    		    participant.setContest(contest); // Set the existing contest reference
//
//    		    // Save and return the response
//    		    ContestParticipants savedParticipant = contestParticipantRepository.save(participant);
//    		    ContestParticipantDTO responseDTO = modelMapper.map(savedParticipant, ContestParticipantDTO.class);
//    		    return ResponseEntity.ok(responseDTO);
//    		}

    @GetMapping("/{shId}")
    public ResponseEntity<ContestParticipantDTO> getParticipantById(@PathVariable String shId) {
    	ContestParticipants participant = contestParticipantRepository.findById(shId)
            .orElseThrow(() -> new ResourceNotFoundException("Contest participant not found with ID: " + shId));
        ContestParticipantDTO participantDTO = modelMapper.map(participant, ContestParticipantDTO.class);
        return ResponseEntity.ok(participantDTO);
    }

    @GetMapping
    public ResponseEntity<List<ContestParticipantDTO>> getAllParticipants() {
        List<ContestParticipants> participants = contestParticipantRepository.findAll();
        participants.get(0).getShId();
        List<ContestParticipantDTO> participantDTOs = participants.stream()
            .map(participant -> modelMapper.map(participant, ContestParticipantDTO.class))
            .collect(Collectors.toList());
        return ResponseEntity.ok(participantDTOs);
        
//    	List<ContestParticipantDTO> participantDTOs = participants.stream().map(participant -> {
//    	    ContestParticipantDTO dto = new ContestParticipantDTO();
//    	    dto.setContest_id(participant.getContest().getId()); // Example for contest ID
//    	    
//    	    // Map shaastra field
//    	    ShaastraResultDTO shaastraDTO = new ShaastraResultDTO();
//    	    if (participant.getShaastra() != null) {
//    	        shaastraDTO.setShId(participant.getShaastra().getSh_id());
//    	    }
//    	    dto.setShaastra(shaastraDTO);
//
//    	    return dto;
//    	}).collect(Collectors.toList());

    }

    @DeleteMapping("/{shId}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable String shId) {
        ContestParticipants participant = contestParticipantRepository.findById(shId)
            .orElseThrow(() -> new ResourceNotFoundException("Contest participant not found with ID: " + shId));
        contestParticipantRepository.delete(participant);
        return ResponseEntity.noContent().build();
    }
}
*/