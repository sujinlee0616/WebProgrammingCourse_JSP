package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import com.sist.dao.*;
import java.util.*;

public class MiddleModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		
		String cno=request.getParameter("cno");
		System.out.println(cno);
		if(cno==null)
			cno="3";
		
		List<FoodVO> list = FoodDAO.middleListData(Integer.parseInt(cno));
		
		// JSP로 결과값 전송 
		request.setAttribute("list", list); // request에 데이터 싣어서 category.jsp한테 전달  
		
		return "food/middle.jsp";
	}

}
