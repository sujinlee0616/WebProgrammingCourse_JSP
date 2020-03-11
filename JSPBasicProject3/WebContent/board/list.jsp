<!-- 목록출력 -->
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%
	BoardDAO dao = new BoardDAO();
	// 사용자가 요청한 페이지를 받는다 
	String strPage=request.getParameter("page");  // 사용자가 보내줬으니까 request 안에 싣어져 있음 (사용자가 보낸 모든 값은 request 안에 有...)
	
	if(strPage==null){
		strPage="1";
	}
	
	int curpage=Integer.parseInt(strPage);
	
	List<BoardVO> list = dao.boardListData(curpage);
	
	int count=dao.boardRowCount();
	int totalpage = (int)(Math.ceil(count/10.0));
	
	count = count-((curpage*10)-10);
	/* 다음 페이지 넘어갈 때마다 10씩 빼야 하니까... */
	/* ex) 전체 게시물 14개, 1페이지당 게시물 10개라면 ==> 1페이지: no 14~5,  2페이지: no 4~1 */
	
	
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
	width: 800px;
}
h2 {
	text-align: center;
}

</style>
</head>
<body>
	<div class="container">
		<h2>묻고 답하기 (답변형 게시판)</h2>
		<div class="row">
			<table class="table">
				<tr>
					<td><a href="insert.jsp" class="btn btn-sm btn-default">새 글</a></td>
				</tr>
			</table>
			<table class="table table-striped">
				<tr>
					<th width=10% class="text-center">번호</th>
					<th width=45% class="text-center">제목</th>
					<th width=15% class="text-center">이름</th>
					<th width=20% class="text-center">작성일</th>
					<th width=15% class="text-center">조회수</th>
				</tr>
				<%
					for(BoardVO vo:list)
					{
				%>
				<tr>
					<td width=10% class="text-center"><%=count-- %></td>
					<td width=45% class="text-left">
						<%
							if(vo.getGroup_tab()>0) // group_tab이 1 이상이면 간격 띄워주고 답변 이미지 달아줘야
							{ 
								for(int i=0;i<vo.getGroup_tab();i++) // tab 값 만큼 간격 반복해서 줌 
								{
									out.println("&nbsp;&nbsp;&nbsp;");
								}
						%>
							<img src="icon_reply.gif">
						<%
								
							}
						%>
						<%
							// 관리자가 삭제한 게시물(자식이 있는 글을 삭제)이라면 1) 제목을 회색으로 노출. 제목에 a태그 없다. 
							String msg="관리자가 삭제한 게시물입니다.";
							if(msg.equals(vo.getSubject()))  
							{
						%>
								<span style="color: #999999;"><%=vo.getSubject() %></span> 
						<%
							}
							// 관리자가 삭제한 게시물이 아니라면(자식이 없는 글을 삭제) 제목에 a태그 걸어서 노출. 
							else  
							{
						%>
								<a href="detail.jsp?no=<%=vo.getNo()%>&page=<%=curpage%>"><%=vo.getSubject() %></a>
						<%
								String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date()); // 오늘 날짜를 yyyy-MM-dd 형식으로 바꿔라
								if(today.equals(vo.getRegdate().toString()))
								{
						%>
									<sup><img src="new.gif"></sup>
						<%
								}
							}
						%>
					</td>
					<td width=15% class="text-center"><%=vo.getName() %></td>
					<td width=20% class="text-center"><%=vo.getRegdate() %></td>
					<td width=15% class="text-center"><%=vo.getHit() %></td>
				</tr>
				<%
					}
				%>
			</table>
			<table class="table">
				<tr>
					<td class="text-center">
						<a href="list.jsp?page=<%=curpage>1?curpage-1:curpage %>" class="btn btn-sm btn-default">이전</a>
							<%=curpage %> page / <%=totalpage %> pages
						<a href="list.jsp?page=<%=curpage<totalpage?curpage+1:curpage %>" class="btn btn-sm btn-default">다음</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
</body>
</html>







