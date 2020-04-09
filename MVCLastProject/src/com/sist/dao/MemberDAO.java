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
	
	// 우편번호 
	public static List<ZipcodeVO> postfindData(String dong)
	{
		List<ZipcodeVO> list = new ArrayList<ZipcodeVO>();
		
		SqlSession session=null;
		
		try {
			session=ssf.openSession(); // 커넥션 연결 
			list=session.selectList("postfindData",dong); // member-mapper.xml의 postfindData SQL문에 dong 넣고 수행한 결과를  list에 넣는다 
		} 
		catch (Exception ex) {
			System.out.println("postfindData: "+ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close(); // 세션 반환. 우리가 커넥션 풀 지금 8개 쓴다고 설정해놔서... 반환 안 하면 8번만 버튼 누르면 뻑남 
		}
		
		return list;
	}
	
	// id 중복체크 - 
	public static int idcheckData(String id)
	{
		int count=0;
		SqlSession session=null;
		
		try {
			session=ssf.openSession(); // 커넥션 연결 
			count=session.selectOne("idcheckData",id); // member-mapper.xml의 idcheckData SQL문에 id 넣고 수행한 결과를 count에 넣는다 
		} 
		catch (Exception ex) {
			System.out.println("idcheckData: "+ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close(); // 세션 반환. 우리가 커넥션 풀 지금 8개 쓴다고 설정해놔서... 반환 안 하면 8번만 버튼 누르면 뻑남 
		}
		return count;
	}
	
	// 회원가입 - 정보 INSERT
	public static void memberInsert(MemberVO vo)
	{
		SqlSession session=null;
		
		try {
			session=ssf.openSession(true); // 커넥션 연결. Insert라 commit 해줘야해서 (true) 넣었음.
			session.insert("memberInsert",vo); // member-mapper.xml의 idcheckData SQL문에 id 넣고 수행한 결과를 count에 넣는다 
		} 
		catch (Exception ex) {
			System.out.println("idcheckData: "+ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close(); // 세션 반환. 우리가 커넥션 풀 지금 8개 쓴다고 설정해놔서... 반환 안 하면 8번만 버튼 누르면 뻑남 
		}
		
	}
	
	// 로그인 - ID/PWD 맞는지 확인
	public static MemberVO memberLogin(String id,String pwd)  // 매개변수: MemberModel에서 받아왔음
	{
		MemberVO vo=new MemberVO(); // 값을 4개(id,pwd,admin,msg) 넘기니까 VO 만들어서 VO로 넘김 
		SqlSession session=null;
		
		try {
			session=ssf.openSession(); // 커넥션 연결.
			int count=session.selectOne("idCount",id); // member-mapper.xml의 idCount SQL문에 id 넣고 수행한 결과를 count에 넣는다 
			if(count==0)
			{
				vo.setMsg("NOID");
			}
			else
			{
				MemberVO mvo=session.selectOne("getPwd",id); // member-mapper.xml의 getPwd SQL문에 id 넣고 수행한 결과를 count에 넣는다
				if(pwd.equals(mvo.getPwd())) // MemberModel에서 받아온 pwd(login_frm의 input에 입력한 pwd)가 id 기반으로 DB에서 찾은 pwd와 일치한다면 
				{
					vo.setMsg("OK");
					vo.setAdmin(mvo.getAdmin());
					vo.setName(mvo.getName());
				}
				else
				{
					vo.setMsg("NOPWD");
				}
			}
			
		} 
		catch (Exception ex) {
			System.out.println("memberLogin: "+ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close(); // 세션 반환. 우리가 커넥션 풀 지금 8개 쓴다고 설정해놔서... 반환 안 하면 8번만 버튼 누르면 뻑남 
		}
		
		return vo;
	}
}









