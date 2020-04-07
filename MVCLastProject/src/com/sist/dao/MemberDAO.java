package com.sist.dao;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.sist.vo.*;

public class MemberDAO {
	private static SqlSessionFactory ssf;
	static
	{
		ssf=CreateSQLSessionFactory.getSsf();
	}
	public static List<ZipcodeVO> postfindData(String dong)
	{
		List<ZipcodeVO> list = new ArrayList<ZipcodeVO>();
		
		SqlSession session=null;
		try {
			session=ssf.openSession(); // 커넥션 연결 
			list=session.selectList("postfindData",dong); // member-mapper.xml의 postfindData SQL문을  list에 넣는다 
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close(); // 세션 반환. 우리가 커넥션 풀 지금 8개 쓴다고 설정해놔서... 반환 안 하면 8번만 버튼 누르면 뻑남 
		}
		return list;
	}
	
	
}
