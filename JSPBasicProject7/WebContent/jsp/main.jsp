<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>

<%-- 
	<JSP 액션태그>
	- 7장, 8장 
	1. <jsp:include>
	 - JSP 안에 특정 부분에 다른 JSP를 첨부 => 모듈화
	 - 'pageContext.include("파일명.jsp")'로도 사용 가능 
	2. <jsp:forward>
	 - request를 계속 유지할 경우 (MVC구조에서 많이 사용)
	3. <jsp:useBean>
	 - 메모리 할당 
	 - <jsp:useBean id="vo" class="com.sist.dao.MemberVO">
	 			        ===
	    ==> JAVA: MemberVO vo = new MemberVO();
	    				   ===
	    ==> useBean의 id는 Java에서의 변수와 동일 
	4. <jsp:setProperty>
	 - setOOO()에 값을 채운다. ===> VO, DTO, Bean(JSP)
	 - ※ Bean
	  	 1) 데이터 관리 bean: 데이터를 모아서 관리
	  	    - 읽기/쓰기 ==> getter/setter  
	  	 2) 데이터 액션 bean: bean(VO)에 값을 설정하는 클래스 
	  	    - 데이터베이스 연결 : '~ DAO', '~Manager'
	  	    - DAO와 DAO를 연결: '~Service' 
	 - ex)  
	   [Java 코딩] 
	   public class SawonVO{
	  	 	private int sabun;
	   		private String name;
	   		private String dept;
	   		public int getSabun()
	   		{
	   			return sabun;
	   		}
	   		public void setSabun(int sabun)
	   		{
	   			this.sabun =sabun;
	   		}
	   		public int getName()
	   		{
	   			return name;
	   		}
	   		public void setName(String name)
	   		{
	   			this.name =name;
	   		}
	   		public int getDept()
	   		{
	   			return dept;
	   		}
	   		public void setDept(String sabun)
	   		{
	   			this.dept =dept;
	   		}
	   }
	   	   SawonVO vo = new SawonVO();
	   	   vo.setSabun(1);
	   	   vo.setName("홍길동");
	   	   vo.setDept("개발부");
	   ==> ★★[jsp 액션태그로 변경 시]★★
	   	   <jsp:useBean id="vo" class="SawonVO">
	   	       vo.setSabun(1);
	   	       <jsp:setProperty name="vo" property="sabun" value="1"/>
	   	       vo.setName("홍길동");
	   	       <jsp:setProperty name="vo" property="name" value="홍길동"/>
	   	       vo.setDept("개발부");
	   	       <jsp:setProperty name="vo" property="dept" value="개발부"/>
	   	   </jsp:useBean>    
	   	   
	   ==> 다른 JSP에서 값을 보낸다
	  		[Java 코딩]  	 
	   		String sabun=request.getParameter("sabun");
	   		String name=request.getParameter("name");
	   		String dept=request.getParameter("dept");
	   		
	   		SawonVO vo=new SawonVO();
	   		vo.setSabun(Integer.parseInt(sabun));
	   		vo.setName(name);
	   		vo.setDept(dept);
	   ==>  ★★[JSP 액션태그로 변경 시]★★
	   		<jsp:useBean id="vo" class="SawonVO">
	   			<jsp:setProperty name="vo" property="*"/>
	   		</jsp:useBean>
	   
	   - set하는건 JSP가 더 편한데, get은 JSP가 오히려 더 코드가 더 길어서 잘 안 씀.  
	
	
	
	
--%>

















