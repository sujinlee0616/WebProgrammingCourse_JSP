<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- require 쓰기 위해서 HTML 5 버젼 쓰자 ==> head 부분을 HTML5 양식으로 고쳤음 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<style type="text/css">
.row{
	margin: 0 auto;
	width: 600px;
}
h2 {
	text-align: center;
}
</style>
<script type="text/javascript">
$(function(){
	$('#pwd2').keyup(function(){ // 유저가 뭔가 입력후 바로 체크 ==> keyup으로 체크 
		var user_input_pwd=$(this).val();
		console.log(user_input_pwd);
		var no=$('#no').val();	
		$.ajax({
			type:'POST', // 
			url:'../reply/password_check.do', // 클라이언트가 요청을 보낼 서버의 URL 주소
			data:{"pwd":user_input_pwd,"no":no}, // HTTP 요청과 함께 서버로 보낼 데이터
			success:function(res) // success:  요청이 성공일때 실행되는 callback 함수
			{ 
				var no=res.trim();
				if(no==1){
					$('#pwd_check_result').html("<font color=blue>비밀번호가 맞습니다. 수정할 수 있습니다.</font>");
					$('#updateBtn').attr('disabled', false);
				}
				else
				{
					$('#pwd_check_result').html("<font color=red>비밀번호가 틀립니다.</font>");
					$('#updateBtn').attr('disabled', true);
				}
			},
			error:function(e){
				alert(e);
			}
		})
	});
});
</script>
</head>
<body>
	<div class="container">
		<h2>수정하기</h2>
		<div class="row">
			<form method=post action="update_ok.do"> 
			<!-- action: update_ok.jsp ==> form에서 보내주는 데이터를  update_ok.jsp에서 받아서 DB 처리 -->
				<table class="table table-hover">
					<tr>
						<th width=20% class="text-right success">이름</th>
						<td width=80%>
							<input type="text" name="name" size=15 value="${vo.name }" required />
							<!-- 데이터 숨겨서 보내기 (update_ok.jsp한테 줄 데이터) -->
							<input type="hidden" name="no" id="no" value="${vo.no }">
							<%-- <input type="hidden" name=page value="${vo.curpage }"> --%>
							<!-- post방식으로 보내주니까 get방식처럼 url에 싣어다 정보 보낼 수 X ==> hidden으로 숨겨서 보낸다  -->
						</td>
					</tr>
					<tr>
						<th width=20% class="text-right success">제목</th>
						<td width=80%>
							<input type="text" name="subject" size=50 value="${vo.subject }" required />
						</td>
					</tr>
					<tr>
						<th width=20% class="text-right success">내용</th>
						<td width=80%>
							<textarea rows="8" cols="50" name="content" required>${vo.content }</textarea>
							<!-- input은 value로 주고, textarea는 태그 사이에 값 준다. -->
						</td>
					</tr>
					<tr>
						<th width=20% class="text-right success">비밀번호</th>
						<td width=80%>
							<input type="password" name="pwd" size=10 required id="pwd2"> <!-- main.jsp의 비번창이 id가 pwd라서 얘는 id를 pwd2로 줬음 -->
							<div id="pwd_check_result"></div>
						</td>
					</tr>
					<tr>
						<td class="text-center" colspan="2">
							<input type="submit" value="수정" class="btn btn-sm btn-primary"
							id="updateBtn" disabled>
							<input type="button" value="취소" class="btn btn-sm btn-danger"
							onclick="javascript:history.back()">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>




