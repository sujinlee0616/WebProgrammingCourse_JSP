package com.sist.movie.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.common.model.CommonData;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class ReserveModel {
	@RequestMapping("site/movie/reserve/reserve.do")
	public String movie_reserve(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("main_jsp", "movie/reserve/reserve.jsp");
		request.setAttribute("msg", "영화 예매");
		CommonData.commonData(request); // 공통부분 불러옴
		return "../../main.jsp";
	}
}
