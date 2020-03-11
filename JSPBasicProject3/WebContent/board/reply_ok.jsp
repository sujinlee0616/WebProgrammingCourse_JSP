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
	// hidden으로 받은 애 2개
	String pno=request.getParameter("pno"); // 엄마글 번호 
	String strPage=request.getParameter("page");
	
	BoardVO vo = new BoardVO();
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(content);
	vo.setPwd(pwd);
	
	// DAO 연결
	BoardDAO dao=new BoardDAO();
	dao.replyInsert(Integer.parseInt(pno),vo);
	
	// 목록 (list.jsp)으로 이동 (내가 보고 있던 페이지의 목록으로 이동)
	response.sendRedirect("list.jsp?page="+strPage);
	
%>









