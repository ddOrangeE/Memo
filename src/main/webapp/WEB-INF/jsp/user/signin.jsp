<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메모 - 로그인</title>

	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	     
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>
	<div class="container">
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
		<section class="contents d-flex justify-content-center">
			<div class="join-box my-5">
				<form id="loginForm">
					<input type="text" placeholder="Username" class="form-control mt-3" id="loginIdInput">
					<input type="password" placeholder="비밀번호" class="form-control mt-2" id="passwordInput">
					<button type="submit" class="btn btn-secondary btn-block mt-3">로그인</button>
				</form>
				<div class="text-center mt-3">
					<a href="/user/signup/view">회원가입</a>
				</div>
			</div>
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp" />
	</div>
	
	<script>
		$(document).ready(function() {
			// $("#loginBtn").on("click", function() {
			
			$("#loginForm").on("submit", function(e) {
				
				// 이벤트에는 원래 파라미터 e 를 전달 받는다 (객체)
				// 필요할 때 추가해서 쓰면 된다
				e.preventDefault(); // 이벤트가 가진 기본 속성을 다 날려 버리는 것   	
				// submit 은 기본적으로 페이지 이동을 가지고 있다  
				// 이걸 막기 위해!!
			
				// 기본속성을 없애면 submit 이라고 해서 페이지 이동을 막기위해 return false 를 해줄 필요가 없다!
				
				let loginId = $("#loginIdInput").val();
				let password = $("#passwordInput").val();
				
				// 벨리데이션
				if(loginId == "") {
					alert("아이디를 입력해주세요");
					return;   
				}
				
				if(password == "") {
					alert("비밀번호를 입력해주세요");
					return;
				}
				
				$.ajax({
					type:"post"
					, url:"/user/signin"
					, data:{"loginId":loginId, "password":password}
					, success:function(data) {
						
						if(data.result == "success") {
							location.href = "/post/list/view";
						} else {
							alert("아이디/비밀번호를 확인하세요");
						}
					}
					, error: function() {
						alert("로그인 에러");
					}
				});
				
			});
			
		});
	</script>
</body>
</html>