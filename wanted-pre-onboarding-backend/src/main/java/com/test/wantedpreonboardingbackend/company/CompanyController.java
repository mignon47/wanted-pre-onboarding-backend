package com.test.wantedpreonboardingbackend.company;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/company")
public class CompanyController {

	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
	@Autowired
	private CompanyRepository companyRepo;

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("company", new Company());
        return "signup_company";
    }

    @PostMapping("/signup")
    public String signUp(Company company) {
        companyService.signUp(company);
        return "login";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String companyId, @RequestParam String companyPass, HttpSession session, Model model) {
        Company company = companyRepo.findById(companyId).orElse(null);
        
        if (company == null || !company.getCompanyPass().equals(companyPass)) {
            return "redirect:/login?error";
        }
        
        session.setAttribute("companyId", companyId);
        session.setAttribute("companyName", company.getCompanyName());
        model.addAttribute("companyId", companyId);
        logger.info("Saved companyId in session: {}", session.getAttribute("companyId"));
        return "company_site";
    }
    
    @PostMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/";
	}
    

}
