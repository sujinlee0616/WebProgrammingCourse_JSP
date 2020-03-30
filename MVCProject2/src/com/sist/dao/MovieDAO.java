package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;

public class MovieDAO {
	private static SqlSessionFactory ssf; // XML을 읽어서 저장해놓는 놈 
	
	static //static 블록: 값을 채우거나 할 때 사용함.
	{
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			// config.xml 파일은 src 폴더 밑에다가 두자. 그럼 따로 경로 지정 안 해줘도 됨. 
			ssf=new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	// 목록 읽기 
		public static List<MovieVO> movieListData(Map map)
		{
			List<MovieVO> list = new ArrayList<MovieVO>();
			SqlSession session=ssf.openSession();
			list=session.selectList("movieListData",map);
			// 						 ============   ====
			// board-mapper.xml: 		id			parameterType
			
			// connection 반환
			session.close();
			return list;
		}
	
		// 총 페이지 구하기
		public static int movieTotalPage()
		{
			int total=0;
			SqlSession session=ssf.openSession();
			total=session.selectOne("movieTotalPage");
			
			session.close();
			return total;
		}

	
}
