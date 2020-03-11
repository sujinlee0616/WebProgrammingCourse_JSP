<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
	String no=request.getParameter("no");
	String pwd=request.getParameter("pwd");
	/* System.out.println("번호:"+no+",비밀번호:"+pwd); */
	
	// DAO 연동 
	BoardDAO dao=new BoardDAO();
	int res=dao.boardDelete(Integer.parseInt(no), pwd);
	
%>

<%=res %>
