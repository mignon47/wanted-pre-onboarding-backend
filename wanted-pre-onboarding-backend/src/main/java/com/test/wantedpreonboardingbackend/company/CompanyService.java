package com.test.wantedpreonboardingbackend.company;

import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
   }

   public void signUp(Company company) {
       // 여기서 필요한 로직을 추가하여 회원 가입 처리를 수행합니다.
       // 예를 들면 비밀번호 암호화, 중복 아이디 체크 등의 작업을 수행할 수 있습니다.
       // 이 예시에서는 단순하게 저장만 수행합니다.
       companyRepository.save(company);
   }
}

