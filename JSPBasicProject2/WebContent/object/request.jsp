<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 유효성검사 방법1. JavaScript 사용 -->
<!-- <script type="text/javascript">
function send(){
	var user_name=window.document.frm.user_name.value; //form의 name값을 사용하여 객체에 접근 
	if(user_name.trim()=="")
	{
		alert("이름을 입력하세요");
		document.frm.user_name.focus();
		return; // 이 밑에 문장이 실행되지 않아야 하므로.
	}
	
	var content=document.frm.content.value;
	if(content.trim()=="")
	{
		alert("소개를 입력하세요");
		document.frm.content.focus();
		return; // 이 밑에 문장이 실행되지 않아야 하므로.
	}
	
	document.frm.submit();
}
</script> -->

<!-- 유효성검사 방법2. jQuery 사용 -->
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
// jQuery 형식: $(선택자).동작함수();
// $의 의미:  제이쿼리를 의미하고, 제이쿼리에 접근할 수 있게 해주는 식별자
// 좀 더 자세히 말하자면, https://ktko.tistory.com/entry/jQuery-%EC%9D%98-%EC%9D%98%EB%AF%B8

$(function(){
	$('#btn').click(function(){
		var user_name=$('#user_name').val();
		if(user_name.trim()=="")
		{
			alert("이름을 입력하세요");
			$('#user_name').focus();
			return; // 이 밑에 문장이 실행되지 않아야 하므로.
		}
		
		var content=$('#content').val();
		if(content.trim()=="")
		{
			alert("소개를 입력하세요");
			$('#content').focus();
			return; // 이 밑에 문장이 실행되지 않아야 하므로.
		}
		
		$('#frm').submit();
	});
});

</script>
</head>
<body>
<%-- <데이터 전송 방법: request> --%>
	<center>
		<h1>개인정보</h1>
		<form method=post action="request_ok.jsp" name=frm>
			<table border=1 width=450>
				<tr>
					<th width=20%>이름</th>
					<td width=80%><input type=text name=user_name size=15 id=user_name></td>
				</tr>
				<tr>
					<th width=20%>성별</th>
					<td width=80%>
						<input type=radio name=sex value="남자" checked>남자
						<input type=radio name=sex value="여자">여자
						<!-- name이 동일해야. value값이 넘어간다. -->
					</td>
				</tr>
				<tr>
					<th width=20%>전화</th>
					<td width=80%>
						<select name="tel1">
							<option>02</option>
							<option>031</option>
							<option>032</option>
							<option>041</option>
							<option>042</option>
						</select>
					</td>
				</tr>
				<tr>
					<th width=20%>취미</th>
					<td width=80%>
							<input type="checkbox" name="hobby" value="운동">운동
							<input type="checkbox" name="hobby" value="낚시">낚시
							<input type="checkbox" name="hobby" value="게임">게임
							<input type="checkbox" name="hobby" value="독서">독서
							<input type="checkbox" name="hobby" value="등산">등산
					</td>
				</tr>
				<tr>
					<th width=20%>소개</th>
					<td width=80%>
						<textarea rows="5" cols="35" name="content" id=content></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<!-- <input type=button value=전송 onclick="send()"> -->
						<input type=button value=전송 id="btn">
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>





<%-- 
	<내장객체 (기본객체)> 
	 - 미리 만들어준 객체 

	[오늘] 
	1. HttpServletRequest request 
	2. HttpServletResponse response
	==========================
	[내일~]
	3. PageContext pageContext;
    4. HttpSession session = null;
    5. ServletContext application;
    6. ServletConfig config;
    7. JspWriter out = null;
    8. Object page = this;
    9. JspWriter _jspx_out = null;
 --%>
 
