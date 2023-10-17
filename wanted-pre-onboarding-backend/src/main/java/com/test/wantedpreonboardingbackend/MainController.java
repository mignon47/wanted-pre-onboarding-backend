package com.test.wantedpreonboardingbackend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class MainController {

	@GetMapping("/hello")
	public String main() {
	    return "redirect:/main";
	}
	@GetMapping("/login")
    public String showLoginForm() {
        return "login"; 
    }
	

}
