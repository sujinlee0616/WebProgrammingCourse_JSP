package com.sist.dao;

import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;

public class FoodDAO {
	private static SqlSessionFactory ssf;
	// 이미 XML Parser 만들어져 있음 ==> 아까처럼 DocumentBuilderFactory 불러와서 만들 필요 없음
	
	static
	{
		try
		{
			// XML 읽기
			Reader reader=Resources.getResourceAsReader("Config.xml");
			// XML 파싱 (XML 데이터를 읽는다) 
			ssf=new SqlSessionFactoryBuilder().build(reader);
			// ssf: XML 데이터가 저장되는 공간
			
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static List<CategoryVO> categoryListData()
	{
		SqlSession session = ssf.openSession();
		// Connection: Session 안에 Connection을 갖고 있는 것. ==> Session이 닫히면, connection이 닫혀서 값이 날아감. 
		List<CategoryVO> list = session.selectList("categoryListData");
		
		session.close(); // Connection 반환 
		return list;
	}
	
	public static List<FoodVO> middleListData(int cno)
	{
		SqlSession session = ssf.openSession();
		// Connection: Session 안에 Connection을 갖고 있는 것. ==> Session이 닫히면, connection이 닫혀서 값이 날아감. 
		List<FoodVO> list = session.selectList("middleListData",cno); 
		// 														====
		// ==> 이 cno는 food-mapper.xml에서 <select id="middleListData">의 WHERE cno=#{cno} 에서 #{cno}에 들어간다.
		
		session.close(); // Connection 반환 
		return list;
	}
	
	public static FoodVO detailData(int no)
	{
		SqlSession session = ssf.openSession();
		// Connection: Session 안에 Connection을 갖고 있는 것. ==> Session이 닫히면, connection이 닫혀서 값이 날아감. 
		FoodVO list = session.selectOne("detailData",no); 
		// 											====
		// ==> 이 no는 food-mapper.xml에서 <select id="detailData">의 WHERE no=#{no} 에서 #{no}에 들어간다.
		// 한 개짜리니까 selectList가 아니라 selectOne
		
		session.close(); // Connection 반환 
		return list;
	}
	
	
}








