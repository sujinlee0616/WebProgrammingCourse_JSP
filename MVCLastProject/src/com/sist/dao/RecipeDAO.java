package com.sist.dao;

import java.util.ArrayList;
import java.util.HashMap;
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
	
	// [레시피 목록]
	public static List<RecipeVO> recipeListData(Map map)
	{
		SqlSession session=null;
		List<RecipeVO> list = new ArrayList<RecipeVO>();
		
		try {
			session=ssf.openSession(); 
			list=session.selectList("recipeListData",map); 
		} 
		catch (Exception ex) {
			System.out.println("recipeListData(): "+ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	// [레시피 목록 총 페이지 수]
	public static int recipeTotalPage()
	{
		SqlSession session=null;
		int total=0; // 초기화 
		
		try {
			session=ssf.openSession(); 
			total=session.selectOne("recipeTotalPage");  // selectOne("recipe-mapper.xml의 id")
		} 
		catch (Exception ex) {
			System.out.println("recipeTotalPage(): "+ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close();
		}
		return total;
	}
	
	// [셰프목록]
	public static List<ChefVO> chefListData(Map map)
	{
		SqlSession session=null;
		List<ChefVO> list = new ArrayList<ChefVO>();
		
		try {
			session=ssf.openSession(); 
			list=session.selectList("chefListData",map); 
		} 
		catch (Exception ex) {
			System.out.println("chefListData(): "+ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	// [셰프목록 총 페이지 수]
	public static int chefTotalPage()
	{
		SqlSession session=null;
		int total=0; // 초기화 
		
		try {
			session=ssf.openSession(); 
			total=session.selectOne("chefTotalPage");  // selectOne("recipe-mapper.xml의 id")
		} 
		catch (Exception ex) {
			System.out.println("chefTotalPage(): "+ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close();
		}
		return total;
	}
	
	// [레시피 상세페이지]
	public static RecipeDetailVO recipeDetailData(int no)
	{
		SqlSession session=null;
		RecipeDetailVO vo = new RecipeDetailVO();
		
		try {
			session=ssf.openSession(); 
			vo=session.selectOne("recipeDetailData",no);  // recipe-mapper.xml의 recipeDetailData SQL문을, no값 넣어서 수행한 값을, vo에 채움
		} 
		catch (Exception ex) {
			System.out.println("recipeDetailData(): "+ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close();
		}
		
		return vo;
	}
	
	// [레시피 디테일 총 수]
	public static int recipeCount(int no)
	{
		SqlSession session=null;
		int total=0; // 초기화 
		
		try {
			session=ssf.openSession(); 
			total=session.selectOne("recipeCount2",no);  
		} 
		catch (Exception ex) {
			System.out.println("recipeCount(int no): "+ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close();
		}
			return total;
	}
	
	// ================================ 2020.04.09 (목) =====================================
	// [셰프 상세페이지]	
	public static List<RecipeVO> chefDetailData(Map map)
	{
		SqlSession session=null;
		List<RecipeVO> list = new ArrayList<RecipeVO>();
		
		try {
			session=ssf.openSession(); 
			list=session.selectList("chefDetailData",map); 
		} 
		catch (Exception ex) {
			System.out.println("chefDetailData(): "+ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	// [셰프 디테일 총 수]
	public static int chefDataTotalPage(String chef)
	{
		SqlSession session=null;
				
		int total=0; // 초기화 
		
		try {
			session=ssf.openSession(); 
			total=session.selectOne("chefDataTotalPage",chef);  
		} 
		catch (Exception ex) {
			System.out.println("chefDataTotalPage(): "+ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close();
		}
		return total;
	}
	
	
	// [레시피 찾기]	
	public static List<RecipeVO> recipeFindData(String fd)
	{
		SqlSession session=null;
		List<RecipeVO> list = new ArrayList<RecipeVO>();
		
		try {
			session=ssf.openSession(); 
			Map map = new HashMap();
			map.put("fd", fd);
			list=session.selectList("recipeFindData",map);  
		} 
		catch (Exception ex) {
			System.out.println("recipeFindData(): "+ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
}



