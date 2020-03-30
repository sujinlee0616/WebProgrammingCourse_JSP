package com.sist.dao;
import java.util.*;
import java.io.*; // XML 읽기 위해서 import 

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BoardDAO {
	private static SqlSessionFactory ssf;
	
	static
	{
		// 1. 파싱 => getConnection(), disConnection()
		// 2. id, sql 문장을 Map에 저장
		// 3. id를 입력하고 sql 실행 결과를 달라
		
		try
		{
			// 1) xml 파일을 읽는다. 
			Reader reader=Resources.getResourceAsReader("Config.xml");
			// Spring, Mybatis ==> classpath에서 읽는다. ==> src 폴더  
			// [참고] classpath란?
			//  - 클래스패스란 말 그대로 클래스를 찾기위한 경로이다. 자바에서 클래스패스의 의미도 똑같다. 
			//  - 즉, JVM이 프로그램을 실행할 때, 클래스파일을 찾는 데 기준이 되는 파일 경로를 말하는 것이다
			
			// 2) 파싱
			ssf=new SqlSessionFactoryBuilder().build(reader);
			
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	// 목록 읽기 
	public static List<BoardVO> boardListData(Map map)
	{
		List<BoardVO> list = new ArrayList<BoardVO>();
		SqlSession session=ssf.openSession();
		list=session.selectList("boardListData",map);
		// 						 ============   ====
		// board-mapper.xml: 		id			parameterType
		
		// connection 반환
		session.close();
		return list;
	}
	
	// 상세보기 
	public static BoardVO boardDetailData(int no)
	{
		BoardVO vo=new BoardVO();
		// 형변환 안 해줘도 됨. MyBatis에서 자동형변환해줌. 
		SqlSession session=ssf.openSession();
		// openSession하면 autocommit 아님 ==> 따로 커밋 날려줘야...
		session.update("hitIncrement",no); // <update>
		// <insert> ==> insert("id",값);
		// <update> ==> update("id",값);
		session.commit();	
		
		vo=session.selectOne("boardDetailData",no);
		// 					  ===============  ===
		// board-mapper.xml: 		id		   parameterType
		
		// [참고] selectOne vs selectList
		//  - BoardVO (VO 1개만) ==> selectOne()
		//  - List<BoardVO> (VO List) ==> selectList()
		
		session.close();
		return vo;
	}
	
	// 총 페이지 구하기
	public static int boardTotalPage()
	{
		int total=0;
		SqlSession session=ssf.openSession();
		total=session.selectOne("boardTotalPage");
		
		session.close();
		return total;
	}
	
	// 데이터 추가 
	public static void boardInsert(BoardVO vo)
	{
		SqlSession session=ssf.openSession(true);  //.oepnSession(true): setAutoCommit(true)해준다. 
		session.insert("boardInsert",vo);
		session.close();
	}
	
	// 글 수정 버튼 클릭 => input에다가 해당 글의 데이터 불러옴
	public static BoardVO boardUpdate(int no)
	{
		BoardVO vo=new BoardVO();
		SqlSession session=ssf.openSession(true);
		
		vo=session.selectOne("boardUpdate",no);
		
		session.close();
		return vo;
	}
	
	// 글 내용 수정해서 update 시킬 때 
	public static void boardUpdateData(BoardVO vo)
	{
		SqlSession session=ssf.openSession(true);  //.oepnSession(true): setAutoCommit(true)해준다. 
		session.update("boardUpdateData",vo); 
		session.close();
	}
	
	
}


/*
 * <Java>
 * 클래스의 구성요소
 * 1) 멤버변수
 * 	 	- instance 변수: new해서 메모리 할당해서 생성하는 변수.  
 * 		- static 변수: JVM이 클래스를 읽기 시작할 때 생성되는 변수. 자동으로 메모리에 저장되므로, 메모리 할당을 안 함. 
 * 2) 메소드
 * 3) 생성자 
 * 
 * =======================================================================
 * 필요 시에 멤버 변수에 대한 초기화를 한다. 
 * ==> 초기값을 주는 방법 
 * 		1) 명시적 방법
 *        - 외부나 메소드를 이용해서 초기화 불가. 
 * 		  - 클래스 영역에서는 선언만 가능할 뿐, 구현은 불가능하기 때문.
 * 			==> 메소드 호출, 제어문, 연산자 사용이 불가능.  
 * 		  - 명시적 방법 ex) 		
 * 			public class A
 * 			{
 * 				int a=10;
 * 				static int b=20;		
 * 			}
 * 		2) 생성자를 이용하는 방식
 *        - 외부에서 파일 읽기, 네트워크 연결, DB연결, XML을 파싱할 때
 * 		3) 초기화 블록을 이용하는 방식
 *        - 초기화 블록은 자동 호출된다. 
 *        (1) instance 블록 ==> 변수가 instance일 때 사용
 *         	  public class A
 *        	  {
 *        			int a;
 *       			{
 *       			   a=getNumber();
 *        			}
 *            }
 *         
 *        (2) static 블록 ==> 변수가 static일 경우에 사용
 *        	  public class A
 *        	  {
 *        			static int b;
 *        			static
 *        			{
 *        				b=getNumber();
 *       			}
 *        	  }
 * 
 * 		4) 순서 
 * 		 - 순서: 명시적 방법 => 초기화 블록 => 생성자			
 * 							 
 *  
 * 
*/













