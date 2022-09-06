package com.soyaa.memo.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.soyaa.memo.common.FileManagerService;
import com.soyaa.memo.post.dao.PostDAO;
import com.soyaa.memo.post.model.Post;

@Service
public class PostBO {

	@Autowired
	private PostDAO postDAO;
	
	// 메모 작성하는 기능
	public int addPost(int userId, String title, String content, MultipartFile file) {
	
		// 파일을 서버에 특정 위치에 저장
		// 해당 파일을 접근할 수 있는 주소 경로를 dao 로 전달한다.
		// (파일을 처리하는 기능을 클래스로 따로 빼서 저장) : bo엔 최대한 간결하게!!
		
		String imagePath = null;
		
		if(file != null) {
			
			imagePath = FileManagerService.saveFile(userId, file);
			
			if(imagePath == null) {
				// 파일 저장 실패
				return 0;  // 또는 -1
			}
		}
		
		return postDAO.insertPost(userId, title, content, imagePath);
	}
	
	// 로그인한 사용자의 메모 리스트를 얻어오는 기능
	public List<Post> getPostList(int userId) {
		return postDAO.selectPostList(userId);
	}
	
	// id와 일치하는 하나의 메모 얻어오는 기능
	public Post getPost(int id) {
		return postDAO.selectPost(id);
	}
	
}
