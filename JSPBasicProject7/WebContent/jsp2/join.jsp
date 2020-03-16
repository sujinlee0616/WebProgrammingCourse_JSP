<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h2>회원가입</h2>
		<form method=post action="join_ok2.jsp">
		<!-- submit 하면 join_ok.jsp에다가 데이터 넘김 -->
		<table border=1 width=350>
			<tr>
				<td width=20%>이름</td>
				<td width=80%><input type="text" name="name" ></td>
				<!-- input의 name값과 MemberBean.java의  private String name; 변수명 이름 똑같아야함! -->
			</tr>
			<tr>
				<td width=20%>성별</td>
				<td width=80%><input type="text" name="sex" ></td>
			</tr>
			<tr>
				<td width=20%>주소</td>
				<td width=80%><input type="text" name="addr" ></td>
			</tr>
			<tr>
				<td width=20%>전화</td>
				<td width=80%><input type="text" name="tel" ></td>
			</tr>
			<tr>
				<td width=20%>나이</td>
				<td width=80%><input type="text" name="age" ></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button>전송</button> 
					<!-- 
						<submit 하는 애 만드는 법>
						1. <input type="submit">
						2. <input type="image">
						3. <button>
						   - 버튼은 만들면 타입 따로 submit이라고 안 적어줘도 submit 가능 
					 -->
				</td>
			</tr>
		</table>
		</form>
	</center>
</body>
</html>
