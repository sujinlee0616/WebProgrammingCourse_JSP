<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%
	Date date = new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-M-d"); 
	String today=sdf.format(date);
	
	StringTokenizer st=new StringTokenizer(today,"-");
	String strYear=st.nextToken();
	String strMonth=st.nextToken();
	String strDay=st.nextToken();
	String sy=request.getParameter("year");
	if(sy==null)
		sy=strYear;
	String sm=request.getParameter("month");
	if(sm==null)
		sm=strMonth;
	
	int year=Integer.parseInt(sy);
	int month=Integer.parseInt(sm);
	int day=Integer.parseInt(strDay);
	
	// <월의 1일이 무슨 요일인지 구하기>
	// 1년도 1월 1일 (월) ~ 요일을 알고자 하는 날짜까지의 총 일수를 구함
	// step1. 전년도 까지의 일수 계산 
	int total=(year-1)*365
	 		+ (year-1)/4
	 		- (year-1)/100
	 		+ (year-1)/400; // 윤년처리 
	// step2. 전월 까지의 일수 계산
	int[] lastDay={31,28,31,30,31,30,
				   31,31,30,31,30,31};
	if( (year%4==0 && year%100!=0) || (year%400==0) )
		lastDay[1]=29;
	else
		lastDay[1]=28;
	
	for(int i=0; i<month-1;i++)
	{
		total+=lastDay[i];
	}
	total++;
	
	// step3. 달의 1일자의 요일 구하기
	int week=total%7;  // week: 1일자의 요일 
	
	String[] strWeek={"일","월","화","수","목","금","토"};
	
	
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
.row{margin: 0px auto; width: 900px;}
</style>
<script>
function change(){
	var f=document.frm; // 폼 태그를 갖고 있음
	f.submit();
}
</script>
</head>
<body>
	<div class="container">
		<h1 class="text-center"><%=session.getAttribute("name")%>(<%=session.getAttribute("id") %>)&nbsp;<%=sy %>년  <%=sm %>월 일정</h1>
		<div class="row">
			<form method="POST" name="frm" action="diary.jsp">
				<table class="table">
					<tr>
						<td class="text-left">
							<select name="year" onchange="change()">
								<%
									for(int i=2020; i<=2030; i++)
									{
								%>
										<option <%=i==year?"selected":"" %>><%=i %></option>
								<%		
									}
								
								%>
							</select> 년도&nbsp;
							<select name="month" onchange="change()">
								<%
									for(int i=1;i<=12;i++)
									{
								%>
										<option <%=i==month?"selected":"" %>><%=i %></option>
								<%
									}
								%>
							</select>월
						</td>
					</tr>
				</table>
			</form>
			<table class="table table-bordered">
				<!-- 1. 달력첫줄 요일 표시: 평일은 검정색, 토요일은 파란색, 일요일은 빨간색으로 출력 -->
				<tr class="danger" height="35">		
				<%
					String color="";
					int k=0;
					for(String s:strWeek)
					{	if(k==0)
							color= "red";
						else if(k==6)
							color= "blue";
						else
							color= "black";
				%>
					<th class="text-center"><h1><font color="<%=color%>"><%=s %></font></h1></th>
				<%
						k++;
					}
				%>
				</tr>
				<%
					color="black";
					for(int i=1;i<=lastDay[month-1];i++)
					{
						/* 달력 날짜에 요일별 색상 설정 */
						if(week==0)
							color="red";
						else if(week==6)
							color="blue";
						else 
							color="black";
						
						/* 해당 월의 1일이 시작하는 요일 전에는 공백으로 줌 */
						if(i==1)
						{
				%>
							<tr>
							<%
								for(int j=0;j<week;j++)  /* week: 1일자의 요일  */
								{
							%>
								<td height="150" class="text-left" width=100 v-align="top">&nbsp;</td>
							<%
								}
							%>
				<%
						}
						
						/* 오늘 날짜 초록색으로 표시 */
						String back="white";
						if(i==day)
							back="success";
				%>
						<td height="150" class="text-left <%=back%>" width=100 valing="top">
							<h3>
								<font color="<%=color %>">
								<a href="diary_login.jsp?"><%=i %></a>
								</font>
							</h3>
						</td>
				<%
						week++;
				if(week>6)
				{
					week=0;
				%>
					</tr>
					<tr>
				<%
				}
					}
				%>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>

<%--
	<Session>
	- 서버에서 사용자의 일부 정보를 저장
	  ==> HttpSession: 각 클라이언트마다 1개만 배정 ==> request를 이용해서  session을 얻어 온다. 
	- 관련된 메소드(기능)
	  1) 저장: setAttribute(key, 값)
	   - Cookie와의 차이점 주의
	   - Cookie: Cookie cookie = new Cookie(key, 값): 값은 String만 저장 가능!
	     ==> 값을 가지고 올 때 return 형은 String
	   - Session: setAttribute(key, 값): 값은 Object 저장 
	     ==> 값을 가지고 올 때 return 형은 Object
	  2) 저장된 값 읽기: Object getAttribute("key") 
	     ==> key 반드시 형 변환해야 (제네릭스가 존재하지 않는다.)  
	  3) 기간 설정: setMaxInactiveInterval() 
	   - default: 30분(1800s)  
	  4) 저장된 내용 삭제 
	   (1) 한 개를 삭제 
	    - removeAttribute("key")
	    - ex) 장바구니 
	   (2) 전체를 삭제 
	    - invalidate()
	    - ex) 로그인 정보를 로그아웃 시 삭제 
	
	※ <MVC>session 														 request, session
	   클라이언트(데이터 전송) === Controller(Servlet) ==> Model(Java) =================> JSP (View)    

--%>