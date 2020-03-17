<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.FoodDAO"></jsp:useBean>
<%
	String no=request.getParameter("no");
	FoodHouseBean vo=dao.foodDetailData(Integer.parseInt(no));
	/* Cookie */
	/* 1. 생성: Cookie cookie=new Cookie(key, value) */
	Cookie cookie=new Cookie("food"+no,no); 
	/* 2. 저장기간 설정 : cookie.setMaxAge(저장기간(ms)); */
	cookie.setMaxAge(60*60*24); /* 하루동안 저장해라 */
	/* 3.  */
	response.addCookie(cookie);
%>
<%-- Google Charts > Pie Chart > 3D Pie Chart 
https://developers.google.com/chart/interactive/docs/gallery/piechart --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        /* var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['Work',     11],
          ['Eat',      2],
          ['Commute',  2],
          ['Watch TV', 2],
          ['Sleep',    7]
        ]); */
        var data = google.visualization.arrayToDataTable(<%=vo.getTag()%>);

        var options = {
          title: '리뷰 통계',
          is3D: true,
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
      }
    </script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<table class="table">
					<tr>
						<%
							StringTokenizer st=new StringTokenizer(vo.getImage(),"^");
							/* 이미지가, 데이터에, 여러개가 들어있고 '^'으로 구분되어 있음 */
							while(st.hasMoreTokens())
							{
						%>
							<td class="text-center">
								<img src="<%=st.nextToken()%>" width=100%>
							</td>
						<%
							}
						%>
					</tr>
					<tr>
						<td class="text-center" colspan="5">
							<h3><%=vo.getTitle() %>&nbsp;<span style="color: #FC6;"><%=vo.getScore() %></span></h3>
						</td>
					</tr>
					<tr>
						<td class="text-right" width=15%>주소</td>
						<td colspan="4">
							<%
								String temp=vo.getAddress();
								String a1=temp.substring(0,temp.indexOf("지번")); // substring 시작:0, 끝: '지번' 앞까지
								String a2=temp.substring(temp.indexOf("지번")+3); // substring 시작번호: "지번"위치부터 +3
								// vo.getAddress()의 형태
								// : 서울특별시 강남구 강남대로156길 28-2 1F 지번 서울시 강남구 신사동 516-2 1F
								// ==> '지번'을 기준으로 잘라서 나눴음
							%>
							<%=a1 %><br>
							<sub style="color: grey;"><%=a2 %></sub>
						</td>
					</tr>
					<tr>
						<td class="text-right" width=15%>전화번호</td>
						<td colspan="4"><%=vo.getTel() %></td>
					</tr>
					<tr>
						<td class="text-right" width=15%>음식종류</td>
						<td colspan="4"><%=vo.getType() %></td>
					</tr>
					<tr>
						<td class="text-right" width=15%>가격대</td>
						<td colspan="4"><%=vo.getPrice() %></td>
					</tr>
					<tr>
						<td class="text-center" colspan=5>
							<div id="piechart_3d" style="width: 900px; height: 500px;"></div>
						</td>
					</tr>
					<tr>
						<td class="text-right" colspan=5>
							<a href="#" class="btn btn-sm btn-danger">찜</a>
							<a href="#" class="btn btn-sm btn-success">예약</a>
							<a href="category.jsp" class="btn btn-sm btn-info">목록</a>
						</td>
					</tr>
				</table>
			</div>
			<div class="col-md-4">
			</div>
		</div>
	</div>
</body>
</html>

















