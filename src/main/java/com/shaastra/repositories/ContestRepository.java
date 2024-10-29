package com.shaastra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shaastra.entities.Contests;

@Repository
public interface ContestRepository extends JpaRepository<Contests , Integer> 
{

}
