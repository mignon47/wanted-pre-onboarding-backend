package com.test.wantedpreonboardingbackend.job;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobId;
	
	
	private String companyId;
	private String companyName;
	private String jobPosition;
	private int jobRewards;
	private String jobDescription;
	private String jobTechnology;
}
