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
	<div class="clear"></div>
	<div class="text-center">
		<h2 class="text-center">레시피 작성자: ${chef }</h2>
	</div>
	<div class="row">
		<c:forEach var="vo" items="${list }">
			<div class="col-md-3">
			    <div class="thumbnail">
			      <a href="../recipe/recipe_detail.do?no=${vo.no }">  <!-- URI는 .do까지 ==> @RequestMapping은 ? 이하는 인식하지X. -->
			      <!-- ? 이하 파라미터값들은 request에 담김.  -->
			        <img src="${vo.poster }" alt="Lights" style="width:100%">
			        <div class="caption">
			          <p>${vo.title }</p>
			          <p><syb style="color:gray;">${vo.chef }</p>
			        </div>
			      </a>
			    </div>
		  	</div>
		</c:forEach>
	</div>
	<div class="clear"></div>
	<div class="text-center">
		<a href="../recipe/chef_detail.do?name=${chef}&page=${curpage>1?curpage-1:curpage}" class="btn btn-sm btn-primary">이전</a>
			${curpage } page / ${totalpage } pages
		<a href="../recipe/chef_detail.do?name=${chef}&page=${curpage<totalpage?curpage+1:curpage}" class="btn btn-sm btn-primary">다음</a>
	</div>
  </div>
</div>

</body>
</html>