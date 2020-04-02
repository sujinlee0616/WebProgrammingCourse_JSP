package com.sist.dao;

import java.io.*;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class EmpDAO {
	private static SqlSessionFactory ssf;
	static
	{
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader); // 이 build라는 메소드가 SAX 파싱을 해준다?? 
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static List<EmpVO> empAllData()
	{
		SqlSession session=ssf.openSession();
		List<EmpVO> list = session.selectList("empAllData");
		session.close();
		
		return list;
	
	}
		
}








