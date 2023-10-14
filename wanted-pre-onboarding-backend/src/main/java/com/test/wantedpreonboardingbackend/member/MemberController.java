package com.test.wantedpreonboardingbackend.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberRepository memberRepo;

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    
    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("member", new Member());
        return "signup_member";
    }

    @PostMapping("/signup")
    public String signUp(Member member) {
        memberService.signUp(member);
        return "login";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String memberId, @RequestParam String memberPass, HttpSession session) {
        Member member = memberRepo.findById(memberId).orElse(null);
        
        if (member == null || !member.getMemberPass().equals(memberPass)) {
            return "redirect:/login?error";
        }
        session.setAttribute("memberId", memberId);
        return "redirect:/";
    }
}
