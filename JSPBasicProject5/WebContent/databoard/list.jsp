<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.dao.*" %>
<%
	String strPage=request.getParameter("page");
	if(strPage==null)
	{
		strPage="1";
	}
	/*
	<null과 공백의 차이>
	http://localhost/JSPBasicProject5/databoard/list.jsp 
	===> null ===> '=='으로 비교 
		ex) https://movie.daum.net/premovie/released
            : page는 null 임 ==> page가 null일 때 1페이지 보여주도록 코딩해놓은 것. 
	http://localhost/JSPBasicProject5/databoard/list.jsp?page=
	==> ""(공백) ===> 'equals'로 비교 
	 */
	 
	int curpage=Integer.parseInt(strPage);
	
	FileBoardDAO dao=new FileBoardDAO();
	List<FileBoardVO> list = dao.boardListData(curpage);
	
	

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<style type="text/css">
@import url('board.css');
</style>
</head>
<body>
	<div class="container">
		<h2>자료실</h2>
		<div class="row">
			<table class="table">
				<tr>
					<td>
						<a href="insert.jsp" class="btn btn-sm btn-primary">새 글</a>
					</td>
				</tr>
			</table>
			<table class="table table-hover">
				<tr class="warning">
					<th width=10% class="text-center">번호</th>
					<th width=45% class="text-center">제목</th>
					<th width=15% class="text-center">이름</th>
					<th width=20% class="text-center">작성일</th>
					<th width=10% class="text-center">조회수</th>
				</tr>
				<%
					for(FileBoardVO vo:list) 
					{
				%>
					<tr>
						<td width=10% class="text-center"><%=vo.getNo() %></td>
						<td width=45% class="text-left">
							<a href="detail.jsp?no=<%=vo.getNo()%>"><%=vo.getSubject() %></a>
						</td>
						<td width=15% class="text-center"><%=vo.getName() %></td>
						<td width=20% class="text-center"><%=vo.getRegdate() %></td>
						<td width=10% class="text-center"><%=vo.getHit() %></td>
					</tr>
				<%
					}
				%>
			</table>
		</div>
	</div>

</body>
</html>









