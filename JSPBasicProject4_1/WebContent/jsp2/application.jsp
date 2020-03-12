<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	RealPath: <%=application.getRealPath("/jsp2/application.jsp") %><br>
	서버이름: <%=application.getServerInfo() %><br>
	Servlet 버젼: <%=application.getMajorVersion() %>.<%=application.getMinorVersion() %><br>
	JSP 버젼: 2.3<br>
	Oracle 버젼: 11g<br>
	<%
		String driver=application.getInitParameter("driver");
		String url=application.getInitParameter("url");
		String username=application.getInitParameter("username");
		String pwd=application.getInitParameter("password");
		
		application.log("Driver: "+driver);
		application.log("Url: "+url);
		application.log("Username: "+username);
		application.log("Password: "+pwd);
		// Eclipse 하단 console창에 log가 찍히는 것을 볼 수 있다. 
	%>
</body>
</html>
<%-- 
	<내장객체 - 4. application 객체>
	 - ServletContext
	       ※ Servlet = Server(서버) + let(가벼운 프로그램) ==> web
	       ※ 이런식으로 'let' 들어가는 프로그램들 많다. ex) MIDlet, Applet 등
	 - 서버와 관련
	 1. 서버정보
	  - getServerInfo: 서버이름 (ex.톰캣) 
	  - getMajorVersion(), getMinorVersion(): Servlet 버젼정보 
	    ex) 3.1버젼 ==> getMajorVersion=3, getMinorVersion=1
	        2.7버젼 ==> getMajorVersion=2, getMinorVersion=7
	 2. 자원정보
	  - getRealPath() 
	 3. web.xml을 읽어서 처리 ==> log 파일과 관련
	  - getInitParameter()    
	    
--%>

<%--
	<기타>
 		 Java SE: Application  
	     Java EE: 기업용 환경(WEB)
	     Java ME: 모바일 
	     
	     [Servlet vs JSP]
		 - 브라우저 ==> 사용자가 데이터 전송 ==> 받을 수 있는 파일 (Servlet, JSP)
		 - 데이터 ==> Servlet ==> Java ==> JSP
		 			======      =====    ====
		 			값 받아서 연결	DB처리 	  화면 출력
		 								 View 		===> MVC 구조 (개발, 유지보수)
	     1. Servlet
	      - 연결. 보안이 필요한 것들. ==> 라이브러리는 Servlet으로 만들어져 있음
	     2. JSP
	      - 화면(HTML) 출력. 
 --%>








