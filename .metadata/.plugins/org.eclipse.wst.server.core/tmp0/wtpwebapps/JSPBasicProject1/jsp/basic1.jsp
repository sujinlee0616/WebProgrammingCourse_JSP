<!-- 2020.03.09 (월) 수업 -->
<%-- 페이지 지시자 --%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp" buffer="16kb" autoFlush="true" session="true"
    info="게시판의 목록 출력"
    %>     
 
 
<%-- JSP 주석 (단일주석) --%>
<%--	
	// JSP 이중주석 
 --%>
 
<%--
	<%
		int a=10;
		// int b=20;
		/* int c=30; */
	%> --%>


<%--
<JSP>
 - Java Server Page : 서버에서 실행되는 Java 파일 
			   ==== web에서는 파일이라고 하지 않고 page 라고 한다. 
					(일반적인 간단한 싸이트는 보통 75 페이지 정도.)
1) 실행 
	- a.jsp ==자바 파일로 변경 ==> a_jsp.java == 컴파일 ==> a_jsp.class ==> 실행
2) 지시자 (Directive)
	(1) page 지시자 
		- JSP 파일에 대한 정보를 갖고 있음. 
		- 형식:
		   <%@ page 속성=값 속성=값 .... %>
		- 속성은 정해져 있음.  
	(2) include 지시자 
		- JSP 안에 다른 JSP를 첨부할 때. (조립)
		- 형식: 
		<%@ include file=""%>
	(3) 태그 라이브러리 
		- 형식: 
			 <%@ taglib prefix="" uri=""%>
			 <c:if>
			 <c:forEach>
			 <c:choose>
			 <c:set>
			 <c:out>
3) 자바 코딩
	(0) 형식: 
		- <% %> : 스크립트릿: 일반 자바 코딩
		- <%! %> : 선언식 : 멤버변수, 메소드를 만들 경우 
		- <%= %> : 표현식: 화면 출력 (out.println())
	(1) 자바 파일 => DB연동, VO(자바빈)  
4) 내장객체 (기본객체)
	- 미리 생성되어 있는 객체. 
	(1) ★request: 사용자 요청 값, 사용자 정보(IP, PORT) 
	(2) ★response: 서버에서 응답 
	(3) ★session: 서버에 사용자 정보나 기타 내용을 저장 
	(4) application: 서버 정보 
	(5) out: 출력 버퍼에 대한 정보 (메모리) ==> <%= %>, ${}로 바뀔 수 있음. 
	(6) ★pageContext: JPS파일-JSP파일간 연결시킬 때 사용. => include, forward 기법 
	(7) page: this 역할. 
	(8) config: 환경설정 => web.xml에서 설정 
	(9) exception: 예외처리 => try ~ catch 
5) 에러페이지 
	- 한 번에 처리 ==> page 지시자에 존재(errorPage="이동할 파일명")
	- 분류해서 처리 ==> Tomcat이 알고 있어야 한다.(web.xml)
6) 액션태그
	- <jsp:include>: HTML만 번역. 출력되는 결과물인 HTML-HTML만 합침. : 더 많이 사용됨 
		<==> <%@ include file=""%> : JSP-JSP 코드를 합침. 상대적으로 잘 사용X.  
	- <jsp:forward>: 화면은 바뀌지만 url 주소는 바뀌지 않음. request를 계속 공유. Spring MVC에서 많이 사용. 
		<==> sendRedirect : url이 바뀐다. request가 새로 생성됨. 
	- <jsp:useBean>: 메모리 할당 ==> 앞으로는 new가 아니라 useBean 사용 
	- <jsp:setProperty>: 요청값을 받아서 처리 
		ex) 회원가입 시 입력하는 값이 20개. get parameter하면 20번 해야 하는 걸 setProperty 쓰면 한 방에 처리 가능함.
========================================== 여기까지가 Model1 방식 ======================================  
7) EL, JSTL
8) MVC 
 --%>
 
 
<%--
<page 지시자> : <%@ page ...%>
 - JSP 파일에 대한 정보
 - 변환코드
   ┗ contentType="text/html; charset=UTF-8"
   ┗ => response.setContentType("text/html; charset=UTF-8")
   ┗ => default: contentType="text/html; charset=ISO-8859-1"
   ┗ 변환형식
     HTML => text/html
     XML => text/xml
     errorPage: error가 있는 경우에 지정한 파일로 이동 
     import: 라이브러리를 읽어 올 경우, 사용자 정의 클래스
     ======= default: java.lang, javax.servlt.* 
     		  ==> 이 둘은 생략해도 사용 가능. 이 둘 외에는 import 해야 사용 가능.
     page 지시자의 속성은 한 번만 사용해야함. 
         ex)  <%@ page language="java" contentType="text/html; charset=UTF-8"
    			pageEncoding="UTF-8" errorPage="error_1.jsp" errorPage="error_2.jsp" %>
    		  : Error. errorPage 속성을 두 번 사용했기 때문.
     ==> 여러개를 import해야 할 경우에는 아래와 같이 ,로 구분하거나, page를 여러개로 나누고 import 하나씩 해준다.
         	방법1) <% page import="java.io.*, java.util.*"%>
         	방법2) <% page import="java.io.*"%>
         		  <% page import="java.util.*"%>
         import 외의 다른 애들은 속성을 두 번 이상 쓰는 것이 아예 불가능하다.
     buffer: 출력버퍼. (HTML을 출력하는 메모리 공간) => 8kb (디폴트 크기가 8kb. 더 크게 설정해도 됨.)
     
     
     
     
        
 --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- HTML 주석  -->
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>안녕하세요 JSP</h1>
	<%
		int a=10/0;
	%>
	<%= a %>
	<%-- 실행시키면 error.jsp가 실행됨. forward 방식.(url이 안 바뀌었는데 내용은 바뀌었으니까 forward방식.) --%>
</body>
</html>




