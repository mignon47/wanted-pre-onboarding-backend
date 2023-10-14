package com.test.wantedpreonboardingbackend.company;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyController {

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
        return "redirect:/company/login2";
    }
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // 이 부분은 실제 로그인 폼 뷰의 이름으로 변경해주세요.
    }

}
