<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.invalidate(); // invalidate: session 전체 삭제
	//session.removeAttribute("name"); // removeAttribute: 지정한 특정 session만 삭제 
	response.sendRedirect("diary.jsp");
%>

<%--
	<Session> 
	 - 서버에 일부 데이터 저장 => 모든 JSP에서 공유할 목적으로 사용
	 - 주요 메소드)
		1) setAttribute(key,Object): 세션 공간에 저장
		2) getAttribute(key,Object): 저장된 값을 읽어올 때 
		3) invalidate(): 전체 session 삭제 
		4) removeAttribyte("key"): 특정 session만 삭제
		5) setMaxInactiveInterval: 저장 기간 설정 
--%>