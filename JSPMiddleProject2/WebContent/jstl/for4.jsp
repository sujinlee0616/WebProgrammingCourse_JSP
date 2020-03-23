<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String[] subjects={"자바","오라클","JSP","Spring","Kotlin"};
	request.setAttribute("subjects", subjects); /* request에 여러개 담을 수 있다. */
%>
<%-- <c:set var="subjects" value="<%=subjects %>"/> --%>
<!-- 6번 줄 기능 = 8번 줄 기능-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Java 코딩</h2>
	<ul>
		<%
			int i=1;
			for(String sub:subjects) // forEach 구문은 배열 또는 리스트에 사용 가능 
			{
		%>
				<li><%=i%>.<%=sub %></li>
		<%
				i++;
			}
		%>
	</ul>
	
	<h2>JSTL 코딩</h2>
	<!-- JSTL은  $ { } 안에 자바 일반변수를 담을 수 X ==> 위의 Java코딩처럼 일반변수 subjects 쓸 수X.
	     ==> request/session에 담아야. -->
	<ul>
		<c:forEach var="subject_name" items="${subjects }" varStatus="s">
			<li>${s.index+1}. ${subject_name }</li>		
		</c:forEach>
	</ul>
</body>
</html>







