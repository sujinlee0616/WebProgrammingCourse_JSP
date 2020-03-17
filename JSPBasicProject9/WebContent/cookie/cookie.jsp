<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	<Cookie>
	- 저장장소: 웹 브라우저에 저장
	- 용도: 최근검색어/최근 방문한 페이지/상세보기 등을 저장 ==> 최근 방문한 메뉴, 장바구니 등에 사용됨 
	- 동작과정: 
	  1) cookie 생성
	     Cookie cookie = new Cookie("key", "value"); 
	  2) 저장단계 
	     response.addCookie(cookie);
	  3) Cookie 읽기 
	     Cookie[] cookies=request.getCookies(); 
	     ==> ArrayList에 저장 후 출력
	  4) 삭제, 저장기간
	          삭제 => setMaxAge(0);
	          저장 => setMaxAge(60*60*24);   
	- 특징: 
	  1) Map 형식 ==> key가 중복되면 안 됨.
	  2) 단점: 문자열만 저장 가능    

 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>

