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
	
	// [영화관 정보] <=== 얘는 어디에??? 
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
	
	// []
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
	
}










