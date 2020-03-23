<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
<jsp:useBean id="model" class="com.sist.model.MovieModel"></jsp:useBean>
<!-- 제어문 안 쓸거니까  taglib 쓰지X-->
<%
	model.movieDetailData(request); // request이 vo를 가져옴 (DAO 파일의 movieDetailData에서  return vo했으니까)
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
.row{margin: 0px auto; width: 800px;}
h2{text-align: center;}
</style>
</head>
<body>
	<div class="container">
		<h2>${vo.title } 상세보기</h2>
		<div class="row">
			<table class="table table-hover">
				<tr>
					<td width=30% class="text-center" rowspan="7">
						<img src="${vo.poster }" width="100%">
					</td>
					<td colspan="2">${vo.title }</td>
				</tr>
				<tr>
					<td class="text-right" width="20%">감독</td>
					<td class="text-left" width="50%">${vo.director }</td>
				</tr>
				<tr>
					<td class="text-right" width="20%">출연</td>
					<td class="text-left" width="50%">${vo.actor }</td>
				</tr>
				<tr>
					<td class="text-right" width="20%">장르</td>
					<td class="text-left" width="50%">${vo.genre }</td>
				</tr>
				<tr>
					<td class="text-right" width="20%">등급</td>
					<td class="text-left" width="50%">${vo.grade }</td>
				</tr>
				<tr>
					<td class="text-right" width="20%">시간</td>
					<td class="text-left" width="50%">${vo.time }</td>
				</tr>
				<tr>
					<td class="text-right" width="20%">상영일</td>
					<td class="text-left" width="50%">${vo.regdate }</td>
				</tr>
				<tr>
					<td colspan="3" height="200px" class="text-left" valign="top">${vo.story }</td>
				</tr>
				<tr>
					<td colspan="3" class="text-right">
						<a href="javascript:history.back()" class="btn btn-sm btn-primary">목록</a>
					</td>
				</tr>
			</table>
			
		</div>
	</div>

</body>
</html>





