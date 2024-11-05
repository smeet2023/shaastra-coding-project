package com.shaastra.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shaastra.entities.SolvedProblems;

public interface SolvedProblemsRepository extends JpaRepository<SolvedProblems, Integer> 
{
	@Query(value = """
		    SELECT sp.*
		    FROM solved_problems sp
		    JOIN contests c ON sp.contest_id = c.contest_id
		    WHERE sp.contest_participant_id = :participantId
		    GROUP BY sp.contest_id, sp.sp_id, sp.score, sp.contest_problem_id, sp.contest_participant_id
		    """, nativeQuery = true)
		List<SolvedProblems> findByContestParticipantIdGroupedByContest(@Param("participantId") Integer participantId);
	
	
	
	 @Query(value = "SELECT * FROM solved_problems WHERE contest_participant_id = :contestParticipantId", nativeQuery = true)
	    List<SolvedProblems> findByContestParticipantId(@Param("contestParticipantId") Integer contestParticipantId);
}
