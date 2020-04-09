<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="../shadow/css/shadowbox.css"> 
<script type="text/javascript" src="../shadow/js/shadowbox.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<style type="text/css">
	.row{margin: 0px auto; width: 700px;}
	.container th{color: black; font-weight: 300;}
	input,select {display: inline-block;}
	.table,td{background-color: white;}
</style>
<script type="text/javascript">
Shadowbox.init({
	players:['iframe'] 
});
var i=0;
var p=0;

$(function(){
	// 우편번호 검색 버튼 클릭 => Shadowbox 창 오픈 
	$('#postBtn').click(function(){  // HTML에 onclick="함수명" 이 jQuery에서는 .click(function(){..}) 와 같음.
		Shadowbox.open({
			content:'../member/postfind.do',
			title:'우편번호 검색',
			player:'iframe', // iframe: HTML에서의 include 같은 것.
			width:550,
			height:400
		})
		p=1;
	})		
	
	// ID 중복체크 버튼 클릭 => Shadowbox 창 오픈 
	$('#idcheckBtn').click(function(){
		Shadowbox.open({
			content:'../member/idcheck.do',
			title:'아이디 중복체크',
			player:'iframe',
			width:390,
			height:250
		})
		i=1;
	})
	
	// 회원가입 버튼 클릭 
	$('#sendBtn').click(function(){
		// 1) 아이디 중복검사 했는지 여부 확인 
		if(i==0)
		{
			alert("아이디 중복체크를 하세요.");
		}
		else
		{	
			if($('#pwd').val()!=$('#pwd1').val())
			{
				alert("비밀번호가 틀립니다!");
			}
			else if(p==0)
			{
				alert("우편번호를 입력하세요.");
			}
		}
	})
});
</script>	
</head>
<body>
	<div class="container">
		<h1 class="text-center">회원가입</h1>
		<div class="row">
			<form name="join_frm" action="../member/join_ok.do" method="post" id="join_frm">
				<table class="table table-hover">
					<tr>
						<th width=15% class="danger text-right">ID</th>
						<td width=85% >
							<input type="text" name="id" size="15" class="input-sm" readonly id="id">
							<!-- 중복체크 ==> Modal ==> 확인 ==> id input창을 readonly로 변경 -->
							<!-- name: Java가 데이터 넘길 때 사용 vs id: CSS/JQeury에서 사용. -->
							<input type="button" value="중복체크" class="btn btn-sm btn-danger" id="idcheckBtn" >
						</td>
					</tr>
					<tr>
						<th width=15% class="danger text-right">비밀번호</th>
						<td width=85% >
							<input type="password" name="pwd" size="15" class="input-sm" id="pwd" required>&nbsp;
							재입력: <input type="password" name="pwd1" size="15" class="input-sm" id="pwd1" required> 
							<!-- 비밀번호 확인  기능은 javascript로 만든다 -->
						</td>
					</tr>
					<tr>
						<th width=15% class="danger text-right">이름</th>
						<td width=85% >
							<input type="text" name="name" size="15" class="input-sm" required>
						</td>
					</tr>
					<tr>
						<th width=15% class="danger text-right">이메일</th>
						<td width=85% >
							<input type="text" name="email" size="50" class="input-sm">
						</td>
					</tr>
					<tr>
						<th width=15% class="danger text-right">성별</th>
						<td width=85% >
							<input type="radio" name="sex" value="남자" checked>남자
							<input type="radio" name="sex" value="여자">여자
						</td>
					</tr>
					<tr>
						<th width=15% class="danger text-right">생일</th>
						<td width=85% >
							<input type="date" name="birthday" size="50" class="input-sm" required>
							<!-- input의 name을 컬럼명과 맞춰놔야 ==> setProperty 사용하기 위해서 -->
						</td>
					</tr>
					<tr>
						<th width=15% class="danger text-right">우편번호</th>
						<td width=85% >
							<input type="text" name="post1" size="5" readonly required> -
							<input type="text" name="post2" size="5" readonly required>
							<input type="button" class="btn btn-sm btn-primary" value="우편번호 검색" id="postBtn">
						</td>
					</tr>
					<tr>
						<th width=15% class="danger text-right">주소</th>
						<td width=85% >
							<input type="text" name="addr1" size="50" class="input-sm" readonly required>
						</td>
					</tr>
					<tr>
						<th width=15% class="danger text-right">상세주소</th>
						<td width=85% >
							<input type="text" name="addr2" size="50" class="input-sm">
						</td>
					</tr>
					<tr>
						<th width=15% class="danger text-right">전화</th>
						<td width=85% >
							<select class="input-sm" name="tel1">
								<option>010</option>
								<option>011</option>
								<option>017</option>
							</select>
							<input type="text" name="tel2" size="5" class="input-sm"> -
							<input type="text" name="tel3" size="7" class="input-sm">
						</td>
					</tr>
					<tr>
						<th width=15% class="danger text-right">소개</th>
						<td width=85% >
							<textarea rows="8" cols="60" name="content"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="text-center">
							<input type="submit" value="회원가입" class="btn btn-sm btn-info"
							id="sendBtn"> <!-- type="submit"!!! -->
							<input type="button" value="취소" class="btn btn-sm btn-success"
							onclick="javascript:history.back()">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>


