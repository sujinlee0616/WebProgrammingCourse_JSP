<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name1="홍길동";
	request.setAttribute("name2", "홍길동"); 
	
	String name3=(String)(request.getAttribute("name"));
	// <이때까지 우리가 코딩해왔던 방식 - MVC가 아님> 
	// Java에서 결과값을 받아서, JSP로 < % % >를 이용해서 보냈음
	// (모든 결과값은 처리하는 위치에 있으므로, 우리가 필요한 값들은 JAVA에 있었기 때문.
	//  ex. BoardDAO.java에서 boardDetailData에서 필요한 값을  받아서 JSP에서 출력...)	
	// 앞으로는, Java에서 request/session에 값을 싣어서 JSP로 보낼거임. ==> EL 사용 ==> JSP에서 < % % >이런 코드들이 사라짐 
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	이름: ${name1 }<br>   <!-- 일반 자바변수 ==> 출력 불가 ㅋ -->
	이름: ${name2 }  <!-- request의 속성명 ==> 출력 가능  -->
	<%=name3 %>
</body>
</html>


<%-- 
	<EL>
	 - 화면에 출력하는 언어
	 - 사용법: ${출력물} 
	 - ${}안에 일반 자바 변수가 들어가는게 아니다!! <==> <%=일반 자바변수 %>
	 - ${}안에 들어가는 것들의 유형
	   1) 파라미터 이름 
	   	  getParameter("para_name") => ${param.para_name} 
	   	  ex) getParameter("id") => ${param.id} 
	   	  
	   2) request의 key값(속성명)
	   	  request.getAttribute("id") => ${requestScope.id} => ${id}
	   	  ====> ${id} 처럼 쓰려면, request에 있어야만 이렇게 쓸 수 있다.  
	      ex) String id="admin"; ==> ${id} (X) // 이렇게 쓰는 것 불가! 일반 자바변수는 불가능!
	       	  request.setAttribute("id",id) ==> ${id} (O) // request의 key니까 사용 가능 
	            참고) session.setAttribute(key, value)
	            
	   3) session의 key값 (속성명)
	      session.getAttribute("name","홍길동") ==> ${sessionScope.name} => ${name}
	      // 참고) session.getAttribute(key, value)
	   	    
	   	    참고) 우선순위: request > session 
	   	    request.setAttribute("id", "admin");
	   	    session.setAttribute("id", "hong");
	   	    ${id} ==> admin
	   	    	// 우선순위가 request가 session보다 더 높으므로, ${id}하면 request의 id값을 가져온다. 
	   	    ${sessionScope.id} ==> hong
	   	    	// hong을 가져오고 싶다면 ${id} 이렇게는 불가능하고 (우선순위에서 밀렸으니까)
	   	    	// ${sessionScope.id} 해서 가져와야 한다. 
		 	
--%>











