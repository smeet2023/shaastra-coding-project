package com.shaastra.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shaastra.entities.ContestParticipants;

@Repository
public interface ContestParticipantRepository extends JpaRepository<ContestParticipants, Integer> 
{
	@Query(value = """
		    SELECT * 
		    FROM contest_participants cp 
		    WHERE cp.participant_id IN :ids 
		      AND NOT EXISTS (
		          SELECT 1 
		          FROM contest_contest_participant_join_table ccpj 
		          WHERE ccpj.sh_id = cp.participant_id 
		            AND ccpj.contest_id = :contestId
		      )
		""", nativeQuery = true)
		Set<ContestParticipants> findByContestParticipantsIdsAndNotInContest(
		    @Param("ids") Set<Integer> ids, 
		    @Param("contestId") Integer contestId);
	
	@Query(value = """
		    SELECT 1
		    FROM contest_contest_participant_join_table
		    WHERE contest_id = :contestId
		    AND sh_id = :participantId
		""", nativeQuery = true)
		Optional<Integer> findByContestParticipantsIdsAndInContest(
		    @Param("contestId") Integer contestId, 
		    @Param("participantId") Integer participantId);
	
	
}
