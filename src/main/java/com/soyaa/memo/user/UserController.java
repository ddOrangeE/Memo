package com.soyaa.memo.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	// 회원가입 화면
	@GetMapping("/signup/view")
	public String signupView() {
		return "user/signup";
	}
}