<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		$('#findBtn').click(function() {
			var dong=$('#dong').val(); // <input id="dong"> 값을 변수에 저장
			if(dong.trim()==""){
				$('#dong').focus();
				return;
			}
			//alert(dong);
			$.ajax({
				type:'POST',
				url:'../member/postfind_result.do',
				/* 
					왜 postfind_result.do로 보내는가? 
					 - 자기자신(post.do)로 보내면, 기존 창은 죽고 새로운 창(post.jsp 창)이 만들어지기 때문. 
					 - 그런데, 우리는 창을 유지해야하기 함. 
				*/
				data:{"dong":dong}, // data:{"key":value}
				success:function(result){
					var div=$('#result').html(result);  //#result div에 html값 변경
					console.log(div);
				},
				error:function(e)
				{
					alert(e);
				}
			})
		})
	})
</script>
<style>
	.row{margin: 0px auto; width:500px; }
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<tr>
					<td>
						입력: <input type="text" id="dong" size="15" class="input-sm">
						<input type="button" id="findBtn" size="15" class="btn btn-sm btn-danger" value="입력">
					</td>
				</tr>
				<tr>
					<td class="text-right">
						<sub style="color: red;">※동/읍/면을 입력하세요.</sub>
					</td>
				</tr>
			</table>
			<div id="result">
			</div>
		</div>
	</div>
</body>
</html>





