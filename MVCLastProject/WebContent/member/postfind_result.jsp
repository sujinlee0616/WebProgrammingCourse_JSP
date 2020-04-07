<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	td{font-size: 8pt;}
</style>
<script type="text/javascript">
	// 방법1.
	$(function(){
		 $('.post_click').hover(function(){
			$(this).css("cursor","pointer").css("background-color","#eeeeee");
		}, function(){
			$(this).css("cursor","none").css("background-color","white");
		});
		$('.post_click').click(function(){
			var zip=$(this).attr('zip');   // this: 클릭한 자신을 가지고 온다 
			var addr=$(this).attr('addr'); 
			parent.join_frm.post1.value=zip.substring(0,3); // parent:join.jsp
			parent.join_frm.post2.value=zip.substring(4,7);
			parent.join_frm.addr1.value=addr;
			parent.Shadowbox.close(); 
		});
	});
	// 방법2.
	function ok(zip,addr)
	{
		console.log(zip);
		parent.join_frm.post1.value=zip.substring(0,3); // parent:join.jsp
		parent.join_frm.post2.value=zip.substring(4,7);
		parent.join_frm.addr1.value=addr;
		parent.Shadowbox.close(); 
	}
</script>
</head>
<body>
	<c:if test="${count==0 }">
		<table class="table">
			<tr>
				<td class="text-center">
					<b>검색 결과가 없습니다.</b>
				</td>
			</tr>
		</table>
	</c:if>
	<c:if test="${count!=0 }">
		<table class="table">
			<tr class="success">
				<th class="text-center">우편번호</th>
				<th class="text-center">주소</th>
			</tr>
			<c:forEach var="vo" items="${list }">
				<tr class="post_click" zip="${vo.zipcode }" addr="${vo.address }">  <!-- HTML attr은 내 마음대로 정의할 수 있음 -->
					<td class="text-center">${vo.zipcode }</td>
					<!-- 방법1. -->
					<td>${vo.address }</td>
					<!-- 방법2. -->
					<!--<td><a href="javascript:ok('${vo.zipcode }','${vo.address }')">${vo.address }</a></td>-->
				</tr>
			</c:forEach>
		</table>
	</c:if>


</body>
</html>









