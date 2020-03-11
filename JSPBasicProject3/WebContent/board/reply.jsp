<!-- 사용자에게 보여지는 답변달기 화면 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String no=request.getParameter("no");
	String strPage=request.getParameter("page");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- require 쓰기 위해서 HTML 5 버젼 쓰자 ==> head 부분을 HTML5 양식으로 고쳤음 -->
<link rel="stylesheet" href="../css/bootstrap.min.css">
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
		<h2>답변하기</h2>
		<div class="row">
			<form method=post action="reply_ok.jsp">
			<!-- action: reply_ok.jsp ==> form에서 보내주는 데이터를  reply_ok.jsp에서 받아서 DB 처리 -->
				<table class="table table-hover">
					<tr>
						<th width=20% class="text-right success">이름</th>
						<td width=80%>
							<input type="text" name="name" size=15 required>
							<!-- 데이터 숨겨서 보내기 (reply_ok.jsp한테 줄 데이터) -->
							<input type="hidden" name=pno value="<%=no%>">
							<!-- pno:parentNo. 왜냐면, DAO에서 가져온 번호는, 답글의 엄마 번호임 -->
							<!-- 답글의 no는 DAO에서 가져온 번호가 아님. 
								  답글의 컬럼값은 엄마글의 번호에서 group_id, group_step, group_tab 읽어와서 거기에 1씩 더해줘야 -->
							<!-- ex)        	   no       group_id   group_step   group_tab
							         AAAAAAAA		1			1			0			0
							         	┗ BBBBBB	2			1			1			1
							-->
							<input type="hidden" name=page value="<%=strPage%>">
							<!-- post방식으로 보내주니까 get방식처럼 url에 싣어다 정보 보낼 수 X ==> hidden으로 숨겨서 보낸다  -->
						</td>
					</tr>
					<tr>
						<th width=20% class="text-right success">제목</th>
						<td width=80%>
							<input type="text" name="subject" size=50 required>
						</td>
					</tr>
					<tr>
						<th width=20% class="text-right success">내용</th>
						<td width=80%>
							<textarea rows="8" cols="50" name="content" required></textarea>
						</td>
					</tr>
					<tr>
						<th width=20% class="text-right success">비밀번호</th>
						<td width=80%>
							<input type="password" name="pwd" size=10 required>
						</td>
					</tr>
					<tr>
						<td class="text-center" colspan="2">
							<input type="submit" value="답변하기" class="btn btn-sm btn-primary">
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




