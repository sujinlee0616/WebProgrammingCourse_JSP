<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div id="services" class="clear">
			<h2 class="text">내용보기</h2>
		  	<div class="row text-center">
		  		<img src="../freeboard/jsp_board2.png" style="width: 900px; height: 200px;">
		  	</div> 
			<div class="row">
				<table class="table">
					<tr>
						<th class="text-center success" width=20% style="color:black;">번호</th>
						<td class="text-center" width=30% >${vo.no }</td>
						<th class="text-center success" width=20% style="color:black;">작성일</th>
						<td class="text-center" width=30%>
							<fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd"/>
						</td>
					</tr>
					<tr>
						<th class="text-center success" width=20% style="color:black;">이름</th>
						<td class="text-center" width=30%>${vo.name }</td>
						<th class="text-center success" width=20% style="color:black;">조회수</th>
						<td class="text-center" width=30%>${vo.hit }</td>
					</tr>
					<tr>
						<th class="text-center success" width=20% style="color:black;">제목</th>
						<td class="text-left" colspan="3">${vo.subject }</td>
					</tr>
					<tr>
						<td class="text-left" colspan="4" valign="top" height="200">${vo.content }</td>
					</tr>
					<tr>
						<td class="text-right" colspan="4">
							<a href="../freeboard/update.do?no=${vo.no }" class="btn btn-xs btn-success">수정</a>
							<a href="../freeboard/delete.do?no=${vo.no }" class="btn btn-xs btn-info">삭제</a>
							<a href="../freeboard/list.do" class="btn btn-xs btn-warning">목록</a>
						</td>
					</tr>
				</table>
				<div style="height: 20px;"></div>
				<!--  ====================== 댓글 ====================== -->
				<table class="table">
					<c:forEach var="rvo" items="${list }">
						<tr>
							<td class="text-left">
								${rvo.name }&nbsp;<span style="color:#999;">(${rvo.dbday })</span>
							</td>
							<td class="text-right">
								<c:if test="${sessionScope.id==rvo.id }">
									<a href="#" class="btn btn-xs btn-primary">수정</a>
									<a href="#" class="btn btn-xs btn-danger">삭제</a>
								</c:if>
								<a href="#" class="btn btn-xs btn-success">댓글</a>
							</td>
						</tr>
						<tr>
							<td colspan="2" class="text-left" valign="top">
								<pre style="white-space: pre-wrap;">${rvo.msg }</pre> <!-- white-space: 글자가 영역을 벗어나면 밑으로 내려가게 만드는 것 -->
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td class="text-center" colspan="2">
							<a href="#" class="btn btn-xs btn-danger">이전</a>
							${curpage } page / ${totalpage } pages
							<a href="#" class="btn btn-xs btn-danger">다음</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>



				

