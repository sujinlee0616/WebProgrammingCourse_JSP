<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.DiaryDAO"></jsp:useBean>
<%
	try
	{
		request.setCharacterEncoding("UTF-8");
	}catch(Exception ex){}
	
	/* 주소 검색 버튼 클릭 시 데이터 처리 */
	String dong=request.getParameter("dong");
	/* 왜 list를 null로 주는가? */
	List<ZipcodeBean> list=null;
	// 처음에는 준 dong값이 없으므로 dong은 null이다. 
	// 그런데, dong이 null이면 List<ZipcodeBean> list=dao.postfind(dong); 하면 zipcode 테이블의 모든 데이터가 다 출력되버림 
	// (참고: JSP의 모든 프로그램은 Java가 먼저 실행됨...) 
	// ===> 처음에 list를 null로 준다. 
	
	if(dong!=null){
		list=dao.postfind(dong);
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
.row{margin: 0px auto; width: 430px;}
td{font-size: 8pt; }
</style>
<script type="text/javascript">
function postfind(){
	var dong=document.frm.dong.value;
	 // document.[form name].[input name] 해서 input 태그에 접근했음 
	if(dong.trim()=="")
	{
		document.frm.dong.focus();	
		return;
	}
	document.frm.submit();
}
function ok(zip, addr){ // javascript 함수의 argument는 형(type) 받지X
	/* alert("ok() call..."); */
	opener.join_frm.post1.value=zip.substring(0,3);
	// opener: 창을 띄워준 애 ==> join.jsp를 말함. (postfind.jsp 창을 띄워준 애가 join.jsp니까)
	// opner.오프너의 form name.오프너의 input name.value 에 값을 대입 
	// 대입하는 값은 이 파일의 zip.substring(0,3)
	// opener객체: 부모 창을 컨트롤 할 때 사용. 
	opener.join_frm.post2.value=zip.substring(4,7);
	opener.join_frm.addr1.value=addr;
	self.close(); // 내 윈도우 창 닫는다. 
	//window.close(); 해도 됨 
	// 참고) window.close();, parent.close(), self.close()
	
}

</script>
</head>
<body>
	<div class="container">
		<h3 class="text-center">우편번호 검색</h3>
		<div class="row">
			<form method="POST" action="postfind.jsp" name="frm"> <!-- 자기 자신한테 데이터 넘겨줘야  -->
				<table class="table">
					<tr>
						<td>
							입력: <input type="text" name="dong" size="15" class="input-sm" value="<%=dong!=null?dong:""%>">
							<!-- 동 검색 시 input 창에서 동 사라지지 않도록  < %=dong!=null?dong:"" %> 처리 -->
							<!-- 
								주소검색 창에서  dong으로 '신촌' 입력 후 '검색' 버튼 클릭 (submit)
								 ==> postfind.jsp로 데이터를 보냄 
								 ==> postfind.jsp 창이 다시 불러와지면서 내가 입력한  dong이 지워짐 
								 ==> 검색버튼 클릭 후에도 기존에 입력한 dong이 input 창에서 지워지지 않도록  
								     value="< % =dong!=null?dong:"" % >로, dong이 null이 아니면 받아오게 했음.
							  -->	 
							<input type="button" value="검색" class="btn btn-sm btn-primary" onclick="postfind()">
						</td>
					</tr>
					<tr>
						<td class="text-right">
							<sub><font color="red">※ 동/읍/면을 입력하세요.</font></sub> 
						</td>
					</tr>
				</table>
			</form>
			<%
				if(list!=null && list.size()>0)
				{
			%>
					<table class="table table-hover">
						<tr>
							<th width=20% class="text-center">우편번호</th>
							<th width=80% class="text-center">주소</th>
						</tr>
					<%
						for(ZipcodeBean vo:list)
						{
					%>
							<tr>
								<td width=20% class="text-center"><%=vo.getZipcode() %></td>
								<td width=80%><a href="javascript:ok('<%=vo.getZipcode() %>','<%=vo.getAddress() %>')"><%=vo.getAddress() %></a></td>
								<!-- 
									<a href= javascript:함수명();></a> a 태그에서 javascript 함수 호출
								 -->
								<!-- 주소 검색 결과 클릭하면 
									1) join.jsp의 주소 input으로 데이터 보내야
									2) 창 닫아야  -->
							</tr>
					<%
						}
					
					%>
					</table>
			<%
				}
			
			%>
		</div>
	</div>
</body>
</html>












