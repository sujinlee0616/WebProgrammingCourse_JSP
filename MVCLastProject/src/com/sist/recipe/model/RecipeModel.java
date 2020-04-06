package com.sist.recipe.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.MainDAO;

@Controller
public class RecipeModel {
	@RequestMapping("recipe/recipe.do")
	public String recipe_recipe(HttpServletRequest request, HttpServletResponse response)
	{
		//
		// List<RecipeVO> vo= MainDAO.mainRecipeData();
		
		// 요청 ==> 처리 
		return "../main/main.jsp";
	}
}
