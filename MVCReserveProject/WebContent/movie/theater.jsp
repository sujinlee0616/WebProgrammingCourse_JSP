<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	// reserve.do에서 .theater 영역에 hover하면 마우스 커서 모양 변경 
	$('.theater').hover(function(){
		$(this).css('cursor','pointer'); // if hover하면 
	},function(){ // hover 안 하면
		$(this).css('cursor','none');	
	});
	
	// reserve.do에서 '극장선택'에서 선택하면 '예매정보'에 해당 극장 정보 들어가게끔 
	$('.theater').click(function(){
		var data=$(this).text().trim(); // ex) [신촌아트레온] CGV
		var loc=data.substring(1,data.lastIndexOf(']')); // ex) 신촌아트레온
		var name=data.substring(data.indexOf(']')+2); // ex) CGV
		//alert("loc="+loc+"\nname="+name);
		var theater=name+"("+loc+")";
		// reserve.jsp의  #movie-theater2에 출력 (맨 오른쪽 '예매정보' 하단)
		$('#movie-theater2').html(theater);
	
		// date.do로 예약가능한 날짜 값을 보낸다.
		var year=$('#year').val();
		var month=$('#month').val();
		var rdate=$(this).attr("data-date");
		$.ajax({
			type:'POST',
			url:'date.do',
			data:{"year":year,"month":month,"rdate":rdate},
			success:function(res)
			{
				$('#movie-date').html(res);
			}
		})
		
		
	})
	
	
	
})
</script>
</head>
<body>
	<div class="row" style="margin: 0px auto; width:350px; height:500px;">
		<table class="table">
			<c:forEach var="vo" items="${tList }">
				<tr>
					<td class="theater" data-date="${vo.tdate }">
						[${vo.tloc }]&nbsp;${vo.tname }
					</td>
					<!-- data-date: 영화관 td 안에다가 날짜 정보를 안 보이게 넣어놨음 ==> reserve.do의 #movie-theater에서 보임 -->
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>