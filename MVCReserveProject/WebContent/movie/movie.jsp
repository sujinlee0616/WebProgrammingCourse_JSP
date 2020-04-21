<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	// reserve.do에서 .movie_title 영역에 hover하면 마우스 커서 모양 변경 
	$('.movie_title').hover(function(){ // if hover했으면 
		$(this).css('cursor','pointer');	
	},function(){ // else (hover 안 하면)
		$(this).css('cursor','none');
	});
	
	// reserve.do의 극장선택 영역(#movie-list)에 출력 
	// - 이것들은, reserve.jsp에 마치 include되는것처럼 동작하면서, #movie-list가 아닌 reserve.jsp의 다른 영역에도 데이터를 전달하게 된다. 
	// - 여기서 theater.do로 정보를 넘김 ★★★ 
	$('.movie_title').click(function(){
		var poster=$(this).attr("data-poster");
		$('#movie-poster').attr("src",poster);  
		$('#movie-title').text($(this).text());
		$('#movie-score').text($(this).attr('data-score')); // 여기까진 수행됨
		console.log(poster);
		
		// 참고) AJAX로 값을 가지고 오면, 가상으로 들어가 있기 때문에  include와 똑같은 역할을 한다.
		var tno=$(this).attr("data-theater"); 
		console.log(tno);
		
		$.ajax({
			type:'POST',
			url:'theater.do',
			data:{"tno":tno},
			success:function(res)
			{
				$('#movie-theater').html(res);
			},
			error:function(e)
			{
				alert(e);
			}
			
		})
	});
	
})


</script>
</head>
<body>
	<div class="row" style="margin: 0px auto; width:350px; height:500px;">
		<table class="table">
			<c:forEach var="vo" items="${mList }">
				<tr>
					<td>
						<img src="${vo.poster }" width="35" height="35">
					</td>
					<td class="movie_title" data-poster=${vo.poster } data-score="${vo.score }" data-theater="${vo.theaterno }">${vo.title }</td>
					<!-- data-poster: 영화제목 td 안에다가 포스터 정보를 안 보이게 넣어놨음 ==> reserve.do의 #movie-poster에서 보임 -->
					<!-- data-score도 차만가지. -->
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>