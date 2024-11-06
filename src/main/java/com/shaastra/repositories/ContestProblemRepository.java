package com.shaastra.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shaastra.entities.ContestProblem;

@Repository
public interface ContestProblemRepository extends JpaRepository<ContestProblem, Integer> 
{
	@Query(value = "SELECT contest_problem_id FROM contest_problem" , nativeQuery = true)
	Set<Integer> findAllByJustId();
}
