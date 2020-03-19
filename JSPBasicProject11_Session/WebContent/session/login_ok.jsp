<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.DiaryDAO"></jsp:useBean>
    
<%
	String id=request.getParameter("id");
	String pwd=request.getParameter("pwd");
	
	// <DAO 연결>
	String res=dao.isLogin(id, pwd);	
	
	// <이동> - 로그인 성공 시 일정표(diary.jsp)로 이동시킴 
	if(res.equals("NO_ID")) // ID틀림 - 존재하지 않는 ID 입력  
	{
		%>
			<script>
			alert("아이디가 존재하지 않습니다.");
			history.back();
			</script>
		<%
	}
	else if(res.equals("WRONG_PWD")) // ID는 있는건데 비번을 잘못 입력 
	{
		%>
			<script>
			alert("비밀번호가 틀렸습니다.");
			history.back();
			</script>
		<%
	}
	else // 로그인 성공 ==> 일정표(diary.jsp)로 이동시킴 
	{
		// ID/PWD 물고 가야 하니까 Session 사용 
		session.setAttribute("id", id);
		session.setAttribute("name", res); // res는 dao의 isLogin에서 id,pwd 넣은애임 (이 파일 10번째 줄)
		
		response.sendRedirect("diary.jsp");
	}
	
	
	
%>








