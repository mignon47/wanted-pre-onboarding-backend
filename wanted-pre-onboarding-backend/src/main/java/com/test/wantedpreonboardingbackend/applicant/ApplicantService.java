package com.test.wantedpreonboardingbackend.applicant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    public void save(Applicant applicant) {
        applicantRepository.save(applicant);
    }
}
