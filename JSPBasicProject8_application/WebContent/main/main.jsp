<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 한글변환 -->
<%
	try
	{
		request.setCharacterEncoding("UTF-8");
	}
	catch(Exception ex){}
%>
<%
	String jsp="home.jsp"; // include 되는 페이지 변경을 위한 변수 
	
	String mode=request.getParameter("mode"); // url 뒤의 파라미터로 붙어있는 mode를 가져와서 mode 값에 따라 jsp값 바꿔서 include되는 페이지 바꿈 
	if(mode==null)
		mode="0"; 
	int no=Integer.parseInt(mode);

	switch(no)
	{
	case 0:
		jsp="home.jsp";
		break;
	case 1:
		jsp="movie.jsp";
		break;
	case 2:
		jsp="music.jsp";
		break;
	case 3:
		jsp="find.jsp";
		break;
	case 4:
		jsp="find_ok.jsp"; /* 검색 결과 페이지  */
		break;
	case 5:
		jsp="list.jsp";
		break;
	case 6:
		jsp="insert.jsp"; /* 리스트 글쓰기 페이지  */
		break;
	} 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<%@ include file="menu.jsp" %> 
	<!-- 정적: 메뉴는 변경되지 않으니까... -->

	<div class="container">
		<jsp:include page="<%=jsp %>"></jsp:include>  
		<!-- 동적: include되는 페이지를 바꿀 수 있다. -->   
	</div>
</body>
</html>







