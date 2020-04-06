package com.sist.user.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

@Controller
public class MainModel {
	@RequestMapping("main/main.do")
	public String main_main(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("main_jsp", "home.jsp");
		
		List<CategoryVO> clist=MainDAO.mainImageData();
		request.setAttribute("clist", clist); //clist: category list
		
		List<RecipeVO> rlist=MainDAO.mainRecipeData();
		int count=-MainDAO.recipeCount();
		request.setAttribute("rlist", rlist);
		request.setAttribute("count", count);
		
		int no=(int)(Math.random()*119)+1; // 1~181까지의 난수 
		FoodVO fvo=MainDAO.mainFoodDetailData(no);
		request.setAttribute("fvo", fvo);
		
		return "../main/main.jsp";
	}

}
