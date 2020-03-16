<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.MovieDAO"></jsp:useBean>
<!-- 이제 Java가 없어지고 다 태그형으로 짜게 될 것...: 책 11장, 12장 내용: Java 없애는 내용. Java와 HTML을 분리. -->
<%
	List<BoardBean> list=dao.boardListData();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>자료실</h2>
	<div class="row">
		<table class="table">
			<tr>
				<td>
					<a href="main.jsp?mode=6" class="btn btn-sm btn-primary">새 글</a>
					<!-- 글쓰기 화면이 main.jsp 안에 있어야 하므로 main.jsp로 보내준다 -->
				</td>
			</tr>
		</table>
		<table class="table table-hover">
			<tr class="warning">
				<th width=10% class="text-center">번호</th>
				<th width=45% class="text-center">제목</th>
				<th width=15% class="text-center">이름</th>
				<th width=20% class="text-center">작성일</th>
				<th width=10% class="text-center">조회수</th>
			</tr>
			<%
				for(BoardBean vo:list)
				{
			%>
				<tr>
					<td width=10% class="text-center"><%=vo.getNo() %></td>
					<td width=45% class="text-left"><%=vo.getSubject() %></td>
					<td width=15% class="text-center"><%=vo.getName() %></td>
					<td width=20% class="text-center"><%=vo.getRegdate() %></td>
					<td width=10% class="text-center"><%=vo.getHit() %></td>
				</tr>
				
			<%
				}
			%>
			
		</table>
	</div>

</body>
</html>





