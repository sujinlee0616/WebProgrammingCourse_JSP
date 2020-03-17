<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*, java.util.*"%>
<!-- useBean: 메모리 할당 -->
<jsp:useBean id="dao" class="com.sist.dao.MovieDAO"></jsp:useBean>
<!-- id=객체명 ==> 객체를 생성했음 -->
<%
	String strPage=request.getParameter("page");
	if(strPage==null)
		strPage="1";
	
	int curpage=Integer.parseInt(strPage);
	
	List<MovieBean> list=dao.movieListData(curpage);
	int totalpage=dao.movieTotalPage();
	
	/* <curpage에 따른 페이지네이션 시작/끝 번호 구하기> */
	final int BLOCK=10;
	int startPage=((curpage-1)/BLOCK*BLOCK)+1; // ex. curpage=5 ==> int startPage=( int(4)/int(10) *10)+1 ==> 0*10+1 ==> 1
	int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK; // ex. curpage=5 ==> int endPage = 0+10=10
	/* curpage: 현재 페이지 */
	/* 1<=curpage<=10 이면 startPage=1, endPage=10이어야  */
	/* 11<=curpage<=20 이면 startPage=11, endPage=20이어야  */
	/* 페이지네이션 한 번에 나오는 갯수: 10 ==> BLOCK 이라고 변수 잡고 startPage, endPage 구하는 수식 세워줬음 */
	
	if(endPage>totalpage)
		endPage=totalpage;
			
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1 class="text-center">영화 목록</h1>
		<div class="row" style="text-align: right;">
			<%=curpage %> page / <%=totalpage %> pages
		</div>
		<div class="row">
			<%
				for(MovieBean vo:list)
				{
			%>
				<div class="col-md-4">
					<div class="panel panel-primary">
				      <div class="panel-heading">
					      <%
					      String temp=vo.getTitle();
						    if(temp.length()>22)
						    {
						    	temp=temp.substring(0,22)+"...";  	
						    }
					      %>
					      <%=temp %>
					      <br>
					      
				      </div>
				      <div class="panel-body">
				      	<img src="<%=vo.getPoster() %>" width="320px" height="200px">
				      </div>
				    </div>
				</div>
			<% 
				}
			%>
		</div>
		<div class="row" style="text-align: center;">
			<ul class="pagination">
			<li><a href="movie.jsp?page=1">&lt;&lt;</a></li>
			  <%
			  	if(curpage>BLOCK) /* curpage>BLOCK 일때만 '<(이전)' 버튼 노출 */
			  	{
			  %>
			  		<li><a href="movie.jsp?page=<%=startPage-1%>">&lt;</a></li>
			  		<!-- 
			  			ex) 15 페이지에서 Pagination의 '<' 버튼 클릭 
				  			==> 10(startPage)-1 = 9페이지 화면으로 이동한다.
				  			==> curpage=9가 되므로 startPage=1이 되고 endPage=10이 됨 
			  		 -->
			  <%
			  	}
			  %>
			  <%
			  	for(int i=startPage; i<=endPage; i++)
			  	{
			  		if(curpage==i)
			  		{
			  %>
			  		<li class="active"><a href="movie.jsp?page=<%=i%>"><%=i %></a></li>
			  <%
			  		}
			  		else
			  		{
			  %>
			  		<li><a href="movie.jsp?page=<%=i%>"><%=i %></a></li>
			  <%
			  		}
			  	}
			  %>
			  <%
			  	if(endPage<totalpage)
			  	{
			  %>
			  		<li><a href="movie.jsp?page=<%=endPage+1%>">&gt;</a></li>
			  <%
			  	}
			  %>
			  <li><a href="movie.jsp?page=<%=totalpage%>">&gt;&gt;</a></li>
			  <%-- <li style="display: block;"><a href=""><%=curpage %> page / <%=totalpage %> pages</a></li> --%>
			</ul>
		</div>
	</div>
</body>
</html>





