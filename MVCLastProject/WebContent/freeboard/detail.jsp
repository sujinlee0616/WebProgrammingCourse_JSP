<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>

<script type="text/javascript">
//전역변수 - 화면이 바뀌기 전까지 계속 유지하고 있어야..
var u=0; // 댓글 수정 input창의 상태 체크를 위한 변수  
var i=0; // 대댓글 입력 input창의 상태 체크를 위한 변수  
$(function(){ // 얘는 onload라는 함수. 
	// [댓글 수정] - 댓글수정 버튼 클릭 시, 숨겨놓은 input 창 보이도록.
	$('.upBtn').click(function(){
		$('.reply_update').hide();
		$('.reply_insert').hide();
		var no=$(this).attr('data-no');

		if(u==0)
		{
			$('#m'+no).show();
			u=1;
		}
		else
		{
			$('#m'+no).hide();
			u=0;
		}
		
	})
	
	$('.inBtn').click(function(){
		$('.reply_update').hide();
		$('.reply_insert').hide();
		var no=$(this).attr('data-no');
		
		if(i==0)
		{
			$('#i'+no).show();
			i=1;
		}
		else
		{
			$('#i'+no).hide();
			i=0;
		}
		
	})
	
	
})
</script>
</head>
<body>
	<div class="container">
		<div id="services" class="clear">
			<h2 class="text">내용보기</h2>
		  	<div class="row text-center">
		  		<img src="../freeboard/jsp_board2.png" style="width: 900px; height: 200px;">
		  	</div> 
			<div class="row">
				<table class="table">
					<tr>
						<th class="text-center success" width=20% style="color:black;">번호</th>
						<td class="text-center" width=30% >${vo.no }</td>
						<th class="text-center success" width=20% style="color:black;">작성일</th>
						<td class="text-center" width=30%>
							<fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd"/>
						</td>
					</tr>
					<tr>
						<th class="text-center success" width=20% style="color:black;">이름</th>
						<td class="text-center" width=30%>${vo.name }</td>
						<th class="text-center success" width=20% style="color:black;">조회수</th>
						<td class="text-center" width=30%>${vo.hit }</td>
					</tr>
					<tr>
						<th class="text-center success" width=20% style="color:black;">제목</th>
						<td class="text-left" colspan="3">${vo.subject }</td>
					</tr>
					<tr>
						<td class="text-left" colspan="4" valign="top" height="200">${vo.content }</td>
					</tr>
					<tr>
						<td class="text-right" colspan="4">
							<a href="../freeboard/update.do?no=${vo.no }" class="btn btn-xs btn-success">수정</a>
							<a href="../freeboard/delete.do?no=${vo.no }" class="btn btn-xs btn-info">삭제</a>
							<a href="../freeboard/list.do" class="btn btn-xs btn-warning">목록</a>
						</td>
					</tr>
				</table>
				<div style="height: 20px;"></div>
				<!--  ============================ [댓글] ============================ -->
				<table class="table">
					<c:forEach var="rvo" items="${list }">
						<!-- ================= 1. 상단 작성자, 작성일, 버튼 3개 영역 =================  -->
						<tr>
							<!-- ====== 1-1. 댓글 작성자, 작성일 ====== -->
							<td class="text-left">
								<!-- 대댓글일 경우 이미지/공백 추가 -->
								<c:if test="${rvo.group_tab>0 }">
									<c:forEach var="i" begin="1" end="${rvo.group_tab }">
										&nbsp;&nbsp;
									</c:forEach>
									<img src="icon_reply.gif">
								</c:if>
								${rvo.name }&nbsp;<span style="color:#999;">(${rvo.dbday })</span>
							</td>
							<td class="text-right">
								<!-- 이미 내가 삭제한 댓글은 수정/삭제/대댓글 달지 못하게 -->
								<c:if test="${rvo.msg!='삭제된 댓글입니다.' }">
									<!-- ====== 1-1, 1-2. [댓글 수정/삭제] 내가 작성한 댓글이면 댓글 수정/삭제 버튼 노출 ====== -->
									<c:if test="${sessionScope.id==rvo.id }">
										<span class="btn btn-xs btn-primary upBtn" data-no="${rvo.no }">수정</span>
										<a href="../freeboard/reply_delete.do?no=${rvo.no }&bno=${vo.no}" class="btn btn-xs btn-danger">삭제</a> 
										<!-- 삭제: no 있어야 이 댓글 삭제 가능, bno있어야 이 게시글로 다시 redirect 가능 -->
									</c:if>
									<!-- ====== 1-3. [대댓글 쓰기] ====== -->
									<span class="btn btn-xs btn-success inBtn" data-no="${rvo.no }">댓글</span>
								</c:if>
							</td>
						</tr>
						<!-- ========================= 2. 하단 댓글 내용 영역 =========================  -->
						<tr>
							<td colspan="2" class="text-left" valign="top">
								<pre style="white-space: pre-wrap;">${rvo.msg }</pre> <!-- white-space: 글자가 영역을 벗어나면 밑으로 내려가게 만드는 것 -->
							</td>
						</tr>
						
						<!-- ★[댓글 수정]★ - 댓글수정 버튼 클릭 시 나오는 input -->
						<tr id="m${rvo.no }" style="display: none;" class="reply_update">
							<td colspan="2">
								<form method="post" action="../freeboard/reply_update.do">
									<input type="hidden" name="bno" value="${vo.no }">
									<input type="hidden" name="no" value="${rvo.no }">
									<textarea rows="5" cols="120" name="msg" style="float:left;">${rvo.msg }</textarea>
									<input type="submit" class="btn btn-sm btn-primary" style="height: 100px; float:left;"
									value="수정하기">
								</form>
							</td>
						</tr>
						
						<!-- ★[대댓글 쓰기]★ - 대댓글 달기 버튼 클릭 시 나오는 input -->
						<tr id="i${rvo.no }" style="display: none;" class="reply_insert">
							<td colspan="2">
								<form method="post" action="../freeboard/reply_reply_insert.do">
									<input type="hidden" name="bno" value="${vo.no }"> <!-- bno: 이 게시물 번호 bno -->
									<input type="hidden" name="pno" value="${rvo.no }"> <!-- pno: 대댓글 달려고 하는 댓글의 no 번호 -->
									<textarea rows="5" cols="120" name="msg" style="float:left;"></textarea>
									<input type="submit" class="btn btn-sm btn-primary" style="height: 100px; float:left;"
									value="대댓글 쓰기">
								</form>
							</td>
						</tr>
						
					</c:forEach>
					<tr>
						<td class="text-center" colspan="2">
							<a href="#" class="btn btn-xs btn-danger">이전</a>
							${curpage } page / ${totalpage } pages
							<a href="#" class="btn btn-xs btn-danger">다음</a>
						</td>
					</tr>
				</table>
				<table class="table">
					<tr>
						<td>
							<form method="post" action="../freeboard/reply_insert.do">
								<input type="hidden" name="bno" value="${vo.no }">
								<textarea rows="5" cols="120" name="msg" style="float:left;"></textarea>
								<input type="submit" class="btn btn-sm btn-primary" style="height: 100px; float:left;"
								value="댓글쓰기">
							</form>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>



				

