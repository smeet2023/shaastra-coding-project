package com.shaastra.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shaastra.entities.Contests;

@Repository
public interface ContestRepository extends JpaRepository<Contests , Integer> 
{
	@Query(value = "SELECT 1 from contests c WHERE c.contest_id = :contestId AND c.status = 'Closed'" , nativeQuery = true)
	Optional<Integer> isThisContestCompleted(@Param("contestId") Integer contestId);
}
