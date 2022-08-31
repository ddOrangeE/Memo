<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 위에 태그는 각 jsp 마다 따로 관리됨 --%>

		<header class="d-flex align-items-center justify-content-between">
			<h1 class="ml-3">Memo</h1>
			
			<c:if test="${not empty userId }">
			<%-- session 값 가져오는 법은 el 태그 안에 내가 저장한 키 --%>
			<div class="mr-3">${userName }님 <a href="/user/signout"> 로그아웃 </a></div>
			</c:if>
		</header>