<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<pre>
 ★★★ [이 프로젝트의 동작순서] ★★★
 0. 톰캣 구동
 1. 톰캣이 web.xml을 읽음
 2. web.xml 정보를 바탕으로 Servlet 실행 
 
 1. XML Parser
  - XML Parser가 applicationContext.xml에서 패키지를 읽음
 2. ComponentScan이 패키지를 파일명으로 변경함
  - Class.forName(class명) 해서...
 3. HandlerMapping(얘는 클래스 관리자임)
  - HandlerMapping은 메모리 할당된 클래스를 전부 가지고 있다. 
  - Dispatcher가 요청하면 클래스를 넘긴다. 
 4. DispatcherServlet
  - HandlerMapping과 통신해서, model을 갖고 온다. 
 5. ViewResolver
  - JSP를 찾는다. 
 6. Model
  - Model을 통해서 데이터를 JSP로 전송한다. 
 7. forward or sendRedirect 
</pre>

</body>
</html>