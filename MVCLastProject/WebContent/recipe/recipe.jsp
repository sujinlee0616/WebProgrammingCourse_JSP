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
<!-- ================================== 카테고리 (Image Slide 하단, 3개짜리) ================================== -->
<div class="wrapper row2">
  <div id="services" class="clear"> 
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
    <!-- ========== Start of Pagination ========== -->
    <div class="text-center">
    	<ul class="pagination">
    	  <c:if test="${startPage>1}">
    	  	<li><a href="../recipe/recipe.do?page=${startPage-1 }">&lt;</a></li>
    	  </c:if>
    	  <c:set var="type" value=""/>
    	  <c:forEach var="i" begin="${startPage }" end="${endPage }">
    	  	<c:if test="${curpage==i }">
    	  		<c:set var="type" value="class=active"/>
    	  	</c:if>
    	  	<c:if test="${curpage!=i }">
    	  		<c:set var="type" value=""/>
    	  	</c:if>
    	  	<li ${type }><a href="../recipe/recipe.do?page=${i }">${i }</a></li>
    	  </c:forEach>
    	  <c:if test="${endPage<allPage }">
    	  	<li><a href="../recipe/recipe.do?page=${endPage+1 }">&gt;</a></li>
    	  </c:if>
		</ul>
    </div>
    <!-- ========== End of Pagination ========== -->
  </div>
</div>
<!-- ================================== 최근 본 ================================== -->
<div class="wrapper row6">
  <section id="cta" class="clear"> 
    <div class="three_quarter first">
      <h2 class="heading" style="font-family: 맑은 고딕">최근 본 레시피</h2>
      <p>총 ${count }개의 맛있는 레시피가 있습니다.</p>
    </div>
    <div class="one_quarter"><a class="btn" href="#">Get it now <span class="fa fa-arrow-right"></span></a></div>
  </section>
</div>

<!-- ================================== 최하단 '오늘의 음식점' ================================== -->
<div class="wrapper row2">
  <div class="latest"> 
    <ul class="nospace group">
    	<c:forEach var="vo" items="${rlist }">
	    	<li>
		        <figure><a class="overlay" href="#"><img src="${vo.poster }" alt=""></a>
		          <figcaption class="inspace-30 center">
		            <p class="bold uppercase">${vo.title }</p>
		            <p>${vo.chef }</p>
		            <p><a href="#">View Here &raquo;</a></p>
		          </figcaption>
		        </figure>
	     	</li>
    	</c:forEach>
    </ul>
  </div>
</div>
</body>
</html>