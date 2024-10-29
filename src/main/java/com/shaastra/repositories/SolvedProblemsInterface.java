package com.shaastra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shaastra.entities.SolvedProblems;

public interface SolvedProblemsInterface extends JpaRepository<SolvedProblems, Integer> 
{

}
