package com.sist.temp;

import java.util.*;

import oracle.jdbc.OracleTypes;

import java.sql.*;

public class StudentDAO {
	private Connection conn;
	private CallableStatement cs; // Procedure 호출 시 사용하는 statement.
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public StudentDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void getConnection()
	{
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception ex) {}
	}
	
	public void disConnection()
	{
		try {
			if(cs!=null)
				cs.close();
			if(conn!=null)
				conn.close();
		} catch (Exception ex) {}
	}
	
	public StudentVO studentInfoData(int no)
	{
		StudentVO vo=new StudentVO();
		try {
			getConnection();
			String sql="{CALL pro_test_select(?,?,?,?,?)}";
			
			cs=conn.prepareCall(sql); // 실행 
			
			// ?에 값을 채운다.
			// 1) 첨부하는 변수 
			cs.setInt(1, no); // 첫번째 ?에 값 채움 .
			// 2) 데이터를 받은 변수 - register라는 공간에 값 채워라.
			cs.registerOutParameter(2, OracleTypes.VARCHAR); // 두번째~다섯번째 ?에 값 채움 
			cs.registerOutParameter(3, OracleTypes.INTEGER);
			cs.registerOutParameter(4, OracleTypes.INTEGER);
			cs.registerOutParameter(5, OracleTypes.INTEGER);
			// 다른 언어 사용할 수도 있으므로 VARCHAR2 대신 VARCHAR로 해줘야한다. VARCHAR2는 오라클에서만 쓰기 때문.
			
			// 실행
			cs.executeUpdate(); // <== 여기에선 select문이라도 executeQuery 아니고 executeUpdate 쓴다. 왜냐면 프로시저는 모든 SQL에 executeUpdate를 써야 함. 
			
			String name=cs.getString(2);
			int kor=cs.getInt(3);
			int eng=cs.getInt(4);
			int math=cs.getInt(5);
			
			vo.setName(name);
			vo.setKor(kor);
			vo.setEng(eng);
			vo.setMath(math);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return vo;
	}



}






