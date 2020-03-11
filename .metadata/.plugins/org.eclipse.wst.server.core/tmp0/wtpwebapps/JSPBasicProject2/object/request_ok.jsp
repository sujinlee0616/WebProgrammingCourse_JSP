<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 데이터 받기 시작 (request.jsp)
	// 한글 변환 
	try
	{
			request.setCharacterEncoding("UTF-8");
	}
	catch(Exception ex){}
	String user_name=request.getParameter("user_name");
	String sex=request.getParameter("sex");
	String tel1=request.getParameter("tel1");
	String[] hobby=request.getParameterValues("hobby"); // <= 여러개를 동시에 받을 때 getParameterValues
	String content=request.getParameter("content");
	// 여러개를 동시에 받는 Checkbox는 getParameterValues 사용. 
	// 나머지는 값 1개씩만 받음 ==> getParameter
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전송받은 데이터 정보</h1>
	이름:<%=user_name %><br>
	성별:<%=sex %><br>
	전화:<%=tel1 %><br>
	소개:<%=content %><br>
	취미:<ul>
			<%
			try
			{
				for(int i=0; i<hobby.length;i++){
			%>
				<li><%=hobby[i] %></li>
			<%
				}
			}
			catch(Exception ex)
			{
			%>
				<span style="color:red;">취미가 없습니다.</span>	
			<%	
			}
			%>
		</ul>
		<!-- try catch 안 해주면, 취미값이 null 일 때 500 에러남 -->
</body>
</html>











