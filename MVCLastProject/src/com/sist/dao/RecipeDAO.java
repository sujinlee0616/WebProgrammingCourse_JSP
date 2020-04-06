package com.sist.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.sist.vo.*;
/*
 *      request                       request
 * 요청  =========> DispatcherServlet  =========> Model <=========> DAO 
 * 				  request를 받는다  				DAO에서 받은 값을 request에 추가 (request.setAttribute)
 */

public class RecipeDAO {
	private static SqlSessionFactory ssf;
	static
	{
		ssf=CreateSQLSessionFactory.getSsf();
	}
	
	public static List<RecipeVO> recipeListData(Map map)
	{
		SqlSession session=null;
		List<RecipeVO> list = new ArrayList<RecipeVO>();
		
		try {
			session=ssf.openSession(); 
			list=session.selectList("recipeListData",map); 
		} catch (Exception ex) {
			System.out.println("recipeListData(): "+ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	public static int recipeTotalPage()
	{
		SqlSession session=null;
		int total=0; // 초기화 
		
		try {
			session=ssf.openSession(); 
			total=session.selectOne("recipeTotalPage");  // selectOne("recipe-mapper.xml의 id")
		} catch (Exception ex) {
			System.out.println("recipeTotalPage(): "+ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close();
		}
		return total;
	}
	
}
