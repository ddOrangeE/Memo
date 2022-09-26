package com.soyaa.memo.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class PermissionInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request
			, HttpServletResponse response
			, Object handler) throws IOException {
		
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId"); // int 가 null을 저장할 수 없는 상태이기 때문에 에러가 났다. null 을 저장할 수 있는 Integer 로 저장
		
		// /user/signin/view
		String uri = request.getRequestURI();
		
		
		// 로그인이 되어 있는 경우
		// 로그인 페이지, 회원가입 페이지를 접속하면
		// 메모 리스트 페이지로 이동
		
		if(userId != null) {
			
			// 회원가입 /user/signup/view
			// 로그인 /user/signin/view
			
			if(uri.startsWith("/user")) {
				response.sendRedirect("/post/list/view");
				return false;  // 지금 하던 거 중단하고 위에 코드 수행해라
			}
		} else {
			// 로그인이 되어 있지 않은 경우
			// 리스트 페이지, 입력화면, 디테일 화면 접속시도 하면
			// 로그인 페이지로 이동
			
			if(uri.startsWith("/post")) {
				response.sendRedirect("/user/signin/view");
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public void postHandle(
			HttpServletRequest request
			, HttpServletResponse response
			, Object handler
			, ModelAndView modelAndView) {  // ModelAndView : Controller 랑 jsp 연결할 때 쓰는 model (Controller의 model값 + jsp 경로) : response 가 완성이 안된 상태이기 때문에 여기서 조작하면 결과를 바꿀 수 있다
		
	}
	
	@Override
	public void afterCompletion(
			HttpServletRequest request
			, HttpServletResponse response
			, Object handler
			, Exception ex) {
		
	}
	

}
