package com.shaastra.repositories;

import java.util.List;
import java.util.Set;

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
		    having sp.contest_id = :contestId
		    """, nativeQuery = true)
		List<SolvedProblems> findByContestParticipantIdGroupedByContest(@Param("participantId") Integer participantId , @Param("contestId") Integer contestId);

	@Query(value = "SELECT * FROM solved_problems WHERE contest_participant_id = :contestParticipantId", nativeQuery = true)
	List<SolvedProblems> findByContestParticipantId(@Param("contestParticipantId") Integer contestParticipantId);

	@Query(value ="SELECT contest_problem_id from contest_contest_problem_join_table where contest_id = :contestId and contest_problem_id in :ids " , nativeQuery = true)
	 Set<Integer> isContestProblemsSetForThisContest(@Param("contestId") Integer contestId , @Param("ids") Set<Integer> ids);
	
	
	@Query(value = "SELECT COUNT(*)	from solved_problems sp JOIN contest_participants cp ON sp.contest_participant_id = cp.participant_id JOIN students st ON cp.sh_id  st.sh_id WHERE sp.contest_id = :contestId AND st.batch = :batch" , nativeQuery = true)
	Integer getSuccessfullSolvedProblems(@Param("contestId") Integer contestId , @Param("batch") String batch);
	
	
}
