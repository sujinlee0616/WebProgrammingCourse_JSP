<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.DiaryDAO"></jsp:useBean>
<%
	String id=request.getParameter("id");
	int temp=0;
	int count=0;
	
	if(id!=null) // id가 입력이 되었다면
	{
		count=dao.idcheck(id); // count: id가 기존에 존재하는지 체크 (동일한 id가 검색되면 DAO에서 동일한 id 몇개인지 count 셀려줌) 
		temp=1; // temp: id가 입력되었는지 확인 
	}
	
	

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
	.row{margin: 0px auto; width: 320px;}
</style>
<script type="text/javascript">
function idcheck(){
	var id=document.idcheck_frm.id.value;
	if(id.trim()=="")
	{
		document.idcheck_frm.id.focus();
		return;
	}
	document.idcheck_frm.submit(); // 데이터 전송 
}

function ok(){
	opener.join_frm.id.value=document.idcheck_frm.id.value;
	// opener: 부모 ==> join.jsp
	// join.jsp의 <form name="join_frm">인 애의 <inlut name="id">인 애의 값에다가 
	// 이 파일(idcheck.jsp)의 <form name="idcheck_frm">인 애의 <inlut name="id">인 애의 값을 넣어라.
	self.close();
	
}
</script>
</head>
<body>
	<div class="container">
		<h3 class="text-center">아이디 중복체크</h3>
		<div class="row">
			<table class="table">
				<tr>
					<td>
						<form method="POST" action="idcheck.jsp" name="idcheck_frm">
							<!-- 
								ID 중복체크 창에서 ID 'testid123' 입력 후 '검색' 버튼 클릭 (submit)
								 ==> idcheck.jsp로 데이터를 보냄 
								 ==> idcheck.jsp 창이 다시 불러와지면서 내가 입력한 ID가 지워짐 
								 ==> 검색버튼 클릭 후에도 기존에 입력한 ID가 input 창에서 지워지지 않도록  
								     value="< % =id!=null?id:"" % >로, ID가 null이 아니면 받아오게 했음.
							  -->
							입력: <input type="text" name="id" size="15" value="<%=id!=null?id:""%>">
							<input type="button" value="검색" class="btn btn-sm btn-primary" size="15" onclick="idcheck()">
						</form>
					</td>
				</tr>
				<tr>
					<td class="text-center">
					<%
						if(temp==0) // id가 입력되지 않았음 
						{
					%>
							<font color=grey>ID를 입력하세요.</font>
					<%
						}
						if(count==0 && temp==1)
						{
					%>
							<font color=blue><%=id %>는(은) 사용 가능합니다.</font>
					<%
						}
						else if(count==0 && temp==1)
						{
					%>
							<font color=red><%=id %>는(은) 이미 사용중입니다.</font>
					<%
						}
					%>
					</td>
				</tr>
			<%
				/* ID 중복 아닐 떄만 확인 버튼 나오도록 */
				if(count==0 && temp==1)
				{
			%>
					<tr>
						<td class="text-center">
							<input type="button" value="확인" class="btn btn-sm btn-danger" onclick="ok()">
						</td>
					</tr>
			<%
				}
			%>
				
			</table>
		</div>
	</div>
</body>
</html>


