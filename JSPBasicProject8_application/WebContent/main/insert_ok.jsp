<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.MovieDAO"></jsp:useBean>
<!-- 한글 변환 : insert.jsp는 main.jsp로 request하는게 아니라, insert_ok.jsp로 하므로, insert_ok.jsp에다가 한글변환 해줘야. -->
<%
	try
	{
		request.setCharacterEncoding("UTF-8");
	}
	catch(Exception ex){}
%>
<!-- 사용자가 넘겨준 값을 받아서 vo에 집어넣는다 -->
<jsp:useBean id="vo" class="com.sist.dao.BoardBean">
	<jsp:setProperty name="vo" property="*"/>
</jsp:useBean>
<%
	dao.boardInsert(vo);

	// 이동 
	response.sendRedirect("main.jsp?mode=5"); // mode=5: list.jsp
%>


