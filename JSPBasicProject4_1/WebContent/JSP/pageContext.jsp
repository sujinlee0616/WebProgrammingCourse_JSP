<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<style type="text/css">
.container{
	border: 1px solid black;
	width: 960px;
	height: 760px;
}
.header{
	border: 1px solid red;
	width: 960px;
	height: 120px;
}
.footer{
	border: 1px solid blue;
	width: 960px;
	height: 120px;
}
.content{
	border: 1px solid green;
	width: 960px;
	height: 520px;
}
.col-sm-3{
	border: 1px solid orange;
}
.col-sm-9{
	border: 1px solid magenta;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row header">
			<%
				pageContext.include("header.jsp");
				// <jsp:include page="header.jsp"> ==> requset를 공유
				// 헤더에 정보를 보내주고 싶으면, header.jsp가 아니라 pageContext.jsp에 정보를 보내야 한다. 
				// 화면에는 pageContext.jsp가 나와야 헤더/컨텐츠/사이드바/푸터 다 나오기 때문.
				// ex) header에 로그인 UI가 있고, id를 보내고 싶음
				// ==> header.jsp가 아니라 pageContext.jsp에 id=admin을 보냄
				// ==> pageContext.jsp는 header.jsp와 request를 공유하므로 header.jsp도 id 정보를 받을 수 있음. 
				// ==> 만약, header.jsp에 정보를 보내면 화면에 헤더만 나와서 안 됨 (컨텐츠/사이드바/푸터가 없어짐ㅠ)
			%>
		</div>
		<div class="row content">
			<div class="col-sm-3">
				<%
					pageContext.include("side.jsp");
				%>
			</div>
			<div class="col-sm-9">
				<%
					pageContext.include("content.jsp");
				%>
			</div>
		</div>
		<div class="row footer">
			<%
				pageContext.include("footer.jsp");
			%>
		</div>
	</div>
</body>
</html>

<%-- 

	<내장객체 - 6. pageContext 객체>
	 - pageContext 기본 객체를 직접 사용하는 경우는 드물다. 
	
	1. 화면을 조립/이동하되, ★★★request를 유지★★★할 수 있게 해줌.
	    화면조립: include()
	    화면이동: forward()
	  ex) http://localhost/JSPBasicProject4_1/JSP/pageContext.jsp?id=admin&id1=hong&id2=kim
	  	  ==> pageContext에 id/id1/id2 정보를 보냈지만 header,content,footer에도 request가 공유되는 것을 확인할 수 있다. 

	[참고] 화면이동
	 - 각 언어에서 화면을 이동하는 방식은 아래와 같다. 
	 - HTML: <a>, <form action="">
	 - JavaScript: location.href (ajax에서 많이 사용) 
	 - Java: sendRedirect(), forward()
	    ┗ sendRedirect()★: request 초기화O (request 유지X)★
	    ┗ forward()★: request 초기화X (requset 유지O)★
	      ┗ 화면은 바뀌지만 파일은 바뀌지 않음 ==> 이전 화면의 request를 그대로 간직하고 있음.
	        ex) 이전에 게임만들 때, 로그인 화면에서 대기실 화면으로 화면은 바뀌어도, 화면만 바뀌었을 뿐 정보는 그대로 가지고 있음.  
	 
	 1) include
	     
	2. 아래 두 개가 같은 코드... 위처럼 내장객체를 쓰는 게 더 편하다보니 pageContext 잘 쓰지 않음. 
	  String name=requset.getParameter("name");
	  String name=pageContext.getRequest.getParameter("name");


 --%>
 
 
 
 
 
 
 