package com.shaastra.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shaastra.entities.ContestResults;

@Repository
public interface ContestResultRepository extends JpaRepository<ContestResults, Integer> 
{
	@Query(value = """
	        SELECT * FROM contest_results cr
	        WHERE cr.contest_id = :contestId
	        AND cr.sh_id = :participantId
	        
	    """, nativeQuery = true)
	    Optional<ContestResults> findByContestIdAndParticipantId(@Param("contestId") Integer contestId, 
	                                                            @Param("participantId") Integer participantId);
	@Query(value = """
	        SELECT * FROM contest_results cr
	        WHERE cr.sh_id = :participantId
	        
	    """, nativeQuery = true)
	Set<ContestResults> getAllResultForThisParticipant(@Param("participantId") Integer participantId);


}
