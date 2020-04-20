<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row" style="margin: 0px auto; width:350px; height:500px;">
		<table class="table">
			<c:forEach var="vo" items="${tList }">
				<tr>
					<td>[${vo.tloc }]&nbsp;${vo.tname }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>