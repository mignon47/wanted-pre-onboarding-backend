package com.test.wantedpreonboardingbackend.applicant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Applicant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long applicantId;
	
	private String memberId;
	private String memberName;
	private String memberPhone;
	private Long jobId;
	
}
