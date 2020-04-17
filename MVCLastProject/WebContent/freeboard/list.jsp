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
<div class="wrapper row2">
  <div id="services" class="clear">
  	
  	<div class="row text-center">
  		<img src="../freeboard/jsp_board2.png" style="width: 900px; height: 200px;">
  	</div> 
  	<!-- ========================== Start of FREEBOARD ========================== -->
  	<div class="row">
		<table class="table">
  			<tr>
  				<td>
  					<a href="../freeboard/insert.do" class="btn btn-sm btn-danger">새글</a>
  				</td>
  			</tr>
  		</table>
  		<table class="table">
  			<tr class="success">
  				<th width="10%" class="text-center" style="color: black;">번호</th>
  				<th width="45%" class="text-center" style="color: black;">제목</th>
  				<th width="15%" class="text-center" style="color: black;">이름</th>
  				<th width="20%" class="text-center" style="color: black;">작성일</th>
  				<th width="10%" class="text-center" style="color: black;">조회수</th>
  			</tr>
  			<c:forEach var="vo" items="${list }">
  				<tr>
	  				<td width="10%" class="text-center">${vo.no }</td>
	  				<td width="45%">
	  					<a href="../freeboard/detail.do?no=${vo.no }">
		  					${vo.subject } 
							&nbsp;
							<c:if test="${vo.dbday==today }">
								<sup><img src="../freeboard/new.gif"></sup>
							</c:if>
	  					</a>
	  				</td>
	  				<td width="15%" class="text-center">${vo.name }</td>
	  				<td width="20%" class="text-center">
						${vo.dbday }  <!-- 오라클에서 dbday로 형태 이미 변환해서 왔으니까 fmt 쓸 필요 없음. -->
					</td>
	  				<td width="10%" class="text-center">${vo.hit }</td>
  				</tr>
  			</c:forEach>
  		</table>
  	</div>
  	<!-- ========================== End of FREEBOARD ========================== -->
  	<div class="row text-center">
  		<a href="../freeboard/list.do?page=${curpage>1?curpage-1:curpage }" class="btn btn-sm btn-primary">이전</a>
  		${curpage } page / ${totalpage } pages
  		<a href="../freeboard/list.do?page=${curpage<totalpage?curpage+1:curpage }" class="btn btn-sm btn-primary">다음</a>
  	</div>
	
  </div>
</div>
</body>
</html>