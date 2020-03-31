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
</div>

</body>
</html>
