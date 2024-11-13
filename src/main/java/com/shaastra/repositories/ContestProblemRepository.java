package com.shaastra.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shaastra.entities.ContestProblem;

@Repository
public interface ContestProblemRepository extends JpaRepository<ContestProblem, Integer> 
{
	@Query(value = "SELECT * FROM contest_problem WHERE contest_problem_id IN :ids", nativeQuery = true)
	Set<ContestProblem> findByContestProblemIds(@Param("ids") Set<Integer> ids);

//	@Query(value = "SELECT * FROM contest_problem" , nativeQuery = true)
//	Set<ContestProblem> findAllByJustId();
}
