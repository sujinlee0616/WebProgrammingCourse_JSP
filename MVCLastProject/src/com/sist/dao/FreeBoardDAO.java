package com.sist.dao;
// ★FreeBoard: 댓글형 게시판. PL/SQL,프로시저 이용하여 구현.★ 
// ☆여기서는 MyBatis 안 쓰고 Java로 짰음!☆ 
// 프로시저는 아래의 파일에 만들었음
// 20200417_PLSQL을_이용한_자유게시판_만들기.sql

import java.util.*;
import com.sist.vo.BoardVO;
import oracle.jdbc.OracleTypes;
import java.sql.*;

public class FreeBoardDAO {
	
	// =============================================================================================
	private Connection conn;
	private CallableStatement cs; // Procedure를 호출 시에 전송 객체 
	// 참고) mybatis에서 하려면, 프로시저 호출 mapper에서 id/resultType/parameterType 적는 곳에 statementType="CALLABLE" 적어줘야함
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// [드라이버 등록] 
	public FreeBoardDAO(){
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} 
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	// [연결]
	// 참고) MyBatis에서 SqlSession session=ssf.openSession()을 할 때, 아래와 같은 getConnection이 불러와진다.
	public void getConnection()    
	{
		try 
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
		} 
		catch (Exception ex) {}
	}
	// [해제]
	// 참고) MyBatis에서 session.close() 하면 아래와 같은 disConnection이 불러와진다. 
	public void disConnection()    
	{
		try 
		{
			if(cs!=null) cs.close();
			if(conn!=null) conn.close();
		} 
		catch (Exception ex) {}
	}
	// =============================================================================================
	
	
	// [글 리스트] 
	/* Oracle에서 코딩한 프로시저는 아래와 같았음 
	 * CREATE OR REPLACE PROCEDURE boardListData(
	    pStart NUMBER,
	    pEnd NUMBER,
	    pResult OUT SYS_REFCURSOR
	 */
	// 참고) MyBatis에서 session.selectList() 하면 아래와 같은 코딩을 하는 것. 
	public List<BoardVO> freeboardListData(int page)
	{
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		try 
		{
			getConnection();
			int rowSize=10;
			int start=rowSize*(page-1)+1;
			int end=rowSize*page;
			
			String sql="{CALL boardListData(?,?,?)}";
			cs=conn.prepareCall(sql); // 프로시저 호출  
			
			// ?에 값을 채운다
			cs.setInt(1, start);
			cs.setInt(2, end);
			cs.registerOutParameter(3, OracleTypes.CURSOR); // OUT변수를 위한 저장공간. "register가 공간이야."
			
			// 실행요청
			cs.executeUpdate(); // 무조건 executeUpdate() 사용한다!☆ -- boardListData(?,?,?) 호출
			
			// 결과값 받는다 
			ResultSet rs=(ResultSet)cs.getObject(3); // Java에는 오라클과 달리 CURSOR라는 데이터 형태가 없기 때문에 object를 사용해서 받는다.   
			// ==> CURSOR가 들어오면 ResultSet으로 받아야 

			// SELECT no,subject,name,regdate,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,num
			// num은 rownum으로 받으니까 필요X ==> 나머지 6개 set하자 
			while(rs.next())
			{
				BoardVO vo=new BoardVO();
				
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
				vo.setDbday(rs.getString(5));
				vo.setHit(rs.getInt(6));
				list.add(vo);
			}
			rs.close();
			
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return list;
	}
	
	
	// [총 페이지 구하기] 
	/* Oracle에서 코딩한 프로시저는 아래와 같았음 
	 * CREATE OR REPLACE PROCEDURE boardTotalPage(
		    pTotal OUT NUMBER
		)
	*/
	public int freeBoardTotalPage()
	{
		int total=0;
		
		try 
		{
			getConnection();
			String sql="{CALL boardTotalPage(?)}";
			cs=conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.INTEGER); // 정수를 저장할 수 있는 공간(메모리)를 만들어라. 
			// OUT 변수는 registerOutParameter로.
			// IN 변수는 이전이랑 방식 똑같음 
			
			// 실행
			cs.executeUpdate();
			
			// 메모리에서 결과값을 가지고 온다
			total=cs.getInt(1); // 얘는 CURSOR아니니까 걍 int 변수로 받음 됨 
			
		} 
		catch (Exception ex)  
		{
			ex.printStackTrace();
		}
		finally {
			disConnection();
		}
		return total;
	}
	
	
	// [글쓰기]
	/* 
	 * 오라클에서 코딩한 프로시져는 아래와 같았음
	 * CREATE OR REPLACE PROCEDURE boardInsertData(
		    pName board.name%TYPE,
		    pSubject board.subject%TYPE,
		    pContent board.content%TYPE,
		    pPwd board.pwd%TYPE
		)
	 */
	public void freeBoardInsertData(BoardVO vo)
	{
		try 
		{
			getConnection();
			String sql="{CALL boardInsertData(?,?,?,?)}"; // 프로시저 호출 
			cs=conn.prepareCall(sql);
			
			// ?에 값을 채운다
			cs.setString(1, vo.getName());
			cs.setString(2, vo.getSubject());
			cs.setString(3, vo.getContent());
			cs.setString(4, vo.getPwd());
			// OUT 변수는 registerOutParameter로.
			// IN 변수는 이전이랑 방식 똑같음 
			
			// 실행
			cs.executeUpdate();
		} 
		catch (Exception ex)  
		{
			ex.printStackTrace();
		}
		finally {
			disConnection();
		}
		
	}
	
	// [상세보기]
	/* 오라클에서 코딩한 프로시져는 아래와 같았음 & pType 1:상세보기, 2:수정하기
	 * CREATE OR REPLACE PROCEDURE boardInfoData(
	     pNo board.no%TYPE,
	     pType NUMBER,
	     pResult OUT SYS_REFCURSOR
		)
	 */
	public BoardVO freeBoardInfoData(int no,int type)
	{
		BoardVO vo=new BoardVO();
		
		try 
		{
			getConnection();
			String sql="{CALL boardInfoData(?,?,?)}"; // 프로시저 호출 
			cs=conn.prepareCall(sql);
			
			// ?에 값을 채운다
			cs.setInt(1, no);
			cs.setInt(2, type);
			cs.registerOutParameter(3, OracleTypes.CURSOR); // OUT변수를 위한 임시 저장공간.
			
			// 실행요청
			cs.executeUpdate(); 
			
			// 결과값 받는다 
			ResultSet rs=(ResultSet)cs.getObject(3); // Java에는 오라클과 달리 CURSOR라는 데이터 형태가 없기 때문에 object를 사용해서 받는다.   
			
			rs.next();
			
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setRegdate(rs.getDate(5));
			vo.setHit(rs.getInt(6));
			
			rs.close();
			
		} 
		catch (Exception ex)  
		{
			ex.printStackTrace();
		}
		finally {
			disConnection();
		}
		
		return vo;

	}
	
	// [글 수정]
	/* 오라클에서 코딩한 프로시져는 아래와 같았음
	 * CREATE OR REPLACE PROCEDURE boardUpdate(
		    pNo board.no%TYPE,
		    pName board.name%TYPE,
		    pSubject board.subject%TYPE,
		    pContent board.content%TYPE,
		    pPwd board.pwd%TYPE,
		    pResult OUT VARCHAR2
		)
	 */
	public boolean freeBoardUpdateData(BoardVO vo)
	{
		boolean bCheck=false;
		
		try {
			
			getConnection();
			String sql="{CALL boardUpdate(?,?,?,?,?,?)}"; // 함수호출
			cs=conn.prepareCall(sql);
			
			cs.setInt(1, vo.getNo());
			cs.setString(2, vo.getName());
			cs.setString(3, vo.getSubject());
			cs.setString(4, vo.getContent());
			cs.setString(5, vo.getPwd());
			// OUT변수
			cs.registerOutParameter(6, OracleTypes.VARCHAR);
			
			cs.executeQuery();
			
			String result=cs.getString(6); // 프로시져에서 비번 맞게 입력했는지를 true/false로 보냄 ==> DAO에서 bCheck에 저장 ==> Model에서 받음  ==> update_ok.jsp로 보냄
			// System.out.println("result="+result);
			bCheck=Boolean.parseBoolean(result);
			
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return bCheck;
	}
	
	
	// [글 삭제]
	/* 오라클에서 코딩한 프로시져는 아래와 같았음
	 CREATE OR REPLACE PROCEDURE boardDeleteData(
	    pNo board.no%TYPE,
	    pPwd board.pwd%TYPE,
	    pResult OUT VARCHAR2
	 )	
	 */
	public boolean freeBoardDeleteData(int no,String pwd)
	{
		boolean bCheck=false;
		
		try {
			
			getConnection();
			String sql="{CALL boardDeleteData(?,?,?)}"; // 함수호출
			cs=conn.prepareCall(sql);
			
			cs.setInt(1, no);
			cs.setString(2, pwd);
			// OUT변수
			cs.registerOutParameter(3, OracleTypes.VARCHAR);
			
			cs.executeQuery();
			
			String result=cs.getString(3); // 프로시져에서 비번 맞게 입력했는지를 true/false로 보냄 ==> DAO에서 bCheck에 저장 ==> Model에서 받음  ==> update_ok.jsp로 보냄
			// System.out.println("result="+result);
			bCheck=Boolean.parseBoolean(result);
			
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return bCheck;
	}
	
}





