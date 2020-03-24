<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
<%
	BoardModel model=new BoardModel();
	// request 넘겨주자...
	model.boardUpdateData(request, response);	
	
%>