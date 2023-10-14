package com.test.wantedpreonboardingbackend.member;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member {

	 @Id
	 private String memberId;
	 
	 private String memberPass;
	 private String memberName;
	 private String memberPhone;
	 
}
