package com.soyaa.memo.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.soyaa.memo.post.model.Post;

@Repository
public interface PostDAO {

	// 메모 작성하는 기능
	public int insertPost(
			@Param("userId") int userId
			, @Param("title") String title
			, @Param("content") String content);
	
	// 로그인한 사용자의 메모 리스트를 얻어오는 기능
	public List<Post> selectPostList(
			@Param("userId") int userId);
	
	// id와 일치하는 하나의 메모 얻어오는 기능
	public Post selectPost(@Param("id") int id);
	
	
}
