<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>산술연산자</h3>
	<b>문자</b><br>
	<%= "Hello" %><br>
	${"Hello" }<br>
	<br>
	<b>숫자</b><br>
	<%=10 %><br>
	${10 }<br>
	<br>
	<b>덧셈</b><br>
	<%=10+10 %><br>
	${10+10 }<br>
	<br>
	<b>★★나누기★★</b><br>
	<%=10/3 %><br>
	${10/3 }<br>
	<br>
	<b>문자열결합</b><br>
	<%="Hello, "+"world" %><br>
	<!-- $ { "Hello, "+"world" }<br> --> <!-- Error. EL은 +로 문자열 결합 불가 -->   
	${"Hello, "+="world" }<br>  <!-- EL은 +=로 문자열 결합한다. -->   
	<br>
	<b>형변환</b><br>
	<%="100"+"10" %><br>  <!-- 결과:10010 (문자)  -->
	${"100"+"10" }<br>	  <!-- 결과: 110. -->
	<!--  EL에서는 "100", "10" 각각을 int로 변환시킨 뒤 더한다  -->
	${"100"+"10" }<br>	  <!-- 큰 따옴표/작은 따옴표 둘 다 사용 가능 -->
	<br>
	<b>NULL 연산</b><br>
	<%-- <%=null+10 %><br> --%> <!-- Error. -->   
	${null+10 }<br> <!-- null은 0으로 변경된다 -->
	<br>
	<br>
	<br>
	<h3>비교연산자</h3>
	
	
	
</body>
</html>


<%-- 
	<EL을 사용한 연산처리> 
	${연산자}
	
	1. 산술연산자 +,-,*,/(div),%(mod) 
	 - JAVA의 +: 덧셈, 문자열 결합
	 - JSP EL의 +: 덧셈만. (문자열 결합X) 
	       ※ JSP EL의 문자열 결합: '+=' 사용 
		 ex) ${"Hello, "+="world" } 
	
	2. 비교연산자 ==, eq, !=, ne <,>,<=,>=
	 - 결과값: true/false 
	 1) ==, eq 
	  - 문자열 or 숫자가 같다  
	 	ex) ${requestScope.id=='admin'}
	 	ex) ${requestScope.id eq 'admin'}
	 2) !=, ne 
	  - 문자열 or 숫자 가 같지 않다
	  - ne: not equal
	    ex) ${requestScope.id!='admin'}
	    ex) ${requestScope.id ne 'admin'}
	 3) <, lt
	  - lt: less than 
	 4) >, gt
	  - gt: greater than
	 5) <=, le
	  - le: less than or equal to
	 6) >=, ge
	  - ge: greater than or equal to
	  
	3. 논리연산자 &&,and,||,or,!,not 
	 - 결과값: true/false 
	 
	4. empty 연산자 
	 - ArrayList에 값이 있는지 없는지의 여부를 확인
	 - null 또는 ""(빈 공백)이면 true를 리턴한다.
	 - 결과값: true/false 
	 - ex) ${empty list}  
	 
	5. 삼항연산자
	 - 형식: ${조건?true일때값:false일때값}
	
	6. 문자열 결합 연산자 +=
	   ex) ${"Hello, "+="world" } 
	
  --%>

  
  
  
  
  
  

  
  