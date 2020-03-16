<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="main.jsp">JSP Action</a>
	    </div>
	    <ul class="nav navbar-nav">
	      <li class="active"><a href="main.jsp">Home</a></li>
	      <li><a href="main.jsp?mode=1">Movie</a></li>
	      <li><a href="main.jsp?mode=2">Music</a></li>
	      <li><a href="main.jsp?mode=3">Find</a></li>
	      <!-- ※ mode=4: 검색 결과 페이지 -->
	      <li><a href="main.jsp?mode=5">게시판</a></li>
	    </ul>
	  </div>
	</nav>
</body>
</html>