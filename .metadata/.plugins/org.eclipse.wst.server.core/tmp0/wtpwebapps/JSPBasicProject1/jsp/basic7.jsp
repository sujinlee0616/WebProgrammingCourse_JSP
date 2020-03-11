<!-- config.xml, movie-mapper.xml 실행파일 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.dao.*" %>
<%
	// DAO에서 값 받기
	List<MusicVO> list = MovieDAO.musicAllData();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h2>음악 차트</h2>          
		  <table class="table table-hover">
		    <thead>
		      <tr>
		        <th>Singer</th>
		        <th>Title</th>
		        <th>Poster</th>
		      </tr>
		    </thead>
		    <tbody>
			<%
				for(MusicVO vo:list)
				{
			%>
				<tr>
					<td><%=vo.getSinger() %></td>
					<td><%=vo.getTitle() %></td>
					<td><img width="100px;" src=<%=vo.getPoster() %>></td>
				</tr>      
			<%		
				}
			%>
			</tbody>
	  </table>
	</div>

</body>
</html>







