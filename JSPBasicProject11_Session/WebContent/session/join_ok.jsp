<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
	try
	{
		request.setCharacterEncoding("UTF-8");
	}catch(Exception ex){}
%>
<jsp:useBean id="dao" class="com.sist.dao.DiaryDAO"></jsp:useBean>
<jsp:useBean id="vo" class="com.sist.dao.MemberBean">
	<jsp:setProperty name="vo" property="*"/>
	
	<!-- <jsp:setProperty>: 
		1. 기능: 자바빈 객체의 값 설정... 
		2. protperty="*"로 하면  각 프로퍼티의 값을, 같은 이름을 갖는 파라미터의 값으로 설정한다. 
			ex) name, email 파라미터가 있다면, 
				name 파라미터의 값을 name 프로퍼티의 값으로 설정,
				email 파라미터의 값을 email 프로퍼티의 값으로 설정함.
		3. <jsp:setProperty>를 사용하면 아래의 코드를 한 줄로 해결할 수 있다. 
			 SawonVO vo=new SawonVO();
             vo.setSabun(Integer.parseInt(sabun));
             vo.setName(name);
             vo.setDept(dept);
		 -->
</jsp:useBean>

<%
	// 연결
	dao.memberInsert(vo);
	// 이동 
	response.sendRedirect("login.jsp");
%>
    
