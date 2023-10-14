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
        return "login"; // 이 부분은 실제 로그인 폼 뷰의 이름으로 변경해주세요.
    }
	

}
