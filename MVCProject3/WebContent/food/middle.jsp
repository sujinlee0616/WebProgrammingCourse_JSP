<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h1>Middle List</h1>
		
		<div class="row">
			<c:forEach var="vo" items="${list }"> 
			
				<table class="table">
					<tr colspan="2">
						<a href="detail.do?no=${vo.no }"><img src="${vo.image }" width="100px;"></a>
					</tr>
					<tr>
						<td>가게이름</td>
						<a href=""><td><h1>${vo.title }</h1></td></a>
					</tr>
					<tr>
						<td>주소</td>
						<td>${vo.address }</td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td>${vo.tel }</td>
					</tr>
				</table>
			</c:forEach>
		</div>
		
	</div>
</body>
</html>