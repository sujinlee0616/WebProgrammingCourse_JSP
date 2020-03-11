<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 

1. 스크립트릿 : _jspService()안에 들어간다. 일반적. 지역변수 코딩.
	<% 
		일반자바코드 (메소드 안에 코딩되는 내용) 
		ex) int s=10;
			if(s==10){...}
	%>
	
2. 표현식
	<%= 출력물 %> (JSP 방식) ==> out.println(출력물); (Servlet 방식) 
	===========
	   ┗ 이 안에는 세미콜론; 찍지 않는다.
	   
3. 선언식: _jspService 밖에 코딩. 전역변수 or 메소드 코딩. 사용권장X. 이렇게 하지 말고 정 필요하면 java 파일을 따로 빼는게 맞음. 
	<%!
		public int sum(int a,int b)
		{
			return a+b;
		}
	%> 
	

--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		int a=100;
	%>
	<h1><%= a %></h1>
</body>
</html>















