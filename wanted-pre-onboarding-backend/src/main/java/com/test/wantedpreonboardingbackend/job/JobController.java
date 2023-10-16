package com.test.wantedpreonboardingbackend.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/job")
public class JobController {
	
	 @Autowired
	    private JobRepository jobRepository;

	@GetMapping("/job_posting")
    public String jobPostingForm(Model model, HttpSession session) {
    	Job job = new Job();

        String companyId = (String) session.getAttribute("companyId");
        String companyName = (String) session.getAttribute("companyName");

        job.setCompanyId(companyId);
        job.setCompanyName(companyName);

        model.addAttribute("job", job);
        return "job_posting";  
    }
    
    @PostMapping("/job_posting")
    public String jobPostingSubmit(@ModelAttribute("job") Job job, Model model, HttpSession session) {
    	jobRepository.save(job);
    	String companyId = (String) session.getAttribute("companyId");
        model.addAttribute("companyId", companyId);
        List<Job> jobs = jobRepository.findByCompanyId(companyId);
        model.addAttribute("jobList", jobs);
    	return "company_site";
    }
    
    @GetMapping("/{jobId}")
    public String getJobDetail(@PathVariable String jobId, Model model) {
    	Long id = Long.parseLong(jobId);
        Job job = jobRepository.findById(id).orElse(null);
        model.addAttribute("job", job);
        return "job_detail"; 
    }
    @GetMapping("/edit/{jobId}")
    public String editJob(@PathVariable Long jobId, Model model) {
        Job job = jobRepository.findById(jobId).orElse(null);
        if (job == null) {
        } else {
            model.addAttribute("job", job);
        }
        
        return "job_edit"; 
    }
    
    @PostMapping("/update/{jobId}")
    public String updateJob(@PathVariable Long jobId, @ModelAttribute Job updatedJob, RedirectAttributes redirectAttrs, HttpSession session) {
        Job existingJob = jobRepository.findById(jobId).orElse(null);
        
        if (existingJob == null) {
        	session.setAttribute("job", existingJob);
        } else {
            existingJob.setCompanyName(updatedJob.getCompanyName());
            existingJob.setJobPosition(updatedJob.getJobPosition());
            existingJob.setJobRewards(updatedJob.getJobRewards());
            existingJob.setJobDescription(updatedJob.getJobDescription());
            existingJob.setJobTechnology(updatedJob.getJobTechnology());

            jobRepository.save(existingJob);

            redirectAttrs.addFlashAttribute("message", "The job has been successfully updated.");
        }
        
        return "redirect:/job/" + jobId; 
    }

    @PostMapping("/delete/{jobId}")
    public String deleteJob(@PathVariable Long jobId, HttpSession session, RedirectAttributes redirectAttrs, Model model) {
        try {
            jobRepository.deleteById(jobId);
            redirectAttrs.addFlashAttribute("message", "Job has been successfully deleted.");
        } catch (EmptyResultDataAccessException e) {
            redirectAttrs.addFlashAttribute("errorMessage", "Failed to delete the job. It might not exist.");
        }
        
        
        String companyId = (String) session.getAttribute("companyId");
        model.addAttribute("companyId", companyId);
        List<Job> jobs = jobRepository.findByCompanyId(companyId);
        model.addAttribute("jobList", jobs);
        
        return "company_site";  
    }

}
