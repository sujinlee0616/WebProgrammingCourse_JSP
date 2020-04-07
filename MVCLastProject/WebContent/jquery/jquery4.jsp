<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script>
 $(function(){
	/* 자바스크립트에서 세미콜론(;) 찍어도 되고 안 찍어도 된다... ES6는 세미콜론 안 찍음... */
	
	// [jQuery 메소드] - eq(), first(), last()
	/* $('h1').css('color','orange');
	$('h1:even').css('background-color','lightpink'); // 번호 0번부터 시작함!!
	$('h1:odd').css('background-color','lightblue'); */
	// $('h1').eq(0).css('color','orange');  
	$('h1').first().css('color','red');
	$('h1').last().css('color','blue');
	
 }); 
 
 /* js의 변수 var는 재선언이 가능하다. */
	var a=10;
	console.log("a="+a);
	var a=20;
	console.log("재선언 a="+a);
	
	/* const, let: 재선언 불가 */
	/* const b=10;
	const b=20;
	let c=10;
	let c=20;  */

</script>
</head>
<body>
	<h1>Java: 0번</h1>
	<h1>Oracle: 1번</h1>
	<h1>JSP: 2번</h1>
	<h1>Spring: 3번</h1>
	<h1>Kotlin: 4번</h1>
</body>
</html>