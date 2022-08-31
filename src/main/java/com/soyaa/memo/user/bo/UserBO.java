package com.soyaa.memo.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soyaa.memo.common.EncryptUtils;
import com.soyaa.memo.user.dao.UserDAO;
import com.soyaa.memo.user.model.User;

@Service
public class UserBO {

	@Autowired
	private UserDAO userDAO;
	
	// 회원정보를 user 테이블에 저장
	public int addUser(
			String loginId
			, String password
			, String name
			, String email) {
		
		// 비밀번호 암호화
//		EncryptUtils utils = new EncryptUtils();
//		String encryptPassword = utils.md5(password);
		
		// static 을 붙여줬으니 객체 생성 필요없음
		String encryptPassword = EncryptUtils.md5(password);
		
		return userDAO.insertUser(loginId, encryptPassword, name, email);
	}
	
	// 아이디 패스워드로 사용자 조회
	// 로그인 해서 사용자 정보 다 긁어올 것이기 때문에 객체로 return 하는 것이 가장 좋겠다.
	// 로그인한 사람 딱 하나만 가지고 오니까 리스트 x
	public User getUser(String loginId, String password) {
		
		String encryptPassword = EncryptUtils.md5(password);
		
		return userDAO.selectUser(loginId, encryptPassword);
	}
}
