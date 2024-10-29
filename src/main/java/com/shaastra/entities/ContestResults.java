package com.shaastra.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ContestResults 
{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contest_id", nullable = false)
    private Contests contest;
    
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sh_id" , nullable = false)
    private ContestParticipants contestParticipant;
//    private ContestParticipants contestParticipant;
    
	@Column(columnDefinition = "INT DEFAULT 0")
	private Integer score;
	
	private Integer rank_in_this_contest;
}
