<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*, java.util.*"%>
<jsp:useBean id="dao" class="com.sist.dao.FoodDAO"></jsp:useBean>
<%
	String cateno=request.getParameter("cateno");
	CategoryBean cb=dao.categoryInfoData(Integer.parseInt(cateno));
	List<FoodHouseBean> list=dao.foodCategoryList(Integer.parseInt(cateno));
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
		<div class="row">
			<div class="jumbotron">
				<h1 class="text-center"><%=cb.getTitle() %></h1>
				<h3 class="text-center"><%=cb.getSubject() %></h3>
			</div>
		</div>
		<div class="row">
			<table class="table">
				<tr>
					<td>
					<%
						for(FoodHouseBean vo:list)
						{
					%>
						<table class="table table-hover">
							<tr>
							<td width="30%" class="text-center" rowspan="3">
									<a href="detail.jsp?no=<%=vo.getNo()%>">
										<img src="<%=vo.getImage() %>" width="280px" height="180px" class="img-rounded">
									</a>
								</td>
								<td width="70%">
									<a href="detail.jsp?no=<%=vo.getNo()%>">
										<h3><%=vo.getTitle() %><span style="color: #FC6;"><%=vo.getScore() %></span></h3>
									</a>
								</td>
							</tr>
							<tr>
								<td width=70%>
								<%
									String temp=vo.getAddress();
									String a1=temp.substring(0,temp.indexOf("지번")); // substring 시작:0, 끝: '지번' 앞까지
									String a2=temp.substring(temp.indexOf("지번")+3); // substring 시작번호: "지번"위치부터 +3
									// vo.getAddress()의 형태
									// : 서울특별시 강남구 강남대로156길 28-2 1F 지번 서울시 강남구 신사동 516-2 1F
									// ==> '지번'을 기준으로 잘라서 나눴음
								%>
								<%=a1 %><br>
								<%=a2 %>
								</td>
							</tr>
							<tr>
								<td width=70%><%=vo.getTel() %></td>
							</tr>
						</table>
					<%
						}
					%>
					</td>
				</tr>
			</table>
		</div>
		<div class="row">
			<table class="table">
				<tr>
					<td class="text-right">
						<a href="category.jsp" class="btn btn-lg btn-primary">목록</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>









