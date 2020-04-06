package com.sist.recipe.model;

import java.util.*;
import com.sist.vo.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.MainDAO;
import com.sist.dao.RecipeDAO;

@Controller
public class RecipeModel {
	@RequestMapping("recipe/recipe.do")
	public String recipe_recipe(HttpServletRequest request, HttpServletResponse response)
	{	
		
		// [페이지 처리]
		// 요청 ==> 처리
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		
		int rowSize=20;
		int start=rowSize*(curpage-1)+1;
		int end=rowSize*curpage;
		
		// Map 
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<RecipeVO> list=RecipeDAO.recipeListData(map);
		// title이 너무 길어서 한 줄에 출력되게끔 글자 수 제한 ==> 나중엔 이거 JSTL로도 할 수 있다.
		for(RecipeVO vo:list)
		{
			String title=vo.getTitle();
			if(title.length()>15)
			{
				title=title.substring(0,12).concat("...");
				vo.setTitle(title);
			}
		}
		int totalpage=RecipeDAO.recipeTotalPage();
		
		// Pagination에서 페이지가 10개씩 나오게 해주자. ex) 1-10 페이지, 11-20 페이지, ... 이렇게 나오게.
		final int BLOCK=10; 
		int startPage=((curpage-1)/BLOCK*BLOCK)+1; 
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		int allPage=totalpage;
		if(endPage>allPage) 
			endPage=allPage;
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("BLOCK", BLOCK);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("allPage", allPage);
		
		request.setAttribute("main_jsp", "../recipe/recipe.jsp");
		
		return "../main/main.jsp";
	}
	
	
	@RequestMapping("recipe/chef.do")
	public String recipe_chef(HttpServletRequest request, HttpServletResponse response)
	{
		// [페이지 처리]
		// 요청 ==> 처리
		String page = request.getParameter("page");
		if (page == null)
			page = "1";
		int curpage = Integer.parseInt(page);

		int rowSize = 30;
		int start = rowSize * (curpage - 1) + 1;
		int end = rowSize * curpage;

		// Map
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<ChefVO> list=RecipeDAO.chefListData(map);
		int totalpage=RecipeDAO.chefTotalPage();
		
		// Pagination에서 페이지가 5개씩 나오게 해주자. ex) 1-5 페이지, 6-10 페이지, ... 이렇게 나오게.
		final int BLOCK = 5;
		int startPage = ((curpage - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((curpage - 1) / BLOCK * BLOCK) + BLOCK;

		int allPage = totalpage;
		if (endPage > allPage)
			endPage = allPage;

		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("BLOCK", BLOCK);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("allPage", allPage);
		
		request.setAttribute("main_jsp", "../recipe/chef.jsp");
		return "../main/main.jsp";
	}
}



/* 
 * RecipeModel이 main.jsp로 request를 보내도, recipe.jsp가 그 request를 쓸 수 있다. 
 * 왜냐면 include된 페이지는, 자신을 include한 페이지의 request를 공유받기 때문.
 */




