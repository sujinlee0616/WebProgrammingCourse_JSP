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
	
	// [답글달기] 
	public static void replyReplyData(int pno,BoardVO vo)
	{
		SqlSession session=null;
		
		try
		{
			session=ssf.openSession();
			
			// 1. 엄마 글의 정보를 먼저 읽어 들어온다
			BoardVO pvo=session.selectOne("replyParentInfoData",pno); // 상위 글의 데이터 모음(pvo) 가져옴  
			System.out.println("1번 수행 완료");
			
			// 2. 같은 그룹 안에 있는 글들의 group_step 1씩 증가시킨다
			session.update("replyGroupStepIncrement",pvo);
			System.out.println("2번 수행 완료");
			
			// 3. 답글 insert함
			vo.setGroup_id(pvo.getGroup_id());
			vo.setGroup_step(pvo.getGroup_step()+1);
			vo.setGroup_tab(pvo.getGroup_tab()+1);
			vo.setRoot(pno);
			session.insert("replyReplyData",vo);
			System.out.println("3번 수행 완료");
			
			// 4.엄마글의 depth(자기 밑에 몇 개를 달고 있는지) 1개 증가시킴
			session.update("replyDetphIncrement",pno);
			System.out.println("4번 수행 완료");
			
			// 커밋 날림 - 1~4 다 정상수행하면 커밋하고 
			session.commit();
			
		}catch(Exception ex)
		{
			System.out.println("replyReplyData: "+ex.getMessage());
			session.rollback(); // 1~4 중 하나라도 정상수행 못한다면 롤백하라. 
		}
		finally
		{
			if(session!=null)
				session.close(); 
		}
	}

	
	// [삭제하기]
	public static boolean replyDeleteData(int no, String pwd)  // return형이 이게 맞나? return형을 뭘로 해줘야하나.... 
	{
		boolean bCheck=false;
		SqlSession session=null;
		
		try
		{
			session=ssf.openSession();
			
			// 1.비밀번호 맞는지 체크 ==> 예전에 만들어놨던 replyCheckRealPwd 재활용
			String db_pwd=session.selectOne("replyCheckRealPwd",no);
			if(db_pwd.equals(pwd)) // 만약 비번이 맞다면 bCheck를 true로 바꾸고 
			{
				bCheck=true; 
				// 2. 삭제할 글의 데이터를 가지고 온다.
				BoardVO vo=session.selectOne("replyDeleteInfoData",no);
				
				// 3-1. depth가 0인 경우 (자식 글 없는 경우) ==> 삭제
				if(vo.getDepth()==0) // depth가 0이라면 
				{
					session.delete("replyDeleteData",no);
				}
				// 3-2. depth가 0이 아닌 경우 (자식 글이 1개 이상 있는 경우) ==> 그냥 삭제하지 말고 '관리자가 삭제한 글이다'라고 바꿔줘야
				else 
				{
					vo.setSubject("관리자가 삭제한 게시물입니다");
					vo.setContent("관리자가 삭제한 게시물입니다");
					vo.setNo(no);
					session.update("replySubjectUpdate",vo);
				}
				// 4. depth 감소시킨다.
				session.update("replyDepthDecrement",vo.getRoot());  // 상위번호의 depth를 감소시켜줘야하니까 no가 아니라 vo.getRoot()
			}
			else // 만약 비번이 틀리다면 bCheck를 false로 바꾸고 아무것도 실행하지X
			{
				bCheck=false;
			}
			
			// 커밋 날림 - 1~4 다 정상수행하면 커밋하고 
			session.commit();
			
		}catch(Exception ex)
		{
			System.out.println("replyDeleteData: "+ex.getMessage());
			session.rollback(); // 1~4 중 하나라도 정상수행 못한다면 롤백하라. 
		}
		finally
		{
			if(session!=null)
				session.close(); 
		}
		
		return bCheck;
	}
	
	
}




