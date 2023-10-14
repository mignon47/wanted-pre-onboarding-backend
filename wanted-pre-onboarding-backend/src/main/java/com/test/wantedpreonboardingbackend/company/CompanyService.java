package com.test.wantedpreonboardingbackend.company;

import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
   }

   public void signUp(Company company) {
       companyRepository.save(company);
   }
}

