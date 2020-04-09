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
$(function(){
	$('#id').focus(); // idcheck iframe 창 오픈될 때 부터 id input에 focus되게 
	$('#checkBtn').click(function(){
		let id=$('#id').val();
		// id가 빈 값이면 checkBtn 눌렀을 떄 id input에 focus가게 
		if(id.trim()=="") 
		{
			$('#id').focus();
			return; // 종료
		}
		// MemberModel에 id를 전송
		$.ajax({
			type:'POST',
			url:'../member/idcheck_result.do',
			data:{"id":id}, // data:{"key":value}
			// data:{"id":"admin","pwd":"1234"} ==> idcheck_result.do?id=admin&pwd=1234
			success:function(result){ // 콜백함수. success: 200 
				let count=parseInt(result.trim());
				if(count==0) // 내가 입력한 id와 동일한 아이디 없을 때 
				{
					let msg='<font color="blue"><b>'+id+'는(은) 사용 가능합니다.'+'</b></font>';
					$('#result').html(msg);
					$('#ok').html(
						'<input type="value" value="확인" class="btn btn-sm btn-success" onclick="ok()">'
					);
				}
				else // 내가 입력한 id와 동일한 아이디가 이미 존재할 때 
				{
					let msg='<font color="red"><b>'+id+'는(은) 이미 사용중입니다.'+'</b></font>';
					$('#result').html(msg);
				}
			},
			error:function(e) // 콜백함수. error: 404, 500 
			{
				alert(e);
			}
		})
		// 결과값을 읽어서 처리
	})
});
function ok(){
	let id=$('#id').val();
	parent.join_frm.id.value=id; // parent인 join.jsp의 join_frm의 name="id"인 input의 값에 id를 채워라
	parent.Shadowbox.close(); // join.jsp가 Shadowbox 만들었으니 parent(join.jsp)가 Shadowbox 닫으라고 해야 함. 
}


</script>
<style>
	.row{margin: 0px auto; width:350px; }
</style>
</head>
<body>
	<div class="container">
		<h2 class="text-center">ID check</h2>
		<div class="row">
			<table class="table">
				<tr>
					<td>
						입력:<input type="text" id="id" class="input-sm" size="15">
						<input type="button" value="아이디 중복 체크" class="btn btn-sm btn-primary" id="checkBtn">
					</td>
				</tr>
				<tr>
					<td class="text-center">
						<span id="result"></span>
					</td>
				</tr>
				<tr>
					<td class="text-center" id="ok">
					
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>



<!--  [callback 함수]
	  - 위에서 'success:function(result){}', 'error:function(e){}' 이런 함수는 '콜백함수'이다. 
	  - 시스템에 의해서 자 호출되는 함수를 말함.
		1) AJAX
		 - XMLHttpRequest request => 얻어온다 (각 브라우저에 내장)
		2) 연결
		 - request.open('POST/GET','../member/idcheck_result.do',true) // false:동기화, true:비동기화 
		 - 참고) https://developer.mozilla.org/ko/docs/Web/Guide/AJAX/Getting_Started
		3) 결과값을 가지고 오는 자동 호출함수를 제작
		 - request.onreadystatechange=sendMessage
		4) 전송할 데이터 설정
		 - request.send(id=admin&pwd=1234)
		    function sendMessage()
			{
				if(request.readyState==4)
				{
					if(request.status==200)
					{
						
					}
					else
					{
					
					}
				}
				// ※ readyState 값 
				//  - 0: open 전 => 연결 중 
				//  - 1: open 후 => 연결완료 
				//  - 2: send준비 
				//  - 3: send완료 
				//  - 4: 정상연결, send 완료 확인
				//  - 참고) https://unikys.tistory.com/232
			}	
 -->
 
 <!-- [JQeury 메소드]
 
 	$("셀렉터").html()
	 - 셀렉터태그내에 존재하는 자식태그을 통째로 읽어올때 사용되는 함수
	 - 태그 동적추가할때 주로 사용되는 함수

	$("셀렉터").text()
	 - 셀렉터태그내에 존재하는 자식태그들 중에 html태그는 모두 제외 한 채 문자열만 출력하고자 할때 사용되는 함수
	 - html태그까지 모두 문자로 인식시켜주는 함수

	$("셀렉터").val()
	 - input 태그에 정의된 value속성의 값을 확인하고자 할때 사용되는 함수
 
  -->



