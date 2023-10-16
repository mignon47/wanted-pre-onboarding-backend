package com.test.wantedpreonboardingbackend.member;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.wantedpreonboardingbackend.company.CompanyController;
import com.test.wantedpreonboardingbackend.job.Job;
import com.test.wantedpreonboardingbackend.job.JobRepository;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
    private JobRepository jobRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
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
    public String login(@RequestParam String memberId, @RequestParam String memberPass, HttpSession session, Model model) {
        Member member = memberRepo.findById(memberId).orElse(null);
        
        if (member == null || !member.getMemberPass().equals(memberPass)) {
            return "redirect:/login?error";
        }
        session.setAttribute("memberId", memberId);
        model.addAttribute("memberId", memberId);
        logger.info("로그인된 회워세션: {}", session.getAttribute("memberId"));
        return "member_site";
    }
    
    @PostMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/";
	}
    
    @PostMapping("/job_list")
    public String showJobList(Model model) {
        // 데이터베이스에서 모든 채용공고 목록 가져오기
        List<Job> jobs = jobRepository.findAll();

        // 모델에 추가하기
        model.addAttribute("jobList", jobs);

        // 뷰 이름 반환하기
        return "job_list";
    }
    
    @GetMapping("/{jobId}")
    public String getJobDetail(@PathVariable String jobId, Model model) {
    	Long id = Long.parseLong(jobId);
        Job job = jobRepository.findById(id).orElse(null);
        model.addAttribute("job", job);
        return "member_job_detail"; 
    }
    
    @GetMapping("/member_site")
    public String jobPostingSubmit(@ModelAttribute("job") Job job, Model model, HttpSession session) {
    	jobRepository.save(job);
    	String memberId = (String) session.getAttribute("memberId");
        model.addAttribute("memberId", memberId);
    	return "member_site";
    }

}
