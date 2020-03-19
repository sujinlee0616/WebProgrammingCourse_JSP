<!-- 상세보기 -->
<%@page import="com.sist.dao.FileBoardVO"%>
<%@page import="com.sist.dao.FileBoardDAO"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%
	/* int a=10/0; *//* <== code for testing error 500 */
	String no=request.getParameter("no");
	String strPage=request.getParameter("page");
	// no => DAO로 전송
	FileBoardDAO dao = new FileBoardDAO();
	// DAO에서 요청한 VO 하나를 받아온다.
	FileBoardVO vo=dao.boardDetailData(Integer.parseInt(no));
	// VO에 저장된 데이터를 HTML을 이용해서 출력 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<style type="text/css">
.row{
	margin: 0 auto;
	width: 600px;
}
h2 {
	text-align: center;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">

var i=0;
$(function(){
	$('#delBtn').click(function(){
		if(i==0) //i==0. '삭제' 버튼 눌렀을 때
		{  
			// 숨겨놨던 비밀번호 입력란 노출
			$('#del').show();
			// '삭제' 버튼을 '취소' 버튼으로 변경 
			$('#delBtn').val("취소");
			i=1;
		}
		else // i==1. '취소' 버튼 눌렀을 때.
		{ 
			$('#del').hide();
			// '취소' 버튼을 '삭제' 버튼으로 변경 
			$('#delBtn').val("삭제");
			i=0;
		}
	});
	
	$('#sendBtn').click(function(){
		var pwd=$('#pwd').val(); //input id=pwd의 값을 변수 pwd에 저장 
		var no=$('#no').val();
		var page=$('#page').val();
		if(pwd==""){
			$('#pwd').focus();
			return;
		}
		
		// json방식 AJAX
		$.ajax({
			type:'POST', // 감춰서 보낸다
			url:'delete.jsp',  // sendBtn 눌렀을 때 pwd,no,page를 delete.jsp에 보낸다 
			data:{no:no,pwd:pwd,page:page}, //data:{변수:값,변수:값....}
			// ==> get방식에서 delete.jsp?no=1&pwd=1234&page=12 이렇게 보내는것처럼 POST 방식으로 보내는 것임.
			success:function(res)
			{
				/* alert(res); */
				var result=res.trim();
				if(result==0){
					alert("비밀번호가 틀렸습니다.");
					$('#pwd').val(""); // input id=pwd 값 지움 
					$('#pwd').focus();
				}
				else{
					location.href="list.jsp?page="+page;
				}
			}
					
		});
	});
});
</script>

</head>
<body>
	<div class="container">
		<h2>내용보기</h2>
		<div class="row">
			<table class="table table-hover">
				<tr>
					<th width=20% class="text-center success">번호</th>
					<td width=30% class="text-center"><%=vo.getNo() %></td>
					<th width=20% class="text-center success">작성일</th>
					<td width=30% class="text-center"><%=vo.getRegdate() %></td>
				</tr>
				<tr>
					<th width=20% class="text-center success">이름</th>
					<td width=30% class="text-center"><%=vo.getName() %></td>
					<th width=20% class="text-center success">조회수</th>
					<td width=30% class="text-center"><%=vo.getHit() %></td>
				</tr>
				<tr>
					<th width=20% class="text-center success">제목</th>
					<td colspan=3 class="text-left"><%=vo.getSubject() %></td>
				</tr>
				<%
					if(vo.getFilesize()!=0)
					{
				%>
				<tr>
					<th width=20% class="text-center success">첨부파일</th>
					<td colspan=3 class="text-left">
						<a href="download.jsp?fn=<%=vo.getFilename() %>"><%=vo.getFilename() %></a>
						&nbsp;(<%=vo.getFilesize() %> Bytes)
					</td>
				</tr>
				<%		
					}
				
				
				%>
				<tr>
					<td colspan="4" class="text-left" valign="top" height="200">
						<pre style="white-space:pre-wrap; background-color: white; border: none;"><%=vo.getContent() %></pre>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="text-right">
						<a href="reply.jsp?no=<%=vo.getNo() %>&page=<%=strPage %>" class="btn btn-xs btn-success">답변</a>
						<a href="update.jsp?no=<%=vo.getNo() %>&page=<%=strPage %>" class="btn btn-xs btn-primary">수정</a>
						<input type="button" class="btn btn-xs btn-danger" id="delBtn" value="삭제">
						<a href="list.jsp" class="btn btn-xs btn-info">목록</a>
					</td>
				</tr>
				<tr id="del" style="display:none;">
					<td colspan="4" class="text-right">
						비밀번호:
						<input type="password" id="pwd" size=10 class="input-sm">
						<input type="hidden" id="no" value="<%=no%>">
						<input type="hidden" id="page" value="<%=strPage%>">
						<input type="button" value="삭제" class="btn btn-sm btn-danger" id="sendBtn">
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>

<!-- 
	<AJAX>
	 - AJAX: Asynchronous JavaScript + XML
	 - X가 XML을 의미하긴 하지만, 요즘은 JSON을 더 많이 사용함.
 	 - AJAX를 사용하지 않으면, 수정/삭제 시에 alert 뜨면서 화면이 사라졌다가 alert 닫으면 화면이 다시 생김(깜빡거림. 화면이 변경됨.)
 	 - 겉보기에는 화면에 출력되는 게 바뀐다는 점에서 javascript로 setTimeOut()해서 조정하는 것들과 비슷해보이지만,
 	   AJAX는 DB에서 데이터 갖고와서 처리한다는 점에서 다르다. 
	<AJAX 사용 예시>
 	 - 회원가입할 시 id 유효성검사를 하면 가끔 '중복된 id입니다'가, 화면전환 없이 표시되는 경우가 있음 ==> AJAX 사용한거임. 
 	 - '더보기' 누르면 깜빡거림 없이 리스트 더 나오는 것...
 	 - 검색어 자동완성 
-->
<!-- 
	<jQuery>
	 - jQuery는 onload(HTML, CSS, image 다 불러진 상태)된 상태에서 동작함
	 1) 함수
	  - JavaScript: window.onload=function(){}
	  - jQuery: $(function(){});
	 2) 특정ID를 가진 요소 선택하기
	  - JavaScript: document.getElementById('delBtn')
	  - jQuery: $('#delBtn')
	 3) 특정class를 가진 요소 선택하기 
	  - JavaScript: document.getElementsByClassName('wrap')
	  - jQuery: $('.wrap')
	 4) 특정 tag 가져오기
	  - JavaScript: document.getElementsByTagName('td')
	  - jQuery: $('td')
	 5) element의 attribute/text/value 가져오는 법
	  (1) element의 text 가져오기 
	  - <a>bbb</a> 에서 bbb를 가져오고 싶다면?
	  	 $('a').text()
	  (2) 
	  - <a></a> 안에 bbb를 넣고 싶다면? 
	  	 $('a').text('bbb')
	  - <a href="kkk" id="k"></a>에서 kkk 값을 가져오고 싶다면? 
	 	 $('#k').attr("href")
	  - <input type="text" id="p" value="kkk">에서 kkk를 ddd로 바꾸고 싶다면? 
	  	 $('#p').val() => $('#p').val('ddd')
	  - <td><span>ooo</span></td> 
	  	 $('td>span').text() ===> ooo
	  	 $('td').text() ==> ooo 
 -->
<!-- <Annotation>: index같은거라고...? -->




