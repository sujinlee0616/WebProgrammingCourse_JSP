<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="16kb"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	현재 메모리 크기: <%=out.getBufferSize() %> byte<br>
	남아있는 메모리 크기: <%=out.getRemaining() %> byte<br>
	현재 사용중인 메모리 크기: <%=out.getBufferSize() - out.getRemaining() %> byte<br><br>
	
	현재 메모리 크기: <% out.println(out.getBufferSize()); %> byte<br>
	남아있는 메모리 크기: <% out.println(out.getRemaining()); %> byte<br>
	현재 사용중인 메모리 크기: <% out.println(out.getBufferSize() - out.getRemaining()); %> byte<br><br>
	
</body>
</html>

<%--
	<기본객체(내장객체)>
	====================================== 1~6: 중요 ==========================================
	1. request ★★★
	  - ★★사용자 요청정보★★, 사용자의 정보 ==> Session/Cookie 생성
	  - HttpServletRequest의 객체 
	    
	2. response ★★★
	  - 응답정보, header 정보, ★★이동정보(페이지)★★
				==========  ====================
				 파일 다운로드	 ★★sendRedirect()★★
				 (자료실 등)
				 ┗ 파일명, 파일 크기를 서버에 먼저 전송 => 이후 파일 전송   
		=> 데이터 전송 (header, data)
	  - HttpServletResponse의 객체 
	
	3. out 객체 
	  - HTML을 출력하는 메모리 (출력버퍼)
	  - JspWriter의 객체 
	 0) JSP 실행과정 
	    		 a.jsp						  java(Servlet)로 변경 a_jsp.java (한 번만 수행)
	      사용자 요청  ========> 톰캣이 파일을 가지고 온다 ==========================================> 컴파일 ===> a_jsp.class ===> 실행      
											  java파일이 없으면 생성하고, 기존에 생성된게 있으면 업데이트함 
		
		======> 출력버퍼에 HTML을 출력(out객체) ====> 사용자에게 전송
	 1) 기타
	  - 메모리에 출력 => print(), println()
	  - getBufferSize() => 8kb
	  - getRemaining() => 남아있는 버퍼 크기 
	 
 	4. application 객체 
 	  - 서버 정보, 버젼, 로그파일 관리, web.xml을 제어 
 	  
 	5. session 객체 ★★★★★
 	  - 추후 배울 예정
 	
 	6. pageContext 객체
 	  - JSP 관리 ==> include(), forward()
 	====================================== 7~9: 중요X ==============================================
 	7. config 객체
 	  - web.xml로 대체된다. 우리는 config 객체 안 쓰고 web.xml 사용할것임.
 	8. page 객체  = this
 	9. exception 객체 => try ~ catch 
 	===============================================================================================
 	※ Cookie는 내장객체가 아니다. 
 	 (내가 방문한 사이트, 라이센스 기간 등을 아는데 사용됨)	 
 --%>
 
 <%--
 	<내장객체 - 3. out 객체>
 	 - 메모리(출력버퍼) 관리 
 	 1) 메모리에 HTML/XML을 출력: out.println(), out.print(), <%= %>
 	 2) 메모리 크기 확인 가능: getBufferSize()
 	   - 디폴트 size: 8kb 
 	 3) 남아있는 메모리 확인 가능: getRemaining()
 	 4) 버퍼 삭제: flush(), clear()
 	   - JSP는 디폴트가 autoFlush여서 flush() 쓸 일 잘 없음. 
 	
 --%>
 
 
 
 
 
 
 
 
 
 
 
 
 
 