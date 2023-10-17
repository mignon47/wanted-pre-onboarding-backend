package com.test.wantedpreonboardingbackend.applicant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.wantedpreonboardingbackend.job.Job;
import com.test.wantedpreonboardingbackend.job.JobRepository;


@Controller
@RequestMapping("/applicant")
public class ApplicantController {

	private final ApplicantService applicantService;
	@Autowired
    private JobRepository jobRepository;
	
	public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }
	
	@PostMapping("/apply")
	public String applyForJob(@ModelAttribute Applicant applicant, Model model) {
	    applicantService.save(applicant);
	    List<Job> jobs = jobRepository.findAll();

        // 모델에 추가하기
        model.addAttribute("jobList", jobs);
	    return "job_list";  // 지원 후 리다이렉트할 페이지 경로입니다.
	}
}
