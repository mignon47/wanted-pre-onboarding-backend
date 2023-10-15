package com.test.wantedpreonboardingbackend.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/job")
public class JobController {
	
	 @Autowired
	    private JobRepository jobRepository;

	@GetMapping("/job_posting")
    public String jobPostingForm(Model model, HttpSession session) {
    	Job job = new Job();

        // 세션에서 회사 ID와 이름을 가져옵니다.
        String companyId = (String) session.getAttribute("companyId");
        String companyName = (String) session.getAttribute("companyName");

        // Job 객체에 설정합니다.
        job.setCompanyId(companyId);
        job.setCompanyName(companyName);

        // Model 객체에 추가하여 뷰에서 사용할 수 있게 합니다.
        model.addAttribute("job", job);
        return "job_posting";  // job_posting.html 페이지 표시
    }
    
    @PostMapping("/job_posting")
    public String jobPostingSubmit(@ModelAttribute("job") Job job) {
    	jobRepository.save(job);
    	return "company_site";
    }
}
