<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*,java.util.*"%>
<%
	try
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url="jdbc:oracle:thin:@localhost:1521:XE";
		Connection conn=DriverManager.getConnection(url,"hr","happy");
		String sql="SELECT rank,title,singer,poster FROM music_genie";
		PreparedStatement ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	$(function(){
		$('#keyword').keyup(function(){ // keyup: 키보드 눌렀다 놨을 때 ==> 뭔가 입력한 후 
			let k=$(this).val();
			//console.log(k);
			$('#user-table > tbody > tr').hide();
			let temp=$('#user-table > tbody > tr > td:nth-child(4n+3):contains("'+k+'")'); // contains 안에는 왜 저렇게 씀?  
			// td:nth-child(4n+3): 곡명 (참고: HTML은 컬럼번호 1번부터 시작)
			// 내가 입력한 값 k를 갖고 있는 곡명을 temp에 담는다. 
			$(temp).parent().show(); // 우리가 갖고온 건 td인데, 보여줘야 하는건 tr이니까 parent
			//console.log(temp);
			// 참고) $(객체).child(); 하면 child 갖고 올 수 있고 $(객체).child().first(); 하면 firstchild 가져옴
			
		});
	});
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<h1 class="text-center">뮤직 Top50</h1>
			<table class="table">
				<tr>
					<td>
						<input type="text" size="30" class="input-sm" placeholder="검색어 입력" id="keyword">
					</td>
				</tr>
			</table>
			<table class="table table-hover" id="user-table">
				<thead>
					<tr class="success">
						<th>순위</th>
						<th></th>
						<th>곡명</th>
						<th>가수명</th>
					</tr>
				</thead>
				<tbody>
				<%
					while(rs.next())
					{
				%>
						<tr>
							<td><%=rs.getInt(1) %></td>
							<td><img src="<%=rs.getString(4) %>" width="35" height="35"></td>
							<td><%=rs.getString(2) %></td>
							<td><%=rs.getString(3) %></td>
						</tr>
				<%	
					}				
				%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
<%
	}catch(Exception ex){}
%>


<!--
	- 아래의 세 개 다 같은거임. 
	function aaa(){}
	var a=function(){}
	var a=()=>{}   // Arrow 함수. function과 return을 생략한 것.
 
 -->






