package com.sist.dao;

import java.io.*;
import java.util.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MovieDAO {
	private static SqlSessionFactory ssf;
	
	static
	{
		try {
			Reader reader=Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	
	// [영화목록 출력]
	public static List<MovieVO> movieListData()
	{
		List<MovieVO> list = new ArrayList<MovieVO>();
		SqlSession session=null;
		
		try {
			session=ssf.openSession();
			list=session.selectList("movieListData");
		} 
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close(); // DBCP 반환 
		}
		
		return list;
	}
	
	// [DB에 값 채워넣기] MainClass에서 사용 ==> movie 테이블에 theaterno가 없었어서, 적당한 랜덤값 생성해서 theaterno 집어넣음
	public static void movieTheaterUpdate(MovieVO vo)
	{
		SqlSession session=null;
		
		try {
			session=ssf.openSession(true); // update auto commit하게
			session.update("movieTheaterUpdate",vo);
		} 
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close(); // DBCP 반환 
		}
	}
	
	// [영화관 출력]
	public static ReserveTheaterVO movieTheaterData(int tno)
	{
		ReserveTheaterVO vo=new ReserveTheaterVO();
		SqlSession session=null;
		
		try {
			session=ssf.openSession(); 
			vo=session.selectOne("movieTheaterData",tno);
		} 
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close(); 
		}
		
		return vo;
	}
	
	// [DB에 값 채워넣기] MainClass에서 사용 ==> reservetheater 테이블에 tdate가 없었어서, 적당한 랜덤값 생성해서 tdate 집어넣음
	public static void movieDateUpdate(ReserveTheaterVO vo)
	{
		SqlSession session=null;
		
		try {
			session=ssf.openSession(true); // update auto commit하게
			session.update("movieDateUpdate",vo);
		} 
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close(); // DBCP 반환 
		}
	}
	
	
	// [DB에 값 채워넣기] MainClass에서 사용 ==> reservedate 테이블의 time값이 null이라, time 값 집어넣음 
	public static void movieTimeUpdate(ReserveDateVO vo)
	{
		SqlSession session=null;
		
		try {
			session=ssf.openSession(true); // update auto commit하게
			session.update("movieTimeUpdate",vo);
		} 
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close(); // DBCP 반환 
		}
	}
	
	
	// []
	// 1) tno=3인 time=3,4,7,11,14,27,28,29 <==이 time을 가져옴 
	public static String movieTimeData(int tno)
	{
		String result="";
		SqlSession session=null;
		try {
			session=ssf.openSession(); 
			result=session.selectOne("movieTimeData",tno); // 데이터가 한 개니까 selectOne 
		} 
		catch (Exception ex) {
			// ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close(); // DBCP 반환 
		}
		return result;
	}
	
	// 2) tno가 3이면 09:00 (사실, 위에서, time=3,4,7,11... 이런거면, 이걸 시간대와 매칭시켜줌 09:00, 09:30, ... 이렇게) 
	// 여러개를 LIST에 집어넣어서 mybatis에서 forEach in() 구문 써도 되지만 이건 Spring할 때 배울 것임. 아직 쓰지X. 
	public static String movieTimeData2(int tno)
	{
		String result="";
		SqlSession session=null;
		try {
			session=ssf.openSession(); 
			result=session.selectOne("movieTimeData2",tno); //  데이터가 한 개니까 selectOne 
		} 
		catch (Exception ex) {
			// ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close(); // DBCP 반환 
		}
		return result;
	}
	
}










