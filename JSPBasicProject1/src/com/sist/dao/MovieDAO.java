package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;

public class MovieDAO {
	private static SqlSessionFactory ssf; // XML을 읽어서 저장해놓는 놈 
	static //static 블록: 값을 채우거나 할 때 사용함.
	{
		try {
			Reader reader = Resources.getResourceAsReader("config.xml");
			// config.xml 파일은 src 폴더 밑에다가 두자. 그럼 따로 경로 지정 안 해줘도 됨. 
			ssf=new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception ex){}
	}
	public static List<MovieVO> movieAllData()
	{
		return ssf.openSession().selectList("movieAllData");
	}
	
	public static List<MusicVO> musicAllData()
	{
		return ssf.openSession().selectList("musicAllData");
	}
}
