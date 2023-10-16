package com.test.wantedpreonboardingbackend.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    
    @Autowired
    private JobRepository jobRepository;

    public List<Job> getJobsByCompanyId(String companyId) {
        return jobRepository.findByCompanyId(companyId);
    }
}