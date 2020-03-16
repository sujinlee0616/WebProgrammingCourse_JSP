<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.MovieDAO"/> <!-- 메모리 할당 -->
<%
	/* 페이지를 받아야. 근데 이 페이지는 main.jsp를 통해서 넘어온다.  */
	String strPage=request.getParameter("page");
	//             ======= 이 request는 main.jsp가 넘겨준 request임 
	// include된 jsp(ex. movie.jsp)는 자기를 include한 jsp(ex. main.jsp)와 request를 공유한다.
	// main.jsp에 request를 보내면 movie.jsp(main.jsp에 include된 애)는 그 request를 받을 수 있음
	// 하지만, movie.jsp(main.jsp에 include된 애)에 request를 보내면 main.jsp는 그 request를 받을 수 없음.
	// 모든 jsp는 request, response를 가짐. 
	if(strPage==null)
		strPage="1";
	int curpage=Integer.parseInt(strPage);
	List<MovieBean> list = dao.movieListData(curpage);
	int totalpage=dao.movieTotalPage();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row1{
	margin: 0px auto;
	width: 960px;
}
</style>
</head>
<body>
	<h1>Movie</h1>
	<p>영화 페이지입니다.</p>
	<div class="row">
	<%
		for(MovieBean mb:list)
		{
	%>
		<div class="col-md-4">
		    <div class="thumbnail">
		      <a href="/w3images/lights.jpg">
		        <img src="<%=mb.getPoster() %>" alt="Lights" style="width:100%">
		        <div class="caption">
		          <p><%=mb.getTitle() %></p>
		        </div>
		      </a>
		    </div>
	  	</div>
	<%
		} 
	%>
	</div>
	<div class="row row1">
		<a href="main.jsp?mode=1&page=<%=curpage>1?curpage-1:curpage %>" class="btn btn-sm btn-primary">이전</a>
		<%=curpage %> page / <%=totalpage %>
		<a href="main.jsp?mode=1&page=<%=curpage<totalpage?curpage+1:curpage %>" class="btn btn-sm btn-primary">다음</a>
		<!-- request를 main.jsp로 보내야 -->
	</div>
</body>
</html>





