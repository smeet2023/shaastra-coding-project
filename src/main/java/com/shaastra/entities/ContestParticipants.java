	package com.shaastra.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer participant_id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sh_id", referencedColumnName = "sh_id" , insertable = false, nullable = false)
//	@JsonIgnore
	private Students student;

    @OneToMany(mappedBy = "contestParticipant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<ContestResults> contestResults = new HashSet<>();

    @OneToMany(mappedBy = "contestParticipant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<SolvedProblems> solvedProblems = new HashSet<>();  /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!111*/
    
    @ManyToMany(mappedBy = "participants")
    @JsonBackReference
    private Set<Contests> contests = new HashSet<>();
}