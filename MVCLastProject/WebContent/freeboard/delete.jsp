<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row{margin: 0px auto; width:350px;}
</style>
</head>
<body>
<div class="container">
	<div class="services" class="clear">
		<div class="row text-center">
			<img src="../freeboard/jsp_board2.png">
		</div>
		<div class="row">
			<form method=post action="../freeboard/delete_ok.do">
				<table class="table">
					<tr>
						<th class="text-right success" width="25%">비밀번호</th>
						<td width="75%">
							<input type="password" name=pwd size=15>
							<input type="hidden" name=no value=${no }>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="text-center">
							<input type="submit" class="btn btn-sm btn-primary" value="삭제"> 
							<!-- input type이 submit이어야 데이터 넘길 수 있다!!★★★  button이면 안된다 -->
							<input type="button" class="btn btn-sm btn-danger" value="취소"
							onclick="javascript:history.back()">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
	
</body>
</html>