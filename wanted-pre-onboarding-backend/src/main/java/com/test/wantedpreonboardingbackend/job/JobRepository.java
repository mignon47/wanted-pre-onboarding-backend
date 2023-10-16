package com.test.wantedpreonboardingbackend.job;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
	List<Job> findByCompanyId(String companyId);
}
