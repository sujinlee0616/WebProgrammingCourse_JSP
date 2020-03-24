<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*,com.sist.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
	BoardModel model=new BoardModel();
	model.boardUpdate(request);
	/* MVC에서는 이 코드가 Controller 안에 들어간다. */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- require 쓰기 위해서 HTML 5 버젼 쓰자 ==> head 부분을 HTML5 양식으로 고쳤음 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
	margin: 0 auto;
	width: 600px;
}
h2 {
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<h2>수정하기</h2>
		<div class="row">
			<form method=post action="update_ok.jsp"> 
			<!-- action: update_ok.jsp ==> form에서 보내주는 데이터를  update_ok.jsp에서 받아서 DB 처리 -->
				<table class="table table-hover">
					<tr>
						<th width=20% class="text-right success">이름</th>
						<td width=80%>
							<input type="text" name="name" size=15 value="${vo.name }" required />
							<!-- 데이터 숨겨서 보내기 (update_ok.jsp한테 줄 데이터) -->
							<input type="hidden" name=no value="${vo.no }">
							<%-- <input type="hidden" name=page value="${vo.curpage }"> --%>
							<!-- post방식으로 보내주니까 get방식처럼 url에 싣어다 정보 보낼 수 X ==> hidden으로 숨겨서 보낸다  -->
						</td>
					</tr>
					<tr>
						<th width=20% class="text-right success">제목</th>
						<td width=80%>
							<input type="text" name="subject" size=50 value="${vo.subject }" required />
						</td>
					</tr>
					<tr>
						<th width=20% class="text-right success">내용</th>
						<td width=80%>
							<textarea rows="8" cols="50" name="content" required>${vo.content }</textarea>
							<!-- input은 value로 주고, textarea는 태그 사이에 값 준다. -->
						</td>
					</tr>
					<tr>
						<th width=20% class="text-right success">비밀번호</th>
						<td width=80%>
							<input type="password" name="pwd" size=10 value="${vo.pwd }" required>
						</td>
					</tr>
					<tr>
						<td class="text-center" colspan="2">
							<input type="submit" value="수정" class="btn btn-sm btn-primary">
							<input type="button" value="취소" class="btn btn-sm btn-danger"
							onclick="javascript:history.back()">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>




