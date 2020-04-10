package com.sist.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.*;
import com.sist.vo.*;

public class ReplyBoardDAO {
	private static SqlSessionFactory ssf;
	static
	{
		ssf=CreateSQLSessionFactory.getSsf();
	}
	
	/*
	 * <프로그램>
	 *  - 공통모듈: 모든 프로그램에 적용되는 모듈. 핵심모델에 반복되는 코드가 들어가지 않기 위해서, 중복되는 코드는 공통모듈로 따로 뺀다. 
	 *    ex) 우리 프로그램에서 CreateSQLSessionFactory.java 따로 뺐음.
	 *    ex) 스프링에서 AOP(Aspect Oriented Programming) 
	 *        : 어떤 로직을 기준으로 핵심적인 관점, 부가적인 관점으로 나누어서 보고 그 관점을 기준으로 각각 모듈화한다.
	 *          횡단관심(Crosscutting concerns)은 Aspect로 모듈화하고 핵심적인 비즈니스 로직에서 분리하여 재사용함.
	 *    ex) 스프링에서는 아래와 같은 메소드에서 catch,finally절은 다 생략 가능. 계속 반복되는 공통 부분이므로 다 따로 빼놨기 때문.
	 *  - 핵심모듈 
	 */
	
	// [답글형 게시판 리스트] 
	public static List<BoardVO> replyListData(Map map)
	{
		List<BoardVO> list=new ArrayList<BoardVO>();
		SqlSession session=null;
		
		try
		{
			session=ssf.openSession();	// 이전에 짰던 GetConnection과 같은 역할.
			list=session.selectList("replyListData",map); // reply-mapper.xml의 id가 replyListData인 SQL구문에 map을 넣어서 값 넣는다.
			
		}catch(Exception ex)
		{
			System.out.println("replyListData: "+ex.getMessage());
		}
		finally
		{
			if(session!=null)
				session.close();
			/*
			 * 위의 코딩 두 줄은 우리가 이전에 짰던 아래의 Disconnection 코드와 같다. 
			 * (즉, Session안에 Connection과 PreparedStatement가 들어있는 것.)  
			 * Connection, PreparedStatement
			 * if(ps!=null) ps.close()
			 * if(conn!=null) conn.close()
			 */
		}
		
		return list;
	}
	
	// [답글형 게시판 총 페이지]
	public static int replyTotalPage()
	{
		int total=0;
		SqlSession session=null;
		
		try
		{
			session=ssf.openSession();	
			total=session.selectOne("replyTotalPage");
			
		}catch(Exception ex)
		{
			System.out.println("replyTotalPage: "+ex.getMessage());
		}
		finally
		{
			if(session!=null)
				session.close(); // 반환 
		}
		
		return total;
	}
	
	// [답글형 게시판 상세페이지] 
	public static BoardVO replyDetailData(int no)
	{
		BoardVO vo = new BoardVO();
		SqlSession session=null;
		
		try
		{
			session=ssf.openSession();	
			vo=session.selectOne("replyDetailData",no); 
			
		}catch(Exception ex)
		{
			System.out.println("replyDetailData: "+ex.getMessage());
		}
		finally
		{
			if(session!=null)
				session.close(); 
		}
		return vo;
	}
	
	// [상세피이지 조회 시 조회 수 증가]
	public static BoardVO hitIncrement(int no)
	{
		BoardVO vo = new BoardVO();
		SqlSession session=null;
		
		try
		{
			session=ssf.openSession();	
			session.update("hitIncrement",no);
			session.commit(); // ★★★
			
			vo=session.selectOne("replyDetailData", no); // ★★★
			
		}catch(Exception ex)
		{
			System.out.println("hitIncrement: "+ex.getMessage());
		}
		finally
		{
			if(session!=null)
				session.close(); 
		}
		
		return vo;
	}
	
	
	// [답글형 게시판 글쓰기] 
	public static BoardVO replyInsertData(BoardVO vo)
	{
		SqlSession session=null;
		
		try
		{
			session=ssf.openSession(true); // 오토커밋하게 true로 변경 	
			session.insert("replyInsertData",vo); // ★★★★
			
		}catch(Exception ex)
		{
			System.out.println("replyInsertData: "+ex.getMessage());
		}
		finally
		{
			if(session!=null)
				session.close(); 
		}
		return vo;
	}
	
	// [글 수정] - 비번 확인 
	public static String replyCheckRealPwd(int no)
	{
		String pwd="";
		SqlSession session=null;
		
		try
		{
			session=ssf.openSession();	
			pwd=session.selectOne("replyCheckRealPwd",no); 
			
		}catch(Exception ex)
		{
			System.out.println("replyCheckRealPwd: "+ex.getMessage());
		}
		finally
		{
			if(session!=null)
				session.close(); 
		}
		return pwd;
	}
	
	
	// [글 수정] - 데이터 update 
	public static BoardVO replyUpdateData(BoardVO vo)
	{
		SqlSession session=null;
		
		try
		{
			session=ssf.openSession(true); 
			session.update("replyUpdateData",vo); 

		}catch(Exception ex)
		{
			System.out.println("replyUpdateData: "+ex.getMessage());
		}
		finally
		{
			if(session!=null)
				session.close(); 
		}
		return vo;
	}
	
	
	
}




