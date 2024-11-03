package com.shaastra.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shaastra.entities.SolvedProblems;

public interface SolvedProblemsRepository extends JpaRepository<SolvedProblems, Integer> 
{
	 @Query(value = "SELECT * FROM solved_problems WHERE contest_participant_id = :contestParticipantId", nativeQuery = true)
	    List<SolvedProblems> findByContestParticipantId(@Param("contestParticipantId") Integer contestParticipantId);
	
}
