<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.MovieDAO"></jsp:useBean>
<%
	List<MovieBean> list=dao.movieFindData(1);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Find</h1>
	<div class="row">
		<table class="table table-hover">
			<tr>
				<td>
					<form method="post" action="main.jsp">   <!-- main.jsp 안에 출력해야 find_ok.jsp가 아니라 main.jsp로 보내야. -->
						<input type="text" name="ss" size="20" class="input-sm">  <!-- ss:Search String -->
						<input type="hidden" name="mode" value="4"> 
						<!-- post 방식으로 보내니까 숨겨서(hidden) 보냈음 -->
						<!-- mode=1: Movie, mode=2: Music, mode=3: Find, mode=4: 검색결과 -->
						<button class="btn btn-sm btn-danger">검색</button>
					</form>
				</td> 
			</tr>
		</table>
		<table class="table table-hover">
			<tr>
				<th></th>
				<th>영화명</th>
				<th>장르</th>
			</tr>
			<%
				for(MovieBean mb:list)
				{
			%>
				<tr>
					<td><img src="<%=mb.getPoster()%>" width=30 height=30></td>
					<td><%=mb.getTitle() %></td>
					<td><%=mb.getGenre() %></td>
				</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>


<!-- 한글 보낼 때, main이 request를 가지고 있기 때문에,  -->