<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row" style="margin: 0px auto; width:450px;">
		<h3 class="text-center">${year }년 ${month }월</h3>
		<table class="table">
			<tr>
				<td>
					<select name="year">
						<c:forEach var="i" begin="2020" end="2030">
							<c:if test="${i==year }">
								<option selected>${i }</option>
							</c:if>
							<c:if test="${i!=year }">
								<option>${i }</option>
							</c:if>
						</c:forEach>
					</select>년도&nbsp;
					<select name="month">
						<c:forEach var="i" begin="1" end="12">
							<c:if test="${i==month }">
								<option selected>${i }</option>
							</c:if>
							<c:if test="${i!=month }">
								<option>${i }</option>
							</c:if>
						</c:forEach>
					</select>월
				</td>
			</tr>
		</table>
		<table class="table">
			<!-- =================== Start of 달력 첫줄: 요일표시 =================== --> 
			<tr>
				<c:forEach var="sw" items="${strWeek }">
					<th class="text-center danger">
					${sw }
					</th>
				</c:forEach>
			</tr>
			<!-- =================== End of 달력 첫줄: 요일표시 =================== --> 
			
			<!-- ========================== 달력 날짜 출력 ========================== --> 	
			<!-- week: 이번달 1일의 요일 
				 ex) 이번달 1일이 수요일이면 week=3 
				     ==> week를 1씩 증가시켜서 그 다음 날짜를 출력. 마지막날까지 출력.
				     ==> week이 6(토)보다 커지면 그 다음줄에 달력 날짜 출력해야. 
				 		    ※ 달력의 한 주는 일요일부터 시작하게 만들거임. -->	
			<c:set var="week" value="${week }"/>  <!-- week를 바꿔야하기 때문에 변수를 줘야 -->
				<c:forEach var="i" begin="1" end="${lastday }"> 
					<!-- (1) 1일이면 1일 이전 날들은 달력에 공백으로 출력한다. -->
					<c:if test="${i==1 }"> 
						<tr>
						<c:forEach var="j" begin="1" end="${week }">  <!-- 이번달의 1일 전에 공백을 출력한다. -->
							<td>&nbsp;</td>  
						</c:forEach>
					</c:if>
					<!-- (2) 모든 i는, i를 출력하고 week을 1씩 증가시키고, week이 6보다 크면 줄바꿈한다. -->
					<td class="text-center">${i }</td> 
					<c:set var="week" value="${week+1 }" />
					<c:if test="${week>6 }"> <!-- week이 6보다 크면 (오늘이 일요일이면) -->
						<c:set var="week" value="0"/> <!-- week을 0(일)으로 바꾸고, 줄바꿈한다. -->
						</tr>
						<tr> <!-- 줄바꿈 후, 다음에 시작할 줄을 위해 tr을 열어준다  -->
					</c:if>					
				</c:forEach>
				</tr>
				<!-- 참고) JSTL의 forEach 문에서, begin,end는 >,<이 아니라 >=,<= 임에 주의할 것!! -->
			
		</table>
	</div>
</body>
</html>