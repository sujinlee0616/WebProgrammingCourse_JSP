<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	int sex=1;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Java로 코딩을 할 경우</h2>
	<%
		if(sex==1)
		{
	%>
			<p>남자입니다.</p>
	<%
		}
		else 
		{
	%>
			<p>남자입니다.</p>
	<%
		}
	%>
	<br>
	<c:set var="sex" value="2"/>
	<!-- request.setAttribute("sex",2); -->
	<h2>JSTL을 이용</h2>
	<c:if test="${sex==1 }">
		남자입니다
	</c:if>
	<c:if test="${sex==2 }">
		여자입니다
	</c:if>
</body>
</html>

<%-- 
	<JSTL>
	
	0. 특징 
	- JSTL은 XML로 제작되었으므로, 태그를 열고 닫아야 한다.
	- 지원하는 속성(var, items, ...) 외의 다른 속성을 이용하면 에러가 난다.  
	- 속성값은 반드시 ""를 사용해야 한다. 
	  ex) <c:set var=id value=admin> // (X) Error. 
	      <c:set var="id" value="admin"> // (O)
	
	1. core 
	 - import: <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	 1) 변수 선언: <c:set>
	   - <c:set var="key" value="value"> 는
	     request.setAttribute(key,value) 와 동일 
	     ==> ${id}
	   - 참고) <c:out value="test"> => out.println()
	          =================== ${}
	 
	 2) 제어문
	 	(1) <c:if>
	 	  - else가 존재하지 않는다.
	 	  - [Java if문 형식] 
		 	    if(조건문)
		 	    {
		 	    	실행문장
		 	    }
	 	  - [<c:if> 사용]
		 	    <c:if test="조건문">
		 	    	실행문장
		 	    </c:if>
	 	    
	 	(2) <c:forEach>
	 	  - 변수가 감소할 수 없다. (step은 항상 양수)
	 	  - [Java for문 형식]
		 	    for(int i=1; i<=10; i++)
		 	    {
		 	    }
	 	  - [<c:forEach> 형식]
		 	    <c:forEach var="i" begin="1" end="10" step="1">
		 	    							 =======  ======== step="1"인 경우 생략 가능 
		 	    							 [주의] <= 임!!!
			</c:forEach>
		  - [Java forEach문 형식] 
			    for(MovieVO vo:list)
			    {
			    }
		  - [<c:forEach> 형식]
			    <c:forEach var="vo" items="list">
			    </c:forEach>	 	    
			
	 	(3) <c:choose>
	 	 - 다중조건문, 선택문 작성할 때 사용
	 	 - 형식) 
	 	 		<c:choose>
	 	 			<c:when test="조건문">실행문장</c:when> // Java에서의 if()와 동일 
	 	 			<c:when test="조건문">실행문장</c:when> // Java에서의 else if()와 동일 
	 	 			<c:when test="조건문">실행문장</c:when> // Java에서의 else if()와 동일 
	 	 			<c:when test="조건문">실행문장</c:when> // Java에서의 else if()와 동일 
	 	 			<c:otherwise>실행문장</c:otherwise> // Java에서의 else와 동일 
	 	 		</c:choose>
	 	
	 	(4) <c:forTokens>
	 	 - StringTokenizer와 기능 동일 
	 	 - 형식) 
	 	 		<c:forTokens var="s" value="red,blue,green" delimt=",">
	 	 - 
	 	
	 3) URL  	   
	   - import: <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/fmt"%>
	   - <c:redirect url="">: 화면 이동 (response.sendRecirect()와 동일)
	   
	2. fmt
	 - import: <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/functions"%>
	 
	3. fn ==> String, Collection
	          ${fn:title.substring()}
	
	
	======================== 사용X =========================
	- JSTL에서 sql,xml은 사용하지X. 책에도 나오지X. ==> 알 필요 X
	4. sql ===========> DAO 
	5. xml ===========> Parser (JAXP, JAXB)
	=============================== 


--%>