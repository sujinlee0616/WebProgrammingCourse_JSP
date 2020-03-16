<!-- [JSP 액션태그로 코딩] -->
<!-- Java보다 훨씬 코드가 더 짧고 간편한 것을 알 수 있다. -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.member.*"%>
<%
	try
	{
		request.setCharacterEncoding("UTF-8");
	}catch(Exception ex){}
%>
<jsp:useBean id="mb" class="com.sist.member.MemberBean"> <!-- 메모리 할당. id="객체명" class="패키지명"-->
	<jsp:setProperty name="mb" property="*"/> <!-- useBean의 id=serProperty의 name값 = 객체명 = 일치해야 -->
</jsp:useBean>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전송받은 데이터 출력</h1>
	이름: <%=mb.getName() %><br>
	이름: <jsp:getProperty property="name" name="mb"/> <br>
	<!-- 위의 둘 다 똑같은 코딩이지만 getProperty 쓰는 것 보다 위의 방식이 더 편함 -->
	성별: <%=mb.getSex() %><br>
	주소: <%=mb.getAddr() %><br>
	전화: <%=mb.getTel() %><br>
	나이: <%=mb.getAge() %><br>
</body>
</html>







