package com.sist.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.*;
import com.sist.vo.*;

public class MainDAO {
	private static SqlSessionFactory ssf;
	static
	{
		ssf=CreateSQLSessionFactory.getSsf();
	}
	
	// [mainImageData] - 이렇게 하면 오류 잘 못잡아서 아래에 새로 코딩했음.
	/* 
	public static List<CategoryVO> mainImageData()
	{
		SqlSession session=ssf.openSession();
		List<CategoryVO> list=session.selectList("mainImageData");
		
		session.close();
		
		return list;
	}
	*/
	
	// [mainImageData] - MyBatis 에러 잡기 쉽게 try~catch 절로 만들었음 
	public static List<CategoryVO> mainImageData()
	{
		SqlSession session=null; // null 해줘야!
		List<CategoryVO> list=new ArrayList<CategoryVO>();
		
		try {
			session=ssf.openSession(); // 이전에 코딩했던 버젼의 getConnection에 해당함 
			list=session.selectList("mainImageData"); // 이전에 코딩했던 버젼의, preparedStautement에 SQL문 갖다놓는 것에 해당함
			// SQL문: maain-mapper.xml의 id="mainImageData"인 SQL문.
		} catch (Exception ex) {
			System.out.println("mainImageData(): "+ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	// [recipeCount]
	public static int recipeCount()
	{
		int count=0;
		SqlSession session=null;
		try {
			session=ssf.openSession(); // getConnection
			count=session.selectOne("recipeCount");
			
		} catch (Exception ex) {
			System.out.println("recipeCount(): "+ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close();
		}
		
		return count;
	}
	
	public static List<RecipeVO> mainRecipeData()
	{
		SqlSession session=null;
		List<RecipeVO> list=new ArrayList<RecipeVO>();
		try {
			session=ssf.openSession();
			list=session.selectList("mainRecipeData");
		} catch (Exception ex) {
			System.out.println("mainRecipeData(): "+ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	public static FoodVO mainFoodDetailData(int no)
	{
		SqlSession session=null;
		FoodVO vo=new FoodVO();
		try {
			session=ssf.openSession();
			vo=session.selectOne("mainFoodDetailData", no);
		} catch (Exception ex) {
			System.out.println("mainFoodDetailData(): "+ex.getMessage());
		}
		finally {
			if(session!=null)
				session.close();
		}
		return vo;
	}




}












