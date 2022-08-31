package com.soyaa.memo.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	// 로그인 화면
	@GetMapping("/signin/view")
	public String signinView() {
		return "user/signin";
	}
	
	// 로그아웃 기능
	// api 는 아니기 때문에 restController 가 아닌 Controller 에서 만들거임 (request 객체가 있기 때문에)
	@GetMapping("/signout")
	public String signout(HttpServletRequest request) {
		
		// session 의 값을 지워야한다 - session이 필요하다 - httpservletRequest 객체가 필요하다
		
		HttpSession session = request.getSession();
		
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		
		// 로그인 페이지로 이동 
		// 코드가 중복되면 좋지 않기 때문에 redirect 로 만들어져있는 로그인 페이지로 이동시키기
		return "redirect:/user/signin/view";
	}
}
