<!-- Call by reference: 값을 주소에 싣어준다...  -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.temp.*,java.util.*"%>
<%
	SawonManager sm=new SawonManager();
	sm.sawonAllData(request); // SawonManager의 request를 받는다.★
	List<Sawon> list = (List<Sawon>)request.getAttribute("list");
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul>
	<%
		for(Sawon s:list)
		{
	%>
			<li><%=s.getName()%>-<%=s.getDept() %></li>
	<%
		}
	%>
	</ul>

</body>
</html>
<!-- 
	<정리>
	1. JSP 생성 => jsp 제작 => java 생성 => 컴파일 (.class) => JVM 읽어서 메모리 출력(html, xml) => 브라우저에 출력 
	  => HTML(화면UI) / XML(문서)
	※ XML
	 - 좀 틀려도 출력되는 HTML과 달리, XML은 정확히 일치해야 나옴... XML은 일종의 ☆문서 데이터베이스.☆
	 - XML은 언어 및 운영체제에 영향받지 않는다. 
	2. < % % > < % = % >
	3. 기본객체 (request, response, application, session) 
	4. <jsp:~> 액션태그 
	5. URL => GET/POST 방식, 한글변환 
	6. error 코드
	7. 사용범위 request, session 
-->






