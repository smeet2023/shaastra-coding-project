	package com.shaastra.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter

public class ContestParticipants 
{
	@Id  /* enforce a pattern starting with --> "@" <-- */
	private String sh_id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sh_id", nullable = false)
	private Students student;

    @OneToMany(mappedBy = "contestParticipant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<ContestResults> contestResults = new HashSet<>();

    @OneToMany(mappedBy = "contestParticipant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SolvedProblems> solvedProblems = new HashSet<>();
    
    @ManyToMany(mappedBy = "participants")
    private Set<Contests> contests = new HashSet<>();
}