package com.sist.movie.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.common.model.CommonData;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class MovieModel {
	@RequestMapping("site/movie/list.do")
	public String movie_list(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("main_jsp", "movie/list.jsp");
		request.setAttribute("msg", "영화");
		CommonData.commonData(request);
		return "../main.jsp";
	}
}
