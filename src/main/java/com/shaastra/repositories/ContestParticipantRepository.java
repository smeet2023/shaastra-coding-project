package com.shaastra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shaastra.entities.ContestParticipants;

@Repository
public interface ContestParticipantRepository extends JpaRepository<ContestParticipants, String> {
    // Additional query methods can be defined here
}
