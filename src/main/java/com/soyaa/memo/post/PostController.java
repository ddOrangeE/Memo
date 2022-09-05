package com.soyaa.memo.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soyaa.memo.post.bo.PostBO;
import com.soyaa.memo.post.model.Post;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostBO postBO;
	
	// 로그인한 사용자의 메모 리스트를 얻어오는 기능
	@GetMapping("/list/view")
	public String list(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		
		int userId = (Integer)session.getAttribute("userId");
		
		List<Post> postList = postBO.getPostList(userId);
		
		model.addAttribute("postList", postList);
		
		return "post/list";
	}
	
	// 입력을 위한 페이지
	@GetMapping("/create/view")
	public String memoInput() {
		return "post/input";
	}
	
	// 게시물 보기 화면
	@GetMapping("/detail/view")
	public String memoDetail(@RequestParam("id") int id, Model model) {
		
		Post post = postBO.getPost(id);
		
		model.addAttribute("post", post);
		
		return "post/detail";
	}
}