</body>
</html>


<!-- [member 테이블]
ID       NOT NULL VARCHAR2(20)  
PWD      NOT NULL VARCHAR2(10)  
NAME     NOT NULL VARCHAR2(34)  
EMAIL             VARCHAR2(100) 
SEX               VARCHAR2(10)  
BIRTHDAY NOT NULL VARCHAR2(20)  
POST     NOT NULL VARCHAR2(7)   
ADDR1    NOT NULL VARCHAR2(200) 
ADDR2             VARCHAR2(200) 
TEL               VARCHAR2(20)  
CONTENT  NOT NULL CLOB          
REGDATE           DATE          
ADMIN             CHAR(1) 
 -->

<!-- <JavaScript>
	 - 태그에 접근: 계층구조
	  
		window
		  ┃
		document : HTML 갖고 있는 애...
		  ┃
		form
		  ┃
		input, select, textarea
 -->
 
 <!-- 2020.04.07 (화) 수업 -->
 <!--  [jQuery] 
 
 	 0. 기본문법
 	  1) $(선택자).동작함수();
 	   - 달러($) 기호는 제이쿼리를 의미하고, 제이쿼리에 접근할 수 있게 해주는 식별자이다.
 	  2) $() 함수
 	   - $() 함수는 선택된 HTML 요소를 제이쿼리에서 이용할 수 있는 형태로 생성해 주는 역할을 한다.
 	   - 이러한 $() 함수를 통해 생성된 요소를 제이쿼리 객체(jQuery object)라고 한다. 
                  제이쿼리는 이렇게 생성된 제이쿼리 객체의 메소드를 사용하여 여러 동작을 설정할 수 있다.

	 1. $(function(){}) : jQuery의 메인 함수! ★
	  - jQuery의 시작점. ==> window.onload ==> main()
	  - 원래는 $(document).ready(function(){})인데  (document).ready 생략 가능해서 생략한 것.
			
	 2. [js] var id=document.getElementByID('id');
	    [jQuery] $('#id')
	     - js에서 위와 같이 쓰던 것을 jQuery에서는 간단하게 쓸 수 있게 해줌.
	 
	 3. 선택자 (Selector)
	  1) 태그 => $('태그명') ex) $('tr')
	  2) ID => $('#id명') 
	  3) CLASS => $('.class명')
	  4) 가상 => 자신, 내장객체 => $(this), $(window), $(document)
 
 	 4. 태그로 제어: CSS 제어, 이벤트 발생 
 	 
 	 5. 각종 값 가져오기
 	  1)input 값  
 	    <input type="text" id="id"> ==> $('#id').val();
 	  2)태그 사이 값  
 	    <td>aaa</td> ==> $('td').text();
 	  3)
 	    <td><span>aaa</span></td> ==> $('td').html();
 	  4)
 	    <a href="aaa"> ==> $('a').attr("href");
 	 
 	 6. 각종 값 설정하기 
 	  1) input값  
 	    <input type="text" id="id">
 	    $('#id').val("admin") ==> 'admin' 으로 값이 설정됨
 	  2) <td>aaa</td> 
 	     $('td').text("ccc") ==> 'aaa'가 'ccc'로 변경됨
 	  3) 추가 - append
 	     
  -->
 
 <!--
	우편번호 검색 흐름) 
	1. 회원가입 페이지(join.do)에서 우편번호 검색 버튼을 누름 
	   ==> postfind.do 를 호출 ==> 우편번호 검색창(Shadowbox, iframe)이 뜸
	2. 우편번호 검색 창에서, 빨간색 '입력' 버튼을 누름 ==> postfind_result.do를 호출
	 
 
 
   -->
 
 
 
 


