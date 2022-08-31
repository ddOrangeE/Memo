package com.soyaa.memo.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soyaa.memo.user.bo.UserBO;
import com.soyaa.memo.user.model.User;

@RestController // @Controller + @ResponseBody
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	// 회원정보를 입력받고 회원가입을 진행하는 기능
	@PostMapping("/signup")
	public Map<String, String> signup(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, @RequestParam("name") String name
			, @RequestParam("email") String email) {
		
		int count = userBO.addUser(loginId, password, name, email);
		
		// 성공시 {"result":"success"}
		// 실패시 {"result":"fail"}
		
		Map<String, String> result = new HashMap<>();
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
	// 로그인 과정을 진행하는 기능
	@PostMapping("/signin")
	public Map<String, String> singin(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, HttpServletRequest request) { // session을 얻어오기 위해 (왜 session 이 필요하지? 로그인 되었다는 정보를 저장하기 위해)
		
		
		
		
		
		User user = userBO.getUser(loginId, password);
		
		Map<String, String> result = new HashMap<>();
		
		// 해당 객체가 null 인지(fail) 아닌지(success)
		if(user != null) {
			result.put("result", "success");
			
			// 로그인이 성공했을 때 해야하니까 성공했을 때 객체 생성
			
			HttpSession session = request.getSession();    // session은 공간인데 자바에서 마련해줌 애가 return 하는 것은 session 객체

			// 생성한 session을 통해서 저장하고 얻어오고 삭제할 수 있다
			
			// session 에 값 저장하기
			
			// 사용자 이름 저장 (모든 곳에서 저장한 값을 꺼내쓸 수 있다)
			// 값이 있냐 없냐에 따라 로그인 되었는지 안되었는지 알 수 있다.
			session.setAttribute("userId", user.getId()); // user table 의 id 
			session.setAttribute("userName", user.getName());
			
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
}
