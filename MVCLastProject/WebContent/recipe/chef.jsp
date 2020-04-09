<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- taglib 없으면 c:if 등 사용 불가.. 잊지 말 것!!-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<style type="text/css">
		.table,td{background-color: white;}
	</style>
</head>
<body>
<!-- ================================== 카테고리 (Image Slide 하단, 3개짜리) ================================== -->
<div class="wrapper row2">
  <div id="services" class="clear"> 
	<div class="row">
	<table class="table">
		<tr>
			<td>
				<c:forEach var="vo" items="${list }">
					<table class="table">
						<tr>
							<td width="30%" class="text-center" rowspan="2">
								<a href="../recipe/chef_detail.do?name=${vo.chef }"><img src="${vo.poster }" class="img-circle" style="width:80px; height:80px;"></a>
							</td>
							<td colspan="4">
								<h3>
									<a style="color:orange;" href="../recipe/chef_detail.do?name=${vo.chef }">${vo.chef }</a>
								</h3>
							</td>
						</tr>
						<tr>
							<td class="text-center">
								<img src="../recipe/images/1.png">${vo.mem_cont1 }
							</td>
							<td class="text-center">
								<img src="../recipe/images/3.png">${vo.mem_cont3 }
							</td>
							<td class="text-center">
								<img src="../recipe/images/7.png">${vo.mem_cont7 }
							</td>
							<td class="text-center">
								<img src="../recipe/images/2.png">${vo.mem_cont2 }
							</td>
						</tr>
					</table>
				</c:forEach>
			</td>
		</tr>
	</table>
		
	</div>
    <div class="clear"></div>
    <!-- ========== Start of Pagination ========== -->
    <div class="text-center">
    	<ul class="pagination">
    	  <c:if test="${startPage>1}">
    	  	<li><a href="../recipe/chef.do?page=${startPage-1 }">&lt;</a></li>
    	  </c:if>
    	  <c:set var="type" value=""/>
    	  <c:forEach var="i" begin="${startPage }" end="${endPage }">
    	  	<c:if test="${curpage==i }">
    	  		<c:set var="type" value="class=active"/>
    	  	</c:if>
    	  	<c:if test="${curpage!=i }">
    	  		<c:set var="type" value=""/>
    	  	</c:if>
    	  	<li ${type }><a href="../recipe/chef.do?page=${i }">${i }</a></li>
    	  </c:forEach>
    	  <c:if test="${endPage<allPage }">
    	  	<li><a href="../recipe/chef.do?page=${endPage+1 }">&gt;</a></li>
    	  </c:if>
		</ul>
    </div>
    <!-- ========== End of Pagination ========== -->
  </div>
</div>
</body>
</html>



<!-- 
	[순서]
	<a> ==> link ==> Model (@RequestMapping) ==> SQL ==> DAO ==> Model ==> JSP 

 -->
 
 