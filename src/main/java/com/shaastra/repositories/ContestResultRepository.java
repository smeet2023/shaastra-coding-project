package com.shaastra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shaastra.entities.ContestResults;

@Repository
public interface ContestResultRepository extends JpaRepository<ContestResults, Integer> 
{

}