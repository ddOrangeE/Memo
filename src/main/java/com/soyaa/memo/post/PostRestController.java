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
import org.springframework.web.multipart.MultipartFile;

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
			, @RequestParam(value="file", required=false) MultipartFile file  // file은 MultipartFile 이라는 타입으로 저장
			, HttpServletRequest request) {  // session 은 어디서든 접근 가능하다 // session 얻으려면 HttpServletRequest 가 필요하다
		
		// 파일을 저장하고, 파일을 접근할 수 있는 경로 지정, 그 경로를 데이터 베이스에 연결!! 하는 과정을 진행해야한다 -> BO 
		
		HttpSession session = request.getSession();
		int userId =  (Integer)session.getAttribute("userId"); // session 에서 값을 꺼내야 하기 때문에 get (key 값을 통해서 얻어옴)
		
		// set 할 때 Object 로 저장했기 때문에 get이 전달하는 것도 Object 객체
		// 즉 downcasting 을 해줘야하고, 어떤 type의 데이터를 얻어올건지 정확히 지정해줘야한다.
		
		int count = postBO.addPost(userId, title, content, file);
		
		Map<String, String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;		
	}
}
