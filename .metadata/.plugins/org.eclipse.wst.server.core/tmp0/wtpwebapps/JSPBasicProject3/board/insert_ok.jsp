<!-- ~~_ok.jsp : 값 받아서 DB 연동 시켜주는 애. 화면 출력은 하지 않음. ==> HTML 코드 다 지운다. -->
<%@page import="com.sist.dao.BoardDAO"%>
<%@ page import="com.sist.dao.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 데이터 받기 
	// 한글 변환
	try{
		request.setCharacterEncoding("UTF-8");
	}catch(Exception ex){}

	String name=request.getParameter("name");
	String subject=request.getParameter("subject");
	String content=request.getParameter("content");
	String pwd=request.getParameter("pwd");
	
	BoardVO vo = new BoardVO();
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(content);
	vo.setPwd(pwd);
	
	// DAO 연결
	BoardDAO dao=new BoardDAO();
	dao.boardInsert(vo);
	
	// 이동  => 목록 (list.jsp)
	response.sendRedirect("list.jsp");	
	
%>









