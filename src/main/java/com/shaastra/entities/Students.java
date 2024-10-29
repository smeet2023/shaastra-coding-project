package com.shaastra.entities;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = {"erp_id" , "tp_id" , "personal_email" , "gsuite_email"})
})
public class Students 
{
	@Id
	@NotNull(message = "!! -> erp_id cannot be empty")
//	@Size(max = 10 , message = "!! -> erp_id cannot be more than 10 digits")
	private long erp_id; // 10322

	@Column(nullable = false , columnDefinition = "INT DEFAULT 1")
	private Integer current_year;
	
	@Column(nullable = false)
//	@Pattern(regexp = "^[A-G]$", message = "!! -> Division must be one of A, B, C, D, E, F, or G")
	private String division;
	
	@Column(nullable = false)
	private String batch;      /*partition */
	
	@Column(unique = true)
	private String sh_id;
	
	@Column(nullable = false)
//	@ValidTpId
	private String tp_id;    
	
	
	@Column(nullable = false , length = 70)
//	@Email(regexp = "^[a-zA-Z0-9._%+-]+@example\\.com$" , message = "!! -> Invalid email")
//	@NotNull(message = "!! -> personal email cannot be null")
//	@Size(max = 70 , message = "!! -> too long email")
	private String personal_email;
	
	@Column(nullable = false , length = 70)
//	@Email(regexp = "^[0-9]+@tcetmumbai\\.in$")
//	@NotNull(message = "!! -> GSUITE ID cannot be null")
	private String gsuite_email;    // index based on some digits
	
	@Column(nullable = false)
//	@Pattern(regexp = "\\d{10}" , message = "!! -> phone should be of exactly 10 digits")
	private String phone;
	
	@Column(nullable = false)
	private String branch;       

//	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true , fetch = FetchType.LAZY)
//    private Set<Semester_Record> semesterRecords;
}