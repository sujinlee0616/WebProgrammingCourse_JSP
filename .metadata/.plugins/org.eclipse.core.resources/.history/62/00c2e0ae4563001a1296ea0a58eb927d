<!-- ~~_ok.jsp 파일은 다 DB 처리하는 애들 ==> HTML 코드 없음  -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
	// 한글변환 
	try
	{
		request.setCharacterEncoding("UTF-8");
	}
	catch(Exception ex){}

	String name=request.getParameter("name");
	String subject=request.getParameter("subject");
	String content=request.getParameter("content");
	String pwd=request.getParameter("pwd");
	// hidden으로 받은 애 2개
	String no=request.getParameter("no");
	String strPage=request.getParameter("page");
	
	BoardVO vo = new BoardVO();
	vo.setNo(Integer.parseInt(no));
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(content);
	vo.setPwd(pwd);
	
	// DAO 연결
	BoardDAO dao=new BoardDAO();
	boolean bCheck=dao.boardUpdate(vo);	
	
	// 이동 
	// 비밀번호가 맞다면
	if(bCheck==true)
	{
		response.sendRedirect("detail.jsp?no="+no+"&page="+strPage);	
	}
	// 비밀번호가 틀렸다면
	else
	{
%>
	<script type="text/javascript">
		alert("비밀번호가 틀립니다!");
		history.back();
	</script>
<%
	}
	
	
%>



