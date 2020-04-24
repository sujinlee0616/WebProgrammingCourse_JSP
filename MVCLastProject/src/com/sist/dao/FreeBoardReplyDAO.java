package com.sist.dao;

import java.sql.*;
import java.util.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.sist.vo.BoardReplyVO;

public class FreeBoardReplyDAO {
	private static SqlSessionFactory ssf; // 파싱하는 애 
	static
	{
		ssf=CreateSQLSessionFactory.getSsf();
	}
	
	
	// [댓글 리스트]
	public static List<BoardReplyVO> replyListData(Map map)
	{
		List<BoardReplyVO> list=new ArrayList<BoardReplyVO>();
		SqlSession session=null;
		
		try {
			session=ssf.openSession();
			// list로 값을 받아오는게 아니라, CURSOR쓰니까 ResultSet 값 받아옴 
			session.update("replyListData2",map); // ★항상 cs.executeUpdate쓰는것처럼 얘도 ★항상 update써야함!★★ 프로시저를 호출해서 세션을 업데이트!
			list=(ArrayList<BoardReplyVO>)map.get("pResult"); // ★CURSOR를 받을 땐 ResultSet 사용★
			// MyBatis => CURSOR => List

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("replyListData2"+ex.getMessage());
		} finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	// [댓글 총 페이지]
	public static int replyTotalPage(Map map) 
	{
		int total=0;
		SqlSession session=null;
		
		try {
			session=ssf.openSession(); 
			session.update("replyTotalPage2",map);
			total=(int)map.get("pTotal");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
		return total;
	}
	
	// [댓글쓰기]
	// 매개변수 => 클래스 => 값 변경, 추가 가능 (CALL BY REFERENCE)
	public static void replyInsert(Map map) 
	// MyBatis(freeboard-reply-mapper.xml)에서 프로시저 호출하는 select 문장이 parameterMap을 쓰니까.. 
	{
		SqlSession session=null;
		
		try {
			session=ssf.openSession(); 
			// Q. insert니까 auto commit 위해서  openSession(true) 해줘야 하는가? 
			// A. No. freeboard-reply-mapper.xml에서 select 문장을 실행시킴으로써 프로시저를 호출할 뿐. 
			session.update("replyInsert2",map); // 프로시저 호출 
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
	}
	
	// [댓글수정]
	public static void replyUpdate(Map map) 
	{
		SqlSession session=null;
		
		try {
			session=ssf.openSession(); 
			session.update("replyUpdate2",map); // 프로시저 호출 
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
	}
	
	// [대댓글달기]
	public static void replyReplyInsert(Map map) {
		SqlSession session = null;

		try {
			session = ssf.openSession();
			session.update("replyReplyInsert2", map); // 프로시저 호출

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	
	// [댓글삭제]
	public static void replyReplyDelete(Map map) {
		SqlSession session = null;

		try {
			session = ssf.openSession();
			session.update("replyDelete2", map); // 프로시저 호출

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	
}






