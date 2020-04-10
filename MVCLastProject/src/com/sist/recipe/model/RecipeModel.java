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
	
	// [레시피 목록]
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
		
		// DAO 연결
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
		
		request.setAttribute("main_jsp", "../recipe/recipe.jsp"); //main에 include시킴 
		
		return "../main/main.jsp";
	}
	
	// [셰프 목록]
	@RequestMapping("recipe/chef.do")
	public String recipe_chef(HttpServletRequest request, HttpServletResponse response)
	{
		// [페이지 처리]
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
		
		// DAO 연결
		List<ChefVO> list=RecipeDAO.chefListData(map);
		int totalpage=RecipeDAO.chefTotalPage();
		
		// Pagination에서 페이지가 5개씩 나오게 해주자. ex) 1-5 페이지, 6-10 페이지, ... 이렇게 나오게.
		final int BLOCK = 5;
		int startPage = ((curpage - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((curpage - 1) / BLOCK * BLOCK) + BLOCK;

		int allPage = totalpage;
		if (endPage > allPage)
			endPage = allPage;

		// request에 값 싣어서 main.jsp로 보냄 ==> include된 chef.jsp가 그 request 쓸 수 있음.
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("BLOCK", BLOCK);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("allPage", allPage);
		
		request.setAttribute("main_jsp", "../recipe/chef.jsp"); //main에 include시킴 
		return "../main/main.jsp";
		/* 
		 * Q) 왜 main.jsp로 return 하는가? 
		 * A) RecipeModel이 main.jsp로 request를 보내도, chef.jsp가 그 request를 쓸 수 있다. 
		 *    왜냐면 include된 페이지는, 자신을 include한 페이지의 request를 공유받기 때문.
		 */	
	}
	
	// [레시피 상세페이지]
	@RequestMapping("recipe/recipe_detail.do")
	public String recipe_detail(HttpServletRequest request, HttpServletResponse response)
	{
		String no = request.getParameter("no");
		
		// DAO 연결
		int count=RecipeDAO.recipeCount(Integer.parseInt(no));
		
		
		if(count!=0)
		{
			RecipeDetailVO vo = RecipeDAO.recipeDetailData(Integer.parseInt(no));
			vo.setFoodmake(vo.getFoodmake().replace("\n", "@"));
			request.setAttribute("vo", vo);
		}		
		
		
		request.setAttribute("count", count);
		request.setAttribute("main_jsp", "../recipe/recipe_detail.jsp"); //main에 include시킴 
		
		return "../main/main.jsp"; 
	}
	
	// [셰프 상세페이지]
	@RequestMapping("recipe/chef_detail.do")
	public String chef_detail(HttpServletRequest request, HttpServletResponse response)
	{
		String chef_name=request.getParameter("name");
		System.out.println("chef_name="+chef_name);
		// Q) 셰프이름이 한글인데 set character encoding 안 해도 되나요? 
		// A) 안 해도 됨. 그건 post 방식일때 해줘야 하는 것. 
		//    get방식은 톰캣에 설정해준다. (Server > Server.xml > <Connector URIEncoding="UTF-8"..)    
		
		// [페이지 처리]
		String page = request.getParameter("page");
		if (page == null)
			page = "1";
		int curpage = Integer.parseInt(page);
		int rowSize = 20;
		int start = rowSize * (curpage - 1) + 1;
		int end = rowSize * curpage;
		
		// Map
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("chef", chef_name);
				
		// DAO 연결
		List<RecipeVO> list = RecipeDAO.chefDetailData(map);
		
		// title이 너무 길어서 한 줄에 출력되게끔 글자 수 제한 
		for(RecipeVO vo:list)
		{
			String title=vo.getTitle();
			if(title.length()>15)
			{
				title=title.substring(0,12).concat("...");
				vo.setTitle(title);
			}
		}
				
		int totalpage=RecipeDAO.chefDataTotalPage(chef_name);
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("main_jsp", "../recipe/chef_detail.jsp"); //main에 include시킴 
		request.setAttribute("chef", chef_name);
		
		return "../main/main.jsp";
	}
	
	
	// [레시피 찾기]
	@RequestMapping("recipe/recipe_find.do")
	public String recipe_find(HttpServletRequest request, HttpServletResponse response)
	{
		// 메뉴 위에 커서 올리면 툴팁(풍선도움말) 나오게..
		String[] data={
				"전체","밑반찬","메인반찬","국/탕","찌개",
				"초스피드","손님접대","밥/죽/떡","술안주","면/만두",
				"일상","빵","다이어트","디저트","샐러드",
				"양식","김치/젓갈","도시락","간식","돼지고기",
				"영양식","과자","양념/소스","차/음료/술","닭고기",
				"야식","채소류","볶음","스프","소고기",
				"해물류","푸드스타일","육류","달걀/유제품","부침",
				"조림","이유식","무침","해장","명절",
				"버섯류","가공식품류","과일류","튀김","끓이기",
				"찜","비빔","밀가루","건어물류","절임",
				"굽기","삶기","회","쌀","콩/견과류",
				"곡류","데치기","퓨전"
		};
		List<String> nList=Arrays.asList(data); // 배열을 리스트로 바꿀 때 
		request.setAttribute("nlist", nList);
		
		request.setAttribute("main_jsp", "../recipe/recipe_find.jsp"); //main에 include시킴 
		return "../main/main.jsp";
	}
	
	// [레시피 찾기 결과] - AJAX는 얘를 통해서 결과를 넘겨줌
	@RequestMapping("recipe/recipe_find_ok.do") 
	public String recipe_find_ok(HttpServletRequest request, HttpServletResponse response)
	{
		
		String[] data={
				"전체","밑반찬","메인반찬","국/탕","찌개",
				"초스피드","손님접대","밥/죽/떡","술안주","면/만두",
				"일상","빵","다이어트","디저트","샐러드",
				"양식","김치/젓갈","도시락","간식","돼지고기",
				"영양식","과자","양념/소스","차/음료/술","닭고기",
				"야식","채소류","볶음","스프","소고기",
				"해물류","푸드스타일","육류","달걀/유제품","부침",
				"조림","이유식","무침","해장","명절",
				"버섯류","가공식품류","과일류","튀김","끓이기",
				"찜","비빔","밀가루","건어물류","절임",
				"굽기","삶기","회","쌀","콩/견과류",
				"곡류","데치기","퓨전"
		};
		String no=request.getParameter("no");
		
		// DAO 연결 (DB연동)
		List<RecipeVO> list=RecipeDAO.recipeFindData(data[Integer.parseInt(no)-1].replace("/","|"));
			// Q1. 왜 data[Integer.parseInt(no)-1]? 
			// A1. 파라미터로 받는 no가, recipe_find.jsp에서 확인하면, foreach 구문에서, var가 1부터 시작했기 때문.
			// Q2. 왜 replace("/","|") ?
			// A2. "면/우동" 이런 애들 면 or 우동 으로 SQL에서 찾을 수 있게 하기 위해서
		
		// title 글자 수 자름
		for(RecipeVO vo:list)
		{
			String title=vo.getTitle();
			if(title.length()>10)
			{
				title=title.substring(0,9).concat("...");
				vo.setTitle(title);
			}
		}

		request.setAttribute("data", data);
		request.setAttribute("list", list);		
		return "../recipe/recipe_find_ok.jsp";
	}
	
}







