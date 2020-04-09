<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
td,th{background-color: white;}
</style>
</head>
<!-- [recipe_detail 테이블]
	NO                    NUMBER         
	POSTER       NOT NULL VARCHAR2(300)  
	TITLE        NOT NULL VARCHAR2(300)  
	CHEF         NOT NULL VARCHAR2(100)  
	CHEF_POSTER  NOT NULL VARCHAR2(200)  
	CHEF_PROFILE NOT NULL VARCHAR2(100)  
	INFO1                 VARCHAR2(20)   
	INFO2                 VARCHAR2(20)   
	INFO3                 VARCHAR2(20)   
	CONTENT      NOT NULL VARCHAR2(4000) 
	FOODMAKE              CLOB           
 -->
<body>
<div class="wrapper row2">
  <div id="services" class="clear"> 
	<div class="row">
		<c:if test="${count!=0 }">
			<div class="thumbnail">
		        <img src="${vo.poster }" alt="Lights" style="width:100%">
		        <div class="caption">
		          <p class="text-center"><img src="${vo.chef_poster }" width=100 height=100 class="img-circle"></p>
		          <p class="text-center">${vo.chef }</p>
		        </div>
		    </div>
		    <table class="table">
		    	<tr>
		    		<td class="text-center" colspan="3">
		    			<b>${vo.title }</b>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td class="text-center" colspan="3">
		    			<font color="gray">${vo.content }</font>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td class="text-center"><img src="images/inwon.png"></td>
		    		<td class="text-center"><img src="images/time.png"></td>
		    		<td class="text-center"><img src="images/target.png"></td>
		    	</tr>
		    	<tr>
		    		<td class="text-center">${vo.info1 }</td>
		    		<td class="text-center">${vo.info2 }</td>
		    		<td class="text-center">${vo.info3 }</td>
		    	</tr>
		    	<tr>
		    		<td colspan=3>
		    			<c:forTokens items="${vo.foodmake }" delims="@" var="s">
		    				${s }<br>
		    			</c:forTokens>
		    		</td>
		    	</tr>
		    </table>
		    <table class="table">
		    	<caption><h3>레시피 작성자</h3></caption>
		    	<tr>
		    		<td width="30%" class="text-center" rowspan="2">
		    			<img src="${vo.chef_poster }" style="width:45px; height:45px;" class="img-circle">
		    		</td>
		    		<td width="70%">${vo.chef }</td>
		    	</tr>
				<tr>
					<td width="70%">${vo.chef_profile }</td>
				</tr>
		    </table>
	    </c:if>
	    <c:if test="${count==0 }">
	    	<h1 class="text-center">정보가 없습니다.</h1>
	    </c:if>
	</div>
  </div>
</div>
</body>
</html>









