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

import com.test.wantedpreonboardingbackend.applicant.Applicant;
import com.test.wantedpreonboardingbackend.company.CompanyController;
import com.test.wantedpreonboardingbackend.job.Job;
import com.test.wantedpreonboardingbackend.job.JobRepository;

import jakarta.servlet.http.HttpServletRequest;
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
        List<Job> jobs = jobRepository.findAll();

        model.addAttribute("jobList", jobs);

        return "job_list";
    }
    
    @GetMapping("/{jobId}")
    public String getJobDetail(@PathVariable Long jobId, Model model, HttpServletRequest request) {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new IllegalArgumentException("Invalid job Id:" + jobId));
        model.addAttribute("job", job);

        HttpSession session = request.getSession();
        String memberId = (String) session.getAttribute("memberId"); 

        if(memberId != null){
            Member member = memberRepo.findById(memberId).orElse(null);  

            if (member != null) {
                Applicant applicant = new Applicant();
                applicant.setMemberId(member.getMemberId());  
                applicant.setMemberName(member.getMemberName()); 
                applicant.setMemberPhone(member.getMemberPhone());
                applicant.setJobId(job.getJobId());

                model.addAttribute("applicant", applicant);
            }
         }
        
        List<Job> otherJobs = jobRepository.findByCompanyId(job.getCompanyId());  // Replace with your method to find Jobs by company name
        model.addAttribute("otherJobs", otherJobs);

         return "member_job_detail"; 
    }

    
    @GetMapping("/search")
    public String searchJobs(@RequestParam("query") String query, Model model) {
        List<Job> jobs = jobRepository.findByJobPositionContainingOrCompanyNameContaining(query, query);
        model.addAttribute("jobList", jobs);  
        return "search_results";
    }

    @GetMapping("/member_site")
    public String showBacksite(HttpSession session, Model model) {
        String memberId = (String) session.getAttribute("memberId");

        if(memberId != null){
            model.addAttribute("memberId", memberId);
         }

         return "member_site";
    }

}
