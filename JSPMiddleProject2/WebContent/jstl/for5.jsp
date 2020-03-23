<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.vo.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	List<Sawon> list = new ArrayList<Sawon>();  // <%= 사용 할 경우 
	list.add(new Sawon(1,"홍길동","영업부"));
	list.add(new Sawon(2,"심청이","기획부"));
	list.add(new Sawon(3,"춘향이","총무부"));
	list.add(new Sawon(4,"박문수","개발부"));
	list.add(new Sawon(5,"김두한","자재부"));
	
	request.setAttribute("list", list); // ${} 사용 
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>자바 코딩</h2>
	<table border=1>
		<tr>
			<th>사번</th>
			<th>이름</th>
			<th>부서</th>
		</tr>
		<%
			for(Sawon s:list)
			{
		%>
				<tr>
					<td><%=s.getSabun() %></td>
					<td><%=s.getName() %></td>
					<td><%=s.getDept() %></td>
				</tr>
		<%
			}
		%>
	</table>
	
	<h2>JSTL 코딩</h2>
	<table border=1>
		<c:forEach var="s" items="${list }">
			<tr>
				<td>${s.getSabun() }</td>
				<td>${s.sabun }</td>  
					<!-- s.sabun은 사실 s.getSabun()을 호출하는 간편한 방식이다.
						 sabun은 변수가 아니라 실제로는★ VO의 getSabun()을 호출하는 것★이다.
						 ★즉, '{}'은 'getXxx()'를 호출하는 것★  -->
				<td>${s.getName() }</td>
				<td>${s.name }</td>
				<td>${s.getDept() }</td>
				<td>${s.dept }</td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>











