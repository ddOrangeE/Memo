package com.soyaa.memo.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soyaa.memo.post.bo.PostBO;

@RestController
@RequestMapping("/post")
public class PostRestController {

	
	@Autowired
	private PostBO postBO;
	
	// 메모 입력 기능
	@PostMapping("/create")
	public Map<String, String> create(
			@RequestParam("title") String title
			, @RequestParam("content") String content
			, HttpServletRequest request) {  // session 은 어디서든 접근 가능하다 // session 얻으려면 HttpServletRequest 가 필요하다
		
		HttpSession session = request.getSession();
		int userId =  (Integer)session.getAttribute("userId"); // session 에서 값을 꺼내야 하기 때문에 get (key 값을 통해서 얻어옴)
		
		// set 할 때 Object 로 저장했기 때문에 get이 전달하는 것도 Object 객체
		// 즉 downcasting 을 해줘야하고, 어떤 type의 데이터를 얻어올건지 정확히 지정해줘야한다.
		
		int count = postBO.addPost(userId, title, content);
		
		Map<String, String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;		
	}
}
