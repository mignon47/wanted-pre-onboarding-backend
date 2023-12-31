package com.test.wantedpreonboardingbackend.member;

import org.springframework.stereotype.Service;


@Service
public class MemberService {

	private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
   }

   public void signUp(Member member) {
       memberRepository.save(member);
   }
}
