<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$(function() {
	// JSON 파싱해서 #list 밑에 appennd 해줌 
	$.getJSON('weekly.json',function(data){
		// for each 구문과 동일. for(MovieVO vo:list)와 동일한 구문.
		// $.each(data["datas"],function(key,value){...})
		$.each(data["datas"],function(key,value){ 
			$('#list').append(
				'<div class="col-md-3">'
					+'<div class="thumbnail">'
						+'<img src="'+value.poster+'" alt="Lights" style="width:100%" onclick=detail('+value.no+')>'
						+'<div class="caption">'	
							+'<p id="ppp">'+value.title+'</p>'
						+'</div>'	
					+'</div>'
				+'</div>'
			)
		});
	});
	
	// [Test] 작동 안 함 - id인 #ppp가 아직 로딩이 안 된 상태기 때문. (위에서 생성된 #ppp는 가상 메모리에 올려져 있음. HTML로 실제로 출력되지 않음.) 
	$('#ppp').click(function(){
		let p=$(this).text();
		alert(p);
	}) 
});

//클릭 시 호출되는 detail 함수 
function detail(no)
{
	// JSON 읽어서 테이블 element들에 데이터를 넣는다.
	$.getJSON('weekly.json',function(data){
		$.each(data["datas"],function(key,value){ 
			if(no==value.no)
			{
				$('#img').attr("src",value.poster); // src에 value.poster 값을 넣어라.	
				$('#title').text(value.title);
				$('#director').text(value.director);
				$('#actor').text(value.actor);
				$('#genre').text(value.genre);
				$('#grade').text(value.grade);
				$('#score').text(value.score);
				$('#regdate').text(value.regdate);
				$('#story').text(value.story);
				
				return false; // 여기서 return 해주지 않으면 no==value.no인 no 찾은 이후에도 for each문 돌아가므로, for each 종료를 위해 return.
			}
	
		});
	});
	
	$('#movie_dialog').dialog({  // #movie_dialog를 dialog로 띄워라 
		width:700,
		height:500
	})
}
</script>
</head>
<body>
	<div class="container">
		<h1 class="text-center">영화목록</h1>
		<div class="row" id="list">
			<!-- jQuery의 $('#list').append가 요소를 추가함 -->
		</div>
		<div class="row">
			<div id="movie_dialog" title="영화 상세보기" style="display:none;">
				<table class="table">
					<tr>
						<td width=30% class="text-center" rowspan="7">
							<img src="" width="100%" id="img">
						</td>
						<td colspan="2" id="title"></td>
					</tr>
					<tr>
						<td width=10% class="text-right">감독</td>
						<td width=60% id="director"></td>
					</tr>
					<tr>
						<td width=10% class="text-right">출연</td>
						<td width=60% id="actor"></td>
					</tr>
					<tr>
						<td width=10% class="text-right">장르</td>
						<td width=60% id="genre"></td>
					</tr>
					<tr>
						<td width=10% class="text-right">등급</td>
						<td width=60% id="grade"></td>
					</tr>
					<tr>
						<td width=10% class="text-right">평점</td>
						<td width=60% id="score"></td>
					</tr>
					<tr>
						<td width=10% class="text-right">상영일</td>
						<td width=60% id="regdate"></td>
					</tr>
					<tr>
						<td colspan="3" class="text-left" id="story"></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>