<%--
<1. request 객체>
 1) 사용자 요청 정보 
 	(1) ★★★String getParameter() => 단일값 =================> list.jsp?page=1&no=10 
 			======				getParameter("page")
 			리턴형				getParameter("no")
 	(2) ★★★String[] getParameterValues() => 다중값  ===========> list.jsp?no=1&no=2&no=3
 			========					getParameterValues("no"); 
 			리턴형 (다중값이니까 배열!)		
 		ex) 체크박스/셀렉트박스에서 여러개를 선택하거나 했을 때
 	(3) ★★★setCharacterEncoding("UTF-8") => 한글 변환 
 		ex) 구글에서 '자바' 검색 한 url을 이클립스에 복붙하면 아래와 같이 변경됨 (디코딩되서 그럼)  
 		https://www.google.com/search?q=%EC%9E%90%EB%B0%94&oq=%EC%9E%90%EB%B0%94&aqs=chrome..69i57j69i59l4j69i61l3.362j0j9&sourceid=chrome&ie=UTF-8
 														 	  ================= 
 														 	  	'자바'를 디코딩한 것 
 2) 사용자의 브라우저 정보 
 	(1) ★★★getRemoteAddr(): 사용자의 IP 
 	(2) getContentType(): 사용자가 요청한 컨텐츠의 타입. (text/html, text/xml) 
 	(3) getProtocol(): 사용자가 이용한 프로토콜 (http or https or ws(웹소켓)) 
 	(4) getMethod(): GET/POST 방식 
 	(5) ★★★getRequestURI(): 
 	(6) ★★★getContextPath()
 	
 	 http://localhost/JSPBasicProject2/jsp/request.jsp
 	 ================================================= URL (전체) ==> getRequestURL()
					 ================================= URI (/JPSBasicProject2/jsp/request.jsp)
					 ================= 디폴트폴더(JSPBasicProject2) ==> getContextPath()   	 
 	 ================ 서버이름(localhost) ==> getServerName()
 	 http://localhost:80 
 	 				  == 포트 ==> getServerPort()
 	 				  
 
 3) 서버 정보 
	(1) getServerName() => localhost
	(2) getServerPort() => 80
 4) 데이터 추가 정보   
	(1) ★★★setAttribute(): 데이터 추가 
	(2) ★★★getAttribute(): 추가된 데이터 읽기 
  --%>


<!-- 
	<JavaScript Core>
	1. 데이터형
		var : 자동 지정 변수.
		1) 형이 자동으로 지정된다.  
			var a=1; ==> a:int 
			var a=10.5; ==> a:double
			var a='Hello, world!'; ==> a:String
			var a="Hello, world!"; ==> a:String
			var a=['a', 123, 10.5, {}]; ==> a: Array
			var a={} ==> a: Object 
			======================
		2) scope 
		 - var는 사용범위가 명확하지 않다. ==> let, const(상수형 변수) 출현.  
		   var: 변수 재선언 가능. 함수 레벨 스코프. 
		   let: 변수 재선언 불가. 재할당 가능. 블록 레벨 스코프.  
		   const: 변수 재선언 불가. 재할당 불가(상수형이니까!). 블록 레벨 스코프.  
	2. 연산자
		1) 산술연산자 (+, -, *, /) 
		2) 비교연산자 (==, !=, <, >, <=, >=)
		3) 논리연산자 (&&, ||) 
		4) 대입연산자 (=, +=, -=) 
	3. 제어문
		if, if~else, for, while, do~while, switch
		for in, forEach
		※참고 - for in
		for (변수 in 객체){
    		....
		}
		※참고 - forEach
		: 
	4. 함수
		1) 형식 
			function 함수명()  <== 매개변수는 'var addr'과 같이 쓰지X. 그냥 'addr'.
			{
				....
			}
		2) 변수에 함수를 할당 가능 
			var a = function(){
				.....
			}
		3) 람다식: 권장사항임!! => 는 함수가 생략됐다고 생각하면 됨. 
			var func=()=> {
				....
			} 			
	5. 객체 구조 
		- window > document > form 
		   상위 ================== 하위
		1) window: 브라우저. ex) window.open()
		2) document
		3) form
		4) location
		5) history: 이전 => back(), forward(), go()
		   - history.go(): 특정 페이지로
		   		ex) history.go(0): 현재 페이지로
		   			history.go(-1): 이전 페이지로 
		   			history.go(-2): 두 단계 전 페이지로
		   			history.go(1): 다음 페이지로 
		   - history.back(): 이전 페이지로  
		   - history.forward(): 다음 페이지로
	6. 사용자 정의 함수   
-->


