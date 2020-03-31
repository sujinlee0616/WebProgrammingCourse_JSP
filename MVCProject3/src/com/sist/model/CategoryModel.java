package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import com.sist.dao.*;
import java.util.*;

public class CategoryModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		List<CategoryVO> list = FoodDAO.categoryListData();
		request.setAttribute("list", list); // request에 데이터 싣어서 category.jsp한테 전달  
		return "food/category.jsp";
	}

}
