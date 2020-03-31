package com.sist.news;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import com.sist.news.*;
import com.sist.model.Model;

public class NewsModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		try 
		{
			request.setCharacterEncoding("UTF-8");
		} catch (Exception ex) {}
		
		String fd=request.getParameter("fd");
		if(fd==null || fd.equals(""))
		{
			fd="맛집";
		}
		NewsManager mgr=new NewsManager();
		List<Item> list=mgr.newsAllData(fd);
		
		request.setAttribute("list", list); // request에 데이터를 싣어서
		return "news/news.jsp"; // 이 JSP 파일로 보낸다.
	}

}
