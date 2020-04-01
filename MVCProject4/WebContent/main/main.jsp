<!-- 항상 요거를 실행해야... 게시판이든 영화든 다 main.jsp 에 include해서 실행하는거다. -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Movie Center</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="../main/home.do">Home</a></li>
      <li><a href="../member/join.do">회원가입</a></li>
      <li><a href="#">영화</a></li>
      <li><a href="#">영화 예매</a></li>
      <li><a href="#">커뮤니티</a></li>
      <li><a href="#">My Page</a></li>
    </ul>
  </div>
</nav>
  
<div class="container">	
  <jsp:include page="${main_jsp }"></jsp:include>
  <!-- 각 Model에서 request에다가 main_jsp 데이터를 싣어 줬음 ==> ${main_jsp}를 사용할 수 있다.
  		각 Model에서 보낸 main_jsp 값은 "../member/자기파일.jsp" 이다.
  		ex1) JoinModel에서 request에다가 싣어 보낸 main_jsp 값은 "../member/join.jsp"  
  		    ==> < jsp : include page="../member/join.jsp" > 이 파일을 include 시킨다. 
  		ex2) HomeModel에서  request에다가 싣어 보낸 main_jsp 값은 "home.jsp" 
  		    ==> < jsp : include page="home.jsp" > 이 파일을 include 시킨다. -->
</div>

</body>
</html>
